package tw.edu.ntub.imd.birc.firstmvc.service;

import tw.edu.ntub.imd.birc.firstmvc.bean.AuthorBean;
import tw.edu.ntub.imd.birc.firstmvc.bean.BookBean;

import java.util.List;

public interface AuthorService extends BaseService<AuthorBean, Integer> {
    List<AuthorBean> findAll();
}