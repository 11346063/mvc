package tw.edu.ntub.imd.birc.firstmvc.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tw.edu.ntub.imd.birc.firstmvc.bean.AuthorBean;
import tw.edu.ntub.imd.birc.firstmvc.bean.BookBean;
import tw.edu.ntub.imd.birc.firstmvc.bean.UploadFileBean;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.dao.AuthorDAO;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.dao.BookDAO;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.Author;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.Book;
import tw.edu.ntub.imd.birc.firstmvc.service.BookService;
import tw.edu.ntub.imd.birc.firstmvc.service.UploadFileService;
import tw.edu.ntub.imd.birc.firstmvc.util.http.BindingResultUtils;
import tw.edu.ntub.imd.birc.firstmvc.util.http.ResponseEntityBuilder;
import tw.edu.ntub.imd.birc.firstmvc.util.json.array.ArrayData;
import tw.edu.ntub.imd.birc.firstmvc.util.json.object.ObjectData;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/book")
public class BookController {
    private final BookService bookService;
    private final AuthorDAO authorDAO;
    private final UploadFileController uploadFileController;
    private final UploadFileService uploadFileService;

    public String DateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateFormat.format(date);
    }

    //新增
    @PostMapping
    public ResponseEntity<String> createBook(@Valid BookBean bookBean,
                                             BindingResult bindingResult,
                                             MultipartFile[] files) {
//        MultipartFile[] files = bookBean.getFiles();
        BindingResultUtils.validate(bindingResult);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        bookBean.setPublication_date(LocalDate.parse(bookBean.getPublication_date_str(), formatter));
        BookBean book = bookService.save(bookBean);
        System.out.println(book);
        for (MultipartFile file : files) {
            UploadFileBean uploadBean = new UploadFileBean();
            uploadBean.setTableNo(book.getId());
            uploadBean.setTableName("book");
            uploadBean.setFile(file);
            uploadBean.setFileName(file.getOriginalFilename());
            uploadFileService.save(uploadBean);
        }
        return ResponseEntityBuilder.success()
                .message("新增成功")
                .build();
    }

    // 更新
//    @PatchMapping(path = "/{id}")
//    public ResponseEntity<String> updateBook(@RequestBody BookBean bookBean, @PathVariable Integer id) {
//

    /// /        bookBean.setPublication_date(LocalDate.parse(bookBean.getPublication_date_str()));
//        bookService.update(id, bookBean);
//        return ResponseEntityBuilder.success()
//                .message("更新成功")
//                .build();
//    }
    @PatchMapping(path = {"/{sno}"})
    public ResponseEntity<String> updateBookFlies(@PathVariable(name = "sno") Integer sno,
                                                  @Valid BookBean bookBean,
                                                  BindingResult bindingResult,
                                                  MultipartFile[] files
    ) {
        // @RequestParam(name = "id") Integer id,) {
        BindingResultUtils.validate(bindingResult);
        bookBean.setId(sno);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        bookBean.setPublication_date(LocalDate.parse(bookBean.getPublication_date_str(), formatter));
        // Integer id = bookBean.getId();
        bookService.update(sno, bookBean);
        if (files.length != 0) {
            for (UploadFileBean file : uploadFileService.searchFiles(sno, "book")) {
                uploadFileService.delete(file.getId());
            }
        }
        for (MultipartFile file : files) {
            UploadFileBean uploadBean = new UploadFileBean();
            uploadBean.setTableNo(sno);
            uploadBean.setTableName("book");
            uploadBean.setFile(file);
            uploadBean.setFileName(file.getOriginalFilename());
            uploadFileService.save(uploadBean);
        }
        return ResponseEntityBuilder.success()
                .message("更新成功")
                .build();
    }

    //刪除
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Integer id) {
        bookService.delete(id);
        return ResponseEntityBuilder.success()
                .message("刪除成功")
                .build();
    }

    //取得全部
//    @GetMapping(path = "")
//    public ResponseEntity<String> getAll() {
//        ArrayData arrayData = new ArrayData();
//        for (BookBean bookBean : bookService.findAll()) {
//            ObjectData objectData = arrayData.addObject();
//            objectData.add("id", bookBean.getId());
//            objectData.add("name", bookBean.getName());
//            objectData.add("publication_date", (bookBean.getPublication_date()));
//            objectData.add("author_id", bookBean.getAuthonr_id());
//            objectData.add("create_time", bookBean.getCreate_time());

    /// /            objectData.add("author_name", bookBean.getAuthor().getName());
//        }
//        return ResponseEntityBuilder.success()
//                .message("查詢成功")
//                .data(arrayData)
//                .build();
//    }
    @GetMapping
    public ResponseEntity<String> getAllBooks() {
//        List<Book> book = bookService.findAll();
        ArrayData arrayData = new ArrayData();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

//        return ResponseEntity.ok(book);  // 回傳所有書籍
        for (BookBean bookBean : bookService.findAll()) {
            ObjectData objectData = arrayData.addObject();
            objectData.add("id", bookBean.getId());


            objectData.add("name", bookBean.getName()); // 返回書名
            objectData.add("publicationDate", bookBean.getPublication_date().format(formatter));
            objectData.add("authorId", bookBean.getAuthor_id());
            objectData.add("authorName", bookBean.getAuthor().getName());// 返回作者名
            objectData.add("authorDescription", bookBean.getAuthor().getInfo());
            objectData.add("birthDate", DateToString(bookBean.getAuthor().getBirthdate()));
        }
        return ResponseEntityBuilder.success()
                .message("查詢成功")
                .data(arrayData)
                .build();
    }

    @GetMapping(params = {"id"})
    public ResponseEntity<String> findById(@RequestParam(name = "id") Integer id) {
//        ArrayData arrayData = new ArrayData();
//        for (BookBean bookBean : bookService.findId(id)) {
//            ObjectData objectData = arrayData.addObject();
//            objectData.add("id", bookBean.getId());
//            objectData.add("name", bookBean.getName());
//            objectData.add("info", bookBean.getInfo());
//            objectData.add("publicationDate", bookBean.getPublication_date().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//            objectData.add("authorName", bookBean.getAuthor().getName());
//            objectData.add("authorId", bookBean.getAuthor().getId());
//        }
        BookBean bookBean = bookService.findId(id).get(0);
        ObjectData objectData = new ObjectData();         // 創 key, value
        objectData.add("id", bookBean.getId());
        objectData.add("name", bookBean.getName());
        objectData.add("info", bookBean.getInfo());
        objectData.add("publicationDate", bookBean.getPublication_date().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        objectData.add("authorName", bookBean.getAuthor().getName());
        objectData.add("authorId", bookBean.getAuthor().getId());

        ArrayData files = new ArrayData();  // 建立list
        for (UploadFileBean uploadFileBean : uploadFileService.searchFiles(id, "book")) {
            ObjectData objectData1 = files.addObject();    // 將下面objectData add 進 Array
            objectData1.add("fileName", uploadFileBean.getFileName());
            objectData1.add("filePath", uploadFileBean.getFilePath());
        }
        objectData.add("uploadFileBeanList", files);

        return ResponseEntityBuilder.success()
                .message("查詢成功")
                .data(objectData)
                .build();
    }
}
