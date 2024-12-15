package tw.edu.ntub.imd.birc.firstmvc.service;
import tw.edu.ntub.imd.birc.firstmvc.bean.TeacherBean;
import java.time.LocalDate;
import java.util.List;

public interface TeacherService extends BaseService<TeacherBean, Integer> {
    List<TeacherBean> searchAllByAge(Integer age);
    List<TeacherBean> searchByName(String name);
    List<TeacherBean> searchByAge(Integer startAge, Integer endAge);
    List<TeacherBean> searchBydate(LocalDate startDate, LocalDate endDate);
}