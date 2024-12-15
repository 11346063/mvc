package tw.edu.ntub.imd.birc.firstmvc.service.impl;

import org.springframework.stereotype.Service;
import tw.edu.ntub.birc.common.util.CollectionUtils;
import tw.edu.ntub.imd.birc.firstmvc.bean.MemberBean;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.dao.MemberDAO;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.Member;
import tw.edu.ntub.imd.birc.firstmvc.service.MemberService;
import tw.edu.ntub.imd.birc.firstmvc.service.transformer.MemberTransformer;

import java.util.List;

//告訴spring這是一個service
@Service
//要實作介面，DAO、transformer為建構元傳入參數(DAO不一定只有一個，依需求)
public class MemberServiceImpl extends BaseServiceImpl<MemberBean, Member, String> implements MemberService {
    private final MemberDAO memberDAO;
    private final MemberTransformer transformer;

    public MemberServiceImpl(MemberDAO memberDAO, MemberTransformer transformer) {
        super(memberDAO, transformer);
        this.memberDAO = memberDAO;
        this.transformer = transformer;
    }

    @Override
    // 新增，不一定要寫，可以要用到再回來寫
    public MemberBean save(MemberBean memberBean) {
        return null;
    }

    // 因為原本是全查，但是要增加條件是否啟用，所以才覆寫，不然用父類別的就可以了
    @Override
    public List<MemberBean> searchAll() {
        /*
            轉.edu.ntub.birc.common.util
換成bean: transformer.transferToBean(member)
            轉換成beanList(也可以用迴圈):
            CollectionUtils是學長寫的util，要import tw
            lambda是一種簡化的寫法
            transformer::transferToBean是lambda的一種 (類別名稱::方法(靜態方法可以直接用類別名稱))
            因為被簡化所以一開始比較難懂，先會寫就好
         */
        return CollectionUtils.map(memberDAO.findByAvailableIsTrue(), transformer::transferToBean);
    }
}
