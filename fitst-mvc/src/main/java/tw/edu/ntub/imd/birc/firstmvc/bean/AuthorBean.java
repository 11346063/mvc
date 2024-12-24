package tw.edu.ntub.imd.birc.firstmvc.bean;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
// 前端資料需要的格式，基本上會跟entity一樣，但是可以因為需求而增加變數
public class AuthorBean {
//    @Null
    private Integer id;

    @NotBlank(message = "姓名 - 未填寫")
    @Size(max = 20, message = "name 輸入字數小於{max}個字")
    private String name;

    @NotBlank(message = "Info Is Null")
    @Size(max = 500, message = "Info Size < 500")
    private String info;

//    @NotBlank(message = "birthdate - 未填寫")
    private String birthdateS;
    @NotNull(message = "birthdate - 未填寫")

    private Date birthdate ;


    private LocalDateTime create_time;
}
