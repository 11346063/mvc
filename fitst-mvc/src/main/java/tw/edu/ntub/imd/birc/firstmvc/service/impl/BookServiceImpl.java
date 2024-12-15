package tw.edu.ntub.imd.birc.firstmvc.service.impl;

import org.springframework.stereotype.Service;
import tw.edu.ntub.birc.common.util.CollectionUtils;
import tw.edu.ntub.imd.birc.firstmvc.bean.BookBean;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.dao.BookDAO;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.Book;
import tw.edu.ntub.imd.birc.firstmvc.service.BookService;
import tw.edu.ntub.imd.birc.firstmvc.service.transformer.BookTransformer;
import java.util.List;


//告訴spring這是一個service
@Service
//要實作介面，DAO、transformer為建構元傳入參數(DAO不一定只有一個，依需求)
public class BookServiceImpl extends BaseServiceImpl<BookBean, Book, Integer> implements BookService {
    private final BookDAO bookDAO;
    private final BookTransformer transformer;

    public BookServiceImpl(BookDAO bookDAO, BookTransformer transformer) {
        super(bookDAO, transformer);
        this.bookDAO = bookDAO;
        this.transformer = transformer;
    }


    @Override
    public BookBean save(BookBean bookBean) {
        bookDAO.save(transformer.transferToEntity(bookBean));
        return null;
    }


//    public List<BookBean> findAll() {
//
//        return CollectionUtils.map(bookDAO.findAll(), transformer::transferToBean);
//    }
    public List<Book> findAll(){
        return bookDAO.findAll();
    }

//    public List<BookBean> findAuthor(Integer id) {
//        return bookDAO.findByAuthorId(id).toString();
//    }
}
