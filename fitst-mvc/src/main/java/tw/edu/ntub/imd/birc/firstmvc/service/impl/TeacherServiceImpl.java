package tw.edu.ntub.imd.birc.firstmvc.service.impl;

import org.springframework.stereotype.Service;
import tw.edu.ntub.birc.common.util.CollectionUtils;
import tw.edu.ntub.imd.birc.firstmvc.bean.TeacherBean;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.dao.TeacherDAO;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.Teacher;
import tw.edu.ntub.imd.birc.firstmvc.service.TeacherService;
import tw.edu.ntub.imd.birc.firstmvc.service.transformer.TeacherTransformer;

import java.time.LocalDate;
import java.util.List;


//告訴spring這是一個service
@Service
//要實作介面，DAO、transformer為建構元傳入參數(DAO不一定只有一個，依需求)
public class TeacherServiceImpl extends BaseServiceImpl<TeacherBean, Teacher, Integer> implements TeacherService {
    private final TeacherDAO teacherDAO;
    private final TeacherTransformer transformer;

    public TeacherServiceImpl(TeacherDAO teacherDAO, TeacherTransformer transformer) {
        super(teacherDAO, transformer);
        this.teacherDAO = teacherDAO;
        this.transformer = transformer;
    }


    @Override
    public TeacherBean save(TeacherBean teacherBean) {
        teacherDAO.save(transformer.transferToEntity(teacherBean));
        return null;
    }

    @Override
    public List<TeacherBean> searchAllByAge(Integer age) {

        return CollectionUtils.map(teacherDAO.findAllByAge(age), transformer::transferToBean);
    }

    public List<TeacherBean> searchByName(String name) {

        return CollectionUtils.map(teacherDAO.findByNameLike(name), transformer::transferToBean);

    }

    public List<TeacherBean> searchByAge(Integer startAge, Integer endAge) {

        return CollectionUtils.map(teacherDAO.findByAgeBetween(startAge, endAge), transformer::transferToBean);
    }

    public List<TeacherBean> searchBydate(LocalDate startDate, LocalDate endDate){
        return CollectionUtils.map(teacherDAO.findByDateBetween(startDate, endDate), transformer::transferToBean);
    }
}
