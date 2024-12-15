package tw.edu.ntub.imd.birc.firstmvc.databaseconfig.dao;

import org.springframework.stereotype.Repository;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.Teacher;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TeacherDAO extends BaseDAO<Teacher, Integer> {
    List<Teacher> findAllByAge(int age);
    List<Teacher> findByNameLike(String name);
    List<Teacher> findByAgeBetween(Integer startAge, Integer endAge);
    List<Teacher> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
