package tw.edu.ntub.imd.birc.firstmvc.bean;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
//entity類別不可放進來
public class ScoreBean {
    // 以下@開頭的東西用來做表單驗證，如果不符合會直接回傳前端message
    @Null(message = "id - 不得填寫")
    private Integer id;
    // not "" and not null
    @NotBlank(message = "學號 - 未填寫")
    @Size(max = 10, message = "學號 - 輸入字數大於{max}個字")
    private String studentId;
    @NotBlank(message = "科目 - 未填寫")
    @Size(max = 20, message = "科目 輸入字數大於{max}個字")
    private String subject;
    @NotNull(message = "成績 - 未填寫")
    @Max(100)
    @Min(0)
    private Integer score;
    @Null(message = "最後修改時間 - 不得填寫")
    private LocalDateTime transactionTime;
    private String studentName;
}
