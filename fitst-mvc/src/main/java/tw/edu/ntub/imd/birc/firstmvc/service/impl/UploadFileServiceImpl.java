package tw.edu.ntub.imd.birc.firstmvc.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tw.edu.ntub.imd.birc.firstmvc.bean.UploadFileBean;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.dao.UploadFileDAO;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.UploadFile;
import tw.edu.ntub.imd.birc.firstmvc.dto.file.uploader.MultipartFileUploader;
import tw.edu.ntub.imd.birc.firstmvc.dto.file.uploader.UploadResult;
import tw.edu.ntub.imd.birc.firstmvc.service.UploadFileService;
import tw.edu.ntub.imd.birc.firstmvc.service.transformer.UploadFileTransformer;


//告訴spring這是一個service
@Service
//要實作介面，DAO、transformer為建構元傳入參數(DAO不一定只有一個，依需求)
public class UploadFileServiceImpl extends BaseServiceImpl<UploadFileBean, UploadFile, Integer> implements UploadFileService {
    private String APPLICATION_CASE_NAME = "/applicationCase/";

    private final UploadFileDAO uploadFileDAO;

    private final UploadFileTransformer uploadFileTransformer;

    private final MultipartFileUploader multipartFileUploader;

    public UploadFileServiceImpl(UploadFileDAO uploadFileDAO,
                                 UploadFileTransformer uploadFileTransformer,
                                 MultipartFileUploader multipartFileUploader) {
        super(uploadFileDAO, uploadFileTransformer);
        this.uploadFileDAO = uploadFileDAO;
        this.uploadFileTransformer = uploadFileTransformer;
        this.multipartFileUploader = multipartFileUploader;
    }

//    @Override
//    public UploadFileBean save(UploadFileBean uploadFileBean) {
//        UploadResult uploadResult = multipartFileUploader.upload(uploadFileBean.getFile(),
//                "protected", uploadFileBean.getTableName(), uploadFileBean.getTableNo().toString());
//        uploadFileBean.setFilePath(uploadResult.getUrl());
//        UploadFile uploadFile = uploadFileDAO.save(uploadFileTransformer.transferToEntity(uploadFileBean));
//        return uploadFileTransformer.transferToBean(uploadFile);
//    }
        @Override
        public UploadFileBean save(UploadFileBean uploadFileBean) {

            UploadResult uploadResult = multipartFileUploader.upload(uploadFileBean.getFile(), "files");

            uploadFileBean.setFilePath(uploadResult.getUrl());
            UploadFile uploadFile = uploadFileDAO.save(uploadFileTransformer.transferToEntity(uploadFileBean));
            return uploadFileTransformer.transferToBean(uploadFile);
        }
}
