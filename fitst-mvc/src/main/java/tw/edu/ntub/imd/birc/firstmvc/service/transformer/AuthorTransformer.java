package tw.edu.ntub.imd.birc.firstmvc.service.transformer;

import tw.edu.ntub.imd.birc.firstmvc.bean.AuthorBean;
import tw.edu.ntub.imd.birc.firstmvc.bean.BookBean;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.Author;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.Book;

public interface AuthorTransformer extends BeanEntityTransformer<AuthorBean, Author> {
}
