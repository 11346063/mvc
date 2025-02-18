package tw.edu.ntub.imd.birc.firstmvc.service.impl;

import org.springframework.stereotype.Service;
import tw.edu.ntub.birc.common.util.CollectionUtils;
import tw.edu.ntub.birc.common.util.MathUtils;
import tw.edu.ntub.imd.birc.firstmvc.bean.BookBean;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.dao.BookDAO;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.Book;
import tw.edu.ntub.imd.birc.firstmvc.dto.file.uploader.MultipartFileUploader;
import tw.edu.ntub.imd.birc.firstmvc.dto.file.uploader.UploadResult;
import tw.edu.ntub.imd.birc.firstmvc.service.BookService;
import tw.edu.ntub.imd.birc.firstmvc.service.UploadFileService;
import tw.edu.ntub.imd.birc.firstmvc.service.transformer.BookTransformer;
import tw.edu.ntub.imd.birc.firstmvc.service.transformer.UploadFileTransformer;

import java.util.List;


//告訴spring這是一個service
@Service
//要實作介面，DAO、transformer為建構元傳入參數(DAO不一定只有一個，依需求)
public class BookServiceImpl extends BaseServiceImpl<BookBean, Book, Integer> implements BookService {
    private final BookDAO bookDAO;
    private final BookTransformer transformer;
    private final MultipartFileUploader multipartFileUploader;

    public BookServiceImpl(BookDAO bookDAO,
                           BookTransformer transformer,
                           MultipartFileUploader multipartFileUploader) {
        super(bookDAO, transformer);
        this.bookDAO = bookDAO;
        this.transformer = transformer;
        this.multipartFileUploader = multipartFileUploader;
    }


    @Override
    public BookBean save(BookBean bookBean) {
        UploadResult uploadResult = multipartFileUploader.upload(bookBean.getFile(), "Image");

        bookBean.setFilePath(uploadResult.getUrl());

        bookDAO.save(transformer.transferToEntity(bookBean));
        return null;
    }


//    public List<BookBean> findAll() {
//
//        return CollectionUtils.map(bookDAO.findAll(), transformer::transferToBean);
//    }
    public List<BookBean> findAll(){
        return CollectionUtils.map(bookDAO.findAll(), transformer::transferToBean);
    }

//    public List<BookBean> findAuthor(Integer id) {
//        return bookDAO.findByAuthorId(id).toString();
//    }
    public List<BookBean> findId(Integer id) {
        return CollectionUtils.map(bookDAO.findAllById(id), transformer::transferToBean);
    }
}
