package tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.listener;

import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.Score;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class ScoreListener {

    @PrePersist
    public void preSave(Score score) {
        if (score.getTransactionTime() == null) {
            score.setTransactionTime(LocalDateTime.now());
        }
    }

    @PreUpdate
    public void preUpdate(Score score) {
        score.setTransactionTime(LocalDateTime.now());
    }
}
