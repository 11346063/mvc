package tw.edu.ntub.imd.birc.firstmvc.service.impl;

import org.springframework.stereotype.Service;
import tw.edu.ntub.birc.common.util.CollectionUtils;
import tw.edu.ntub.imd.birc.firstmvc.bean.AuthorBean;
import tw.edu.ntub.imd.birc.firstmvc.bean.BookBean;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.dao.AuthorDAO;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.dao.BookDAO;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.Author;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.Book;
import tw.edu.ntub.imd.birc.firstmvc.service.AuthorService;
import tw.edu.ntub.imd.birc.firstmvc.service.BookService;
import tw.edu.ntub.imd.birc.firstmvc.service.transformer.AuthorTransformer;
import tw.edu.ntub.imd.birc.firstmvc.service.transformer.BookTransformer;

import java.util.List;


//告訴spring這是一個service
@Service
//要實作介面，DAO、transformer為建構元傳入參數(DAO不一定只有一個，依需求)
public class AuthorServiceImpl extends BaseServiceImpl<AuthorBean, Author, Integer> implements AuthorService {
    private final AuthorDAO authorDAO;
    private final AuthorTransformer transformer;

    public AuthorServiceImpl(AuthorDAO authorDAO, AuthorTransformer transformer) {
        super(authorDAO, transformer);
        this.authorDAO = authorDAO;
        this.transformer = transformer;
    }


    @Override
    public AuthorBean save(AuthorBean authorBean) {
        authorDAO.save(transformer.transferToEntity(authorBean));
        return null;
    }


    public List<AuthorBean> findAll() {

        return CollectionUtils.map(authorDAO.findAll(), transformer::transferToBean);
    }
}
