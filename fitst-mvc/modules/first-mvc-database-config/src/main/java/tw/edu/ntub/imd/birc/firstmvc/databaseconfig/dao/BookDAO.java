package tw.edu.ntub.imd.birc.firstmvc.databaseconfig.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.Book;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.Teacher;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookDAO extends BaseDAO<Book, Integer> {
    List<Book> findAll();

//    @Query("SELECT b.author.name FROM Book b WHERE b.author.id = :authorId")
//    List<Book> findByAuthorId(Integer authorId);


}
