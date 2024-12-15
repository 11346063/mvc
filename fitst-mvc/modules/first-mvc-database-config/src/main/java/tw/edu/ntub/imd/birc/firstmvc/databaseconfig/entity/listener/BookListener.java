package tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.listener;

import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.Book;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.Score;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class BookListener {

    @PrePersist
    public void preSave(Book book) {
        if (book.getCreate_time() == null) {
            book.setCreate_time(LocalDateTime.now());
        }
    }

    @PreUpdate
    public void preUpdate(Book book) {
        book.setCreate_time(LocalDateTime.now());
    }
}
