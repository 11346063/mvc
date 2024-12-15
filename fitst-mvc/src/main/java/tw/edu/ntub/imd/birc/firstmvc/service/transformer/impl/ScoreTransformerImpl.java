package tw.edu.ntub.imd.birc.firstmvc.service.transformer.impl;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import tw.edu.ntub.birc.common.util.JavaBeanUtils;
import tw.edu.ntub.imd.birc.firstmvc.bean.ScoreBean;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.Score;
import tw.edu.ntub.imd.birc.firstmvc.service.transformer.ScoreTransformer;

@Component
public class ScoreTransformerImpl implements ScoreTransformer {
    @NonNull
    @Override
    public Score transferToEntity(@NonNull ScoreBean scoreBean) {
        return JavaBeanUtils.copy(scoreBean, new Score());
    }

    @NonNull
    @Override
    public ScoreBean transferToBean(@NonNull Score score) {
        return JavaBeanUtils.copy(score, new ScoreBean());
    }
}
