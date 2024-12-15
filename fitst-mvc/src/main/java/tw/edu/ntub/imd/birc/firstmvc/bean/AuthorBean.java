package tw.edu.ntub.imd.birc.firstmvc.bean;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
// 前端資料需要的格式，基本上會跟entity一樣，但是可以因為需求而增加變數
public class AuthorBean {
    @Null
    private Integer id;

    @NotBlank(message = "姓名 - 未填寫")
    @Size(max = 5, message = "name 輸入字數小於{max}個字")
    private String name;

    @NotBlank(message = "birthdate - 未填寫")
    private String birthdate_str;
    private LocalDate birthdate;


    private LocalDateTime create_time;
}
