package tw.edu.ntub.imd.birc.firstmvc.service;

import tw.edu.ntub.imd.birc.firstmvc.bean.ScoreBean;

import java.util.List;

// interface 用來"定義"方法，這裡不"實作"方法
public interface ScoreService extends BaseService<ScoreBean, Integer> {
    List<ScoreBean> searchFailedStudentBySubject(String subject);
}