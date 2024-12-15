package tw.edu.ntub.imd.birc.firstmvc.service.impl;

import org.springframework.stereotype.Service;
import tw.edu.ntub.birc.common.util.JavaBeanUtils;
import tw.edu.ntub.imd.birc.firstmvc.bean.ScoreBean;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.dao.ScoreDAO;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.Member;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.Score;
import tw.edu.ntub.imd.birc.firstmvc.service.ScoreService;
import tw.edu.ntub.imd.birc.firstmvc.service.transformer.ScoreTransformer;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreServiceImpl extends BaseServiceImpl<ScoreBean, Score, Integer> implements ScoreService {
    private final ScoreDAO scoreDAO;
    private final ScoreTransformer transformer;

    public ScoreServiceImpl(ScoreDAO scoreDAO, ScoreTransformer transformer) {
        super(scoreDAO, transformer);
        this.scoreDAO = scoreDAO;
        this.transformer = transformer;
    }

    @Override
    public ScoreBean save(ScoreBean scoreBean) {
        Score score = scoreDAO.save(transformer.transferToEntity(scoreBean));
        return transformer.transferToBean(score);
    }


    @Override
    public List<ScoreBean> searchFailedStudentBySubject(String subject) {
        List<ScoreBean> scoreBeanList = new ArrayList<>();
        for (Score score : scoreDAO.findBySubjectAndScoreLessThan(subject, 60)) {
            ScoreBean scoreBean = transformer.transferToBean(score);
            // 取得關聯表資料
            Member member = score.getMemberByStudentId();
            scoreBean.setStudentName(member.getName());
            scoreBeanList.add(scoreBean);
        }
        return scoreBeanList;
    }
}
