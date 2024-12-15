package tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.listener;

import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.Member;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class MemberListener {

    // 新增前檢查
    @PrePersist
    public void PreSave(Member member) {
        if (member.getAvailable() == null) {
            member.setAvailable(true);
        }
        if (member.getTransactionTime() == null) {
            member.setTransactionTime(LocalDateTime.now());
        }
    }

    // 更新前檢查
    @PreUpdate
    public void PreUpdate(Member member) {
        member.setTransactionTime(LocalDateTime.now());
    }
}
