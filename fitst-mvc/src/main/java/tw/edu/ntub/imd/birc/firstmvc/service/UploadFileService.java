package tw.edu.ntub.imd.birc.firstmvc.service;

import tw.edu.ntub.imd.birc.firstmvc.bean.BookBean;
import tw.edu.ntub.imd.birc.firstmvc.bean.UploadFileBean;

import java.util.List;

public interface UploadFileService extends BaseService<UploadFileBean, Integer> {
    // UploadFileBean save(UploadFileBean uploadBean);
//    String findAuthor(Integer id);
    List<UploadFileBean> searchFiles(Integer tableNo, String tableName);
}