package tw.edu.ntub.imd.birc.firstmvc.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tw.edu.ntub.imd.birc.firstmvc.bean.TeacherBean;
import tw.edu.ntub.imd.birc.firstmvc.service.TeacherService;
import tw.edu.ntub.imd.birc.firstmvc.util.http.BindingResultUtils;
import tw.edu.ntub.imd.birc.firstmvc.util.http.ResponseEntityBuilder;
import tw.edu.ntub.imd.birc.firstmvc.util.json.array.ArrayData;
import tw.edu.ntub.imd.birc.firstmvc.util.json.object.ObjectData;

import javax.validation.Valid;
import java.time.LocalDate;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping(path = "/create")
    public ResponseEntity<String> searchStudent(@Valid @RequestBody TeacherBean teacherBean,
                                                BindingResult bindingResult) {
        BindingResultUtils.validate(bindingResult);
        teacherBean.setDate(LocalDate.parse(teacherBean.getSDate()));
        teacherService.save(teacherBean);
        return ResponseEntityBuilder.success()
                .message("新增成功")
                .build();
    }

    @DeleteMapping(path = "/{sno}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Integer sno) {
        teacherService.delete(sno);
        return ResponseEntityBuilder.success()
                .message("刪除成功")
                .build();
    }

    @PatchMapping(path = "/{sno}")
    public ResponseEntity<String> updateScore(@RequestBody TeacherBean teacherBean, @PathVariable Integer sno) {

        teacherBean.setDate(LocalDate.parse(teacherBean.getSDate()));
        teacherService.update(sno, teacherBean);
        return ResponseEntityBuilder.success()
                .message("更新成功")
                .build();
    }

    @GetMapping(path = "", params = {"age"})
    public ResponseEntity<String> searchAllTeacherAge(@RequestParam(name = "age") Integer age) {
        ArrayData arrayData = new ArrayData();
        for (TeacherBean teacherBean : teacherService.searchAllByAge(age)) {
            ObjectData objectData = arrayData.addObject();
            objectData.add("name", teacherBean.getName());
            objectData.add("age", teacherBean.getAge());
            objectData.add("number", teacherBean.getNumber());
            objectData.add("t_id", teacherBean.getT_id());
        }
        return ResponseEntityBuilder.success()
                .message("查詢成功")
                .data(arrayData)
                .build();
    }

    @GetMapping(path = "/name", params = {"name"})
    public ResponseEntity<String> searchTeacherName(@RequestParam(name="name") String name){
        ArrayData arrayData = new ArrayData();
        for (TeacherBean teacherBean: teacherService.searchByName(name)){
            ObjectData objectData = arrayData.addObject();
            objectData.add("name", teacherBean.getName());
            objectData.add("age", teacherBean.getAge());
            objectData.add("number", teacherBean.getNumber());
            objectData.add("t_id", teacherBean.getT_id());
        }
        return ResponseEntityBuilder.success()
                .message("查詢成功")
                .data(arrayData)
                .build();
    }

    @GetMapping(path = "/age", params = {"startAge", "endAge"})
    public ResponseEntity<String> searchTeacherAge(@RequestParam(name="startAge") Integer startAge,
                                                   @RequestParam(name="endAge") Integer endAge){
        ArrayData arrayData = new ArrayData();
        for (TeacherBean teacherBean: teacherService.searchByAge(startAge, endAge)){
            ObjectData objectData = arrayData.addObject();
            objectData.add("name", teacherBean.getName());
            objectData.add("age", teacherBean.getAge());
            objectData.add("number", teacherBean.getNumber());
            objectData.add("t_id", teacherBean.getT_id());
        }
        return ResponseEntityBuilder.success()
                .message("查詢成功")
                .data(arrayData)
                .build();
    }


    @GetMapping(path = "", params = {"startDate", "endDate"})
    public ResponseEntity<String> searchTeacherDate(@RequestParam(name = "startDate") String startDate,
                                                    @RequestParam(name = "endDate") String endDate){
        ArrayData arrayData = new ArrayData();
        LocalDate SLDate = LocalDate.parse(startDate), ELDate = LocalDate.parse(endDate);
        for (TeacherBean teacherBean: teacherService.searchBydate(SLDate, ELDate)){
            ObjectData objectData = arrayData.addObject();
            objectData.add("name", teacherBean.getName());
            objectData.add("age", teacherBean.getAge());
            objectData.add("number", teacherBean.getNumber());
            objectData.add("t_id", teacherBean.getT_id());
        }
        return ResponseEntityBuilder.success()
                .message("查詢成功")
                .data(arrayData)
                .build();
    }
}
