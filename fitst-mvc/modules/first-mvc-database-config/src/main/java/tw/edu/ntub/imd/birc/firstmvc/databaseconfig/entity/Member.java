package tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity;


import lombok.Data;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.Config;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.listener.MemberListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 學生資料
 *
 * @Since 1.0.1
 */

/*
1. 與資料表對應
2. 會用文件註解填寫名稱及從哪個版本開始
3. 使用類別型態
4. 建完之後建議重開伺服器看看有沒有壞掉
 */

// 長出getter跟setter，和其他必備的東西，要有lombok
@Data
// 告訴spring這是一個entity
@Entity
// 有些欄位有預設值，或是不可為null，所以在新增、更新時檢查
@EntityListeners(MemberListener.class)
// 對應資料表的名稱
@Table(name = "member", schema = Config.DATABASE_NAME)
// 從資料庫取出的資料格式為entity
public class Member {
    /**
     * 學生編號
     *
     * @Since 1.0.1
     */
    // PK
    @Id
    // 對應欄位資料
    @Column(name = "id", length = 10, nullable = false)
    private String id;

    /**
     * 學生姓名
     *
     * @Since 1.0.1
     */
    @Column(name = "name", length = 45, nullable = false)
    private String name;

    /**
     * 性別(0:男生/1:女生)
     *
     * @Since 1.0.1
     */
    @Column(name = "gender", length = 1, nullable = false)
    private String gender;

    /**
     * 啟用狀態(0:啟用/1:不啟用)
     *
     * @Since 1.0.1
     */
    @Column(name = "available", nullable = false)
    private Boolean available;

    /**
     * 最後修改時間
     *
     * @Since 1.0.1
     */
    @Column(name = "transaction_time", nullable = false)
    private LocalDateTime transactionTime;

}