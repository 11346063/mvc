package tw.edu.ntub.imd.birc.firstmvc.databaseconfig.dao;

import org.springframework.stereotype.Repository;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.Teacher;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.UploadFile;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UploadFileDAO extends BaseDAO<UploadFile, Integer> {
    List<UploadFile> findByTableNoAndTableName(Integer tableNo, String tableName);
}
