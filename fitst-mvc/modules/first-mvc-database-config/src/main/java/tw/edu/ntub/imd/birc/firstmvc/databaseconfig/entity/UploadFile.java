package tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.Config;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.listener.AuthorListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Data
@Entity
@EntityListeners(AuthorListener.class)


@Table(name = "upload", schema = Config.DATABASE_NAME)

public class UploadFile {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "table_no", nullable = false)
    private Integer TableNo;
    // 對應欄位資料

    @Column(name = "table_name", nullable = false)
    private String tableName;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "file_path", nullable = false)
    private String filePath;
}