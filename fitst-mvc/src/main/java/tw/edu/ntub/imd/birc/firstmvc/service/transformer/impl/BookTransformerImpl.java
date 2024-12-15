package tw.edu.ntub.imd.birc.firstmvc.service.transformer.impl;


import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import tw.edu.ntub.birc.common.util.JavaBeanUtils;
import tw.edu.ntub.imd.birc.firstmvc.bean.BookBean;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.Book;
import tw.edu.ntub.imd.birc.firstmvc.service.transformer.BookTransformer;

import javax.annotation.Nonnull;

@Component
public class BookTransformerImpl implements BookTransformer {
    // 記得import spring的
    @NonNull
    @Override
    public Book transferToEntity(@NonNull BookBean bookBean) {
        // 複製左邊的給右邊的，null不複製、名字要相同
        return JavaBeanUtils.copy(bookBean, new Book());
    }

    @NonNull
    @Override
    public BookBean transferToBean(@NonNull Book book) {
        return JavaBeanUtils.copy(book, new BookBean());
    }
}
