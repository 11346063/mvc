package tw.edu.ntub.imd.birc.firstmvc.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tw.edu.ntub.imd.birc.firstmvc.bean.BookBean;
import tw.edu.ntub.imd.birc.firstmvc.bean.TeacherBean;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.Book;
import tw.edu.ntub.imd.birc.firstmvc.service.BookService;
import tw.edu.ntub.imd.birc.firstmvc.service.TeacherService;
import tw.edu.ntub.imd.birc.firstmvc.util.http.BindingResultUtils;
import tw.edu.ntub.imd.birc.firstmvc.util.http.ResponseEntityBuilder;
import tw.edu.ntub.imd.birc.firstmvc.util.json.array.ArrayData;
import tw.edu.ntub.imd.birc.firstmvc.util.json.object.ObjectData;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/book")
public class BookController {
    private final BookService bookService;

    //新增
    @PostMapping(path = "/create")
    public ResponseEntity<String> createBook(@Valid @RequestBody BookBean bookBean,
                                                BindingResult bindingResult) {
        BindingResultUtils.validate(bindingResult);
        bookBean.setPublication_date(LocalDate.parse(bookBean.getPublication_date_str()));
//        System.out.println(bookBean.getId() + "\n" + bookBean.getName() + "\n"
//                + bookBean.getPublication_date() + "\n" + bookBean.getAuthonr_id() + "\n"
//               + bookBean.getCreate_time() + "\n");
        bookService.save(bookBean);
        return ResponseEntityBuilder.success()
                .message("新增成功")
                .build();
    }

    // 更新
    @PatchMapping(path = "/{id}")
    public ResponseEntity<String> updateScore(@RequestBody BookBean bookBean, @PathVariable Integer id) {

        bookBean.setPublication_date(LocalDate.parse(bookBean.getPublication_date_str()));
        bookService.update(id, bookBean);
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
////            objectData.add("author_name", bookBean.getAuthor().getName());
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
//        return ResponseEntity.ok(book);  // 回傳所有書籍
        for (Book book : bookService.findAll()) {
            ObjectData objectData = arrayData.addObject();
            objectData.add("id", book.getId());
            objectData.add("name", book.getName()); // 返回書名
            objectData.add("publication_Date", book.getPublication_date());
            objectData.add("author", book.getAuthor().getName());  // 返回作者名
        }
        return ResponseEntityBuilder.success()
                .message("查詢成功")
                .data(arrayData)
                .build();
    }
}
