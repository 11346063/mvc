package tw.edu.ntub.imd.birc.firstmvc.service.transformer.impl;


import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import tw.edu.ntub.birc.common.util.JavaBeanUtils;
import tw.edu.ntub.imd.birc.firstmvc.bean.MemberBean;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.Member;
import tw.edu.ntub.imd.birc.firstmvc.service.transformer.MemberTransformer;

@Component
public class MemberTransformerImpl implements MemberTransformer {
    // 記得import spring的
    @NonNull
    @Override
    public Member transferToEntity(@NonNull MemberBean memberBean) {
        // 複製左邊的給右邊的，null不複製、名字要相同
        return JavaBeanUtils.copy(memberBean, new Member());
    }

    @NonNull
    @Override
    public MemberBean transferToBean(@NonNull Member member) {
        return JavaBeanUtils.copy(member, new MemberBean());
    }
}
