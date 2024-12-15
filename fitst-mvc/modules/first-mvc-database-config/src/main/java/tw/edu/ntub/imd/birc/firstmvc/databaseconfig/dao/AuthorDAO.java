package tw.edu.ntub.imd.birc.firstmvc.databaseconfig.dao;

import org.springframework.stereotype.Repository;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.Author;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.Book;

import java.util.List;

@Repository
public interface AuthorDAO extends BaseDAO<Author, Integer> {
    List<Author> findAll();
}
