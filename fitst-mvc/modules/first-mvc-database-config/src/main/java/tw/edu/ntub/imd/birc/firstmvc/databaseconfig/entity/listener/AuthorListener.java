package tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.listener;

import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.Author;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.Book;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class AuthorListener {

    @PrePersist
    public void preSave(Author author) {
        if (author.getCreate_time() == null) {
            author.setCreate_time(LocalDateTime.now());
        }
    }

    @PreUpdate
    public void preUpdate(Author author) {
        author.setCreate_time(LocalDateTime.now());
    }
}
