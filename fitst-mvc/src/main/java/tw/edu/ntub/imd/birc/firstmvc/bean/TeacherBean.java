package tw.edu.ntub.imd.birc.firstmvc.bean;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
// 前端資料需要的格式，基本上會跟entity一樣，但是可以因為需求而增加變數
public class TeacherBean{
    private LocalDate date;

    @NotBlank(message = "姓名 - 未填寫")
    private String name;

    @NotBlank(message = "電話 - 未填寫")
    private String number;

    @NotNull(message = "年齡 - 未瑱寫")
    @Max(110)
    @Min(0)
    private Integer age;

    @NotBlank(message = "t_id - 未填寫")
    @Size(max = 6, message = "t_id 輸入字數大於{max}個字")
    @Size(min = 6, message = "t_id 輸入字數小於{min}個字")
    private String t_id;

    @NotBlank(message = "Date - 未填寫")
    private String SDate;



}
