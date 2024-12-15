package tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity;

import lombok.Data;
import net.bytebuddy.asm.Advice;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.Config;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.listener.MemberListener;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity



@Table(name = "teacher", schema = Config.DATABASE_NAME)

public class Teacher {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sno", nullable = false)
    private Integer sno;
    // 對應欄位資料
    @Column(name = "t_id", length = 6, nullable = false)
    private String t_id;


    @Column(name = "name", length = 45, nullable = false)
    private String name;

    @Column(name = "number",length = 45, nullable = false)
    private String number;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "date", nullable = false)
    private LocalDate date;


}