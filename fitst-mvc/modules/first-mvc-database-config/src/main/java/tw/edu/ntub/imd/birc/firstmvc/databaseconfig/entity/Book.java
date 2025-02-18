package tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.Config;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.listener.BookListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@Entity

@EntityListeners(BookListener.class)

@Table(name = "book", schema = Config.DATABASE_NAME)

public class Book {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    // 對應欄位資料
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "info", length = 500, nullable = false)
    private String info;

    @Column(name = "publication_date", nullable = false)
    private Date publication_date;

    @Column(name = "author_id", nullable = false)
    private Integer author_id;

    @ManyToOne
    @JoinColumn(name = "author_id", insertable = false, updatable = false)  // 外鍵對應到 "author" 表的 id
    private Author author;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime create_time;

    @Column(name = "file_path", length = 200)
    private String filePath;


}