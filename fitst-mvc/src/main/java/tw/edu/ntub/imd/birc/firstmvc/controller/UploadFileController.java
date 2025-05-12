package tw.edu.ntub.imd.birc.firstmvc.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tw.edu.ntub.imd.birc.firstmvc.bean.UploadFileBean;
import tw.edu.ntub.imd.birc.firstmvc.dto.file.uploader.MultipartFileUploader;
import tw.edu.ntub.imd.birc.firstmvc.dto.file.uploader.UploadResult;
import tw.edu.ntub.imd.birc.firstmvc.service.BookService;
import tw.edu.ntub.imd.birc.firstmvc.service.UploadFileService;
import tw.edu.ntub.imd.birc.firstmvc.util.http.ResponseEntityBuilder;
import tw.edu.ntub.imd.birc.firstmvc.util.json.array.ArrayData;
import tw.edu.ntub.imd.birc.firstmvc.util.json.object.ObjectData;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/upload")
public class UploadFileController {
    private final BookService bookService;
    private final UploadFileService uploadFileService;
    private final MultipartFileUploader multipartFileUploader;


    @PostMapping(path = "test")
    public  ResponseEntity<String> test(@Valid MultipartFile[] files) {
        for(MultipartFile file : files){
            UploadResult uploadResult = multipartFileUploader.upload(file, "image");

        }
        return ResponseEntityBuilder.success().message("上傳成功").build();

    }

    @PostMapping
    public ResponseEntity<String> uploadFile(MultipartFile[] files , Integer tableNo, String  tableName) {
        for(MultipartFile file : files) {
            UploadFileBean uploadBean = new UploadFileBean();
            uploadBean.setTableNo(tableNo);
            uploadBean.setTableName(tableName);
            uploadBean.setFile(file);
            uploadBean.setFileName(file.getOriginalFilename());
            uploadFileService.save(uploadBean);
        }
        return ResponseEntityBuilder.success().message("上傳成功").build();
    }

    @GetMapping(path = "/search", params = {"tableNo", "tableName"})
    public ResponseEntity<String> getFiles(@RequestParam(name = "tableNo") Integer tableNo,
                                           @RequestParam(name = "tableName") String tableName) {
        ArrayData arrayData = new ArrayData();
        for (UploadFileBean uploadFileBean : uploadFileService.searchFiles(tableNo, tableName)){
            ObjectData objectData = arrayData.addObject();
            objectData.add("name", uploadFileBean.getFileName());
            objectData.add("path", uploadFileBean.getFilePath());
        }

        return ResponseEntityBuilder.success()
                .message("查詢成功")
                .data(arrayData)
                .build();
    }
}

