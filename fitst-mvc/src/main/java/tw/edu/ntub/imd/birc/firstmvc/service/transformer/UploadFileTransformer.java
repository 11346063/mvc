package tw.edu.ntub.imd.birc.firstmvc.service.transformer;

import tw.edu.ntub.imd.birc.firstmvc.bean.BookBean;
import tw.edu.ntub.imd.birc.firstmvc.bean.UploadFileBean;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.Book;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.UploadFile;

public interface UploadFileTransformer extends BeanEntityTransformer<UploadFileBean, UploadFile> {
}
