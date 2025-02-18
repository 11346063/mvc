package tw.edu.ntub.imd.birc.firstmvc.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tw.edu.ntub.imd.birc.firstmvc.bean.UploadFileBean;
import tw.edu.ntub.imd.birc.firstmvc.service.BookService;
import tw.edu.ntub.imd.birc.firstmvc.service.UploadFileService;
import tw.edu.ntub.imd.birc.firstmvc.util.http.ResponseEntityBuilder;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/upload")
public class UploadFileController {
    private final BookService bookService;
    private final UploadFileService uploadFileService;

//    @PostMapping(path = "/{aSno}")
//    public ResponseEntity<String> uploadFile(@PathVariable(name = "aSno") Integer aSno, MultipartFile[] files) {
//        for(MultipartFile file : files) {
//            UploadFileBean uploadBean = new UploadFileBean();
//            uploadBean.setTableNo(aSno);
//            uploadBean.setTableName("");
//            uploadBean.setFile(file);
//            uploadBean.setFileName(file.getOriginalFilename());
//            uploadFileService.save(uploadBean);
//        }
//        return ResponseEntityBuilder.success().message("上傳成功").build();
//    }
    @PostMapping(path = "")
    public ResponseEntity<String> upload(@Valid @RequestBody UploadFileBean uploadFileBean,
                                         BindingResult bindingResult) {
        MultipartFile file = uploadFileBean.getFile();
        uploadFileService.save(uploadFileBean);
        return ResponseEntityBuilder.success()
                .message("上傳成功")
                .build();
    }
}
