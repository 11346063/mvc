package tw.edu.ntub.imd.birc.firstmvc.service;

import tw.edu.ntub.imd.birc.firstmvc.bean.BookBean;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.Book;

import java.util.List;

public interface BookService extends BaseService<BookBean, Integer> {
    List<Book> findAll();
//    String findAuthor(Integer id);
}