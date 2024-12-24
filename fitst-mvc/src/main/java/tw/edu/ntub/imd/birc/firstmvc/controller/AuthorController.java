package tw.edu.ntub.imd.birc.firstmvc.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tw.edu.ntub.imd.birc.firstmvc.bean.AuthorBean;
import tw.edu.ntub.imd.birc.firstmvc.service.AuthorService;
import tw.edu.ntub.imd.birc.firstmvc.util.http.BindingResultUtils;
import tw.edu.ntub.imd.birc.firstmvc.util.http.ResponseEntityBuilder;
import tw.edu.ntub.imd.birc.firstmvc.util.json.array.ArrayData;
import tw.edu.ntub.imd.birc.firstmvc.util.json.object.ObjectData;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/author")
public class AuthorController {
    private final AuthorService authorService;

    public String DateToString (Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateFormat.format(date);
    }
    //新增
    @PostMapping
    public ResponseEntity<String> createAuthor(@Valid @RequestBody AuthorBean authorBean,
                                                BindingResult bindingResult) {
        BindingResultUtils.validate(bindingResult);

//        authorBean.setBirthdate(LocalDate.parse(authorBean.getBirthdateS()));
        authorService.save(authorBean);
        return ResponseEntityBuilder.success()
                .message("新增成功")
                .build();
    }

    // 更新
    @PatchMapping(path = "/{id}")
    public ResponseEntity<String> updateScore(@RequestBody AuthorBean authorBean, @PathVariable Integer id) {

        //        authorBean.setBirthdate(LocalDate.parse(authorBean.getBirthdateS()));
        authorService.update(id, authorBean);
        return ResponseEntityBuilder.success()
                .message("更新成功")
                .build();
    }

    //刪除
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Integer id) {
        authorService.delete(id);
        return ResponseEntityBuilder.success()
                .message("刪除成功")
                .build();
    }

    //取得全部
    @GetMapping
    public ResponseEntity<String> getAll() {
        ArrayData arrayData = new ArrayData();
        for (AuthorBean authorBean : authorService.findAll()) {
            ObjectData objectData = arrayData.addObject();
            objectData.add("id", authorBean.getId());
            objectData.add("name", authorBean.getName());
            objectData.add("birthDate", DateToString(authorBean.getBirthdate()));
            objectData.add("create_time", authorBean.getCreate_time());
        }
        return ResponseEntityBuilder.success()
                .message("查詢成功")
                .data(arrayData)
                .build();
    }

    @GetMapping(params = {"id"})
    public ResponseEntity<String> findById(@RequestParam(name = "id") Integer id) {
        ArrayData arrayData = new ArrayData();
        for (AuthorBean authorBean : authorService.findId(id)) {
            ObjectData objectData = arrayData.addObject();
            objectData.add("id", authorBean.getId());
            objectData.add("name", authorBean.getName());
            objectData.add("info", authorBean.getInfo());
            objectData.add("birthDate", DateToString(authorBean.getBirthdate()));
        }
        return ResponseEntityBuilder.success()
                .message("查詢成功")
                .data(arrayData)
                .build();
    }
}
