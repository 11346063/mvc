package tw.edu.ntub.imd.birc.firstmvc.service.transformer.impl;


import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import tw.edu.ntub.birc.common.util.JavaBeanUtils;
import tw.edu.ntub.imd.birc.firstmvc.bean.BookBean;
import tw.edu.ntub.imd.birc.firstmvc.bean.UploadFileBean;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.Book;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.UploadFile;
import tw.edu.ntub.imd.birc.firstmvc.service.transformer.BookTransformer;
import tw.edu.ntub.imd.birc.firstmvc.service.transformer.UploadFileTransformer;

@Component
public class UploadFileTransformerImpl implements UploadFileTransformer {
    // 記得import spring的
    @NonNull
    @Override
    public UploadFile transferToEntity(@NonNull UploadFileBean uploadFileBean) {
        // 複製左邊的給右邊的，null不複製、名字要相同
        return JavaBeanUtils.copy(uploadFileBean, new UploadFile());
    }

    @NonNull
    @Override
    public UploadFileBean transferToBean(@NonNull UploadFile uploadFile) {
        return JavaBeanUtils.copy(uploadFile, new UploadFileBean());
    }
}
