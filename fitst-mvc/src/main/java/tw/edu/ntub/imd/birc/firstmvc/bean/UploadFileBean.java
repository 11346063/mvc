package tw.edu.ntub.imd.birc.firstmvc.bean;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
// 前端資料需要的格式，基本上會跟entity一樣，但是可以因為需求而增加變數
public class UploadFileBean {

//    @ManyToOne
//    @JoinColumn(name = "author_id", insertable = false, updatable = false)
//    private AuthorBean author;

//    @Null(message = "id 不得填寫")
    private Integer id;

    @NotNull
    private Integer tableNo;

    @NotBlank
    private String tableName;

    @NotBlank
    private String fileName;

    @NotBlank
    private String filePath;

    @NotNull
    public MultipartFile file;

}
