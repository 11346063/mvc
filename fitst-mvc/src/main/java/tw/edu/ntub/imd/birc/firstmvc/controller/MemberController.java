package tw.edu.ntub.imd.birc.firstmvc.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tw.edu.ntub.imd.birc.firstmvc.bean.MemberBean;
import tw.edu.ntub.imd.birc.firstmvc.bean.ScoreBean;
import tw.edu.ntub.imd.birc.firstmvc.service.MemberService;
import tw.edu.ntub.imd.birc.firstmvc.service.ScoreService;
import tw.edu.ntub.imd.birc.firstmvc.util.http.BindingResultUtils;
import tw.edu.ntub.imd.birc.firstmvc.util.http.ResponseEntityBuilder;
import tw.edu.ntub.imd.birc.firstmvc.util.json.array.ArrayData;
import tw.edu.ntub.imd.birc.firstmvc.util.json.object.ObjectData;

import javax.validation.Valid;
import java.util.Optional;

/*
題目:
1. 查詢全部學生
2. 查PK，學生
3. 條件查詢(某個科目成績不及格的學生名單)
4. 新增成績
5. 修改成績
6. 刪除成績
 */

/*
1. 需有lombok
2. 環境變數log.path
3. 複製application-server.yml改名為application-local.yml，增加資料庫相關資料
4. 如果不知道怎麼下手，可以先把相關的檔案建完再照著順序走

建檔順序: entity => bean => DAO => service => controller
撰寫順序: Controller => Service => DAO(習慣反過來寫)
*/

/*
HTTP method:
GET查詢: url可以帶parameter
POST新增
PATCH更新
DELETE刪除

url命名方式:
通常我們會以本次查詢目的來去命名，並且會配以HTTP method來去做解讀
以下舉例
新增學生: POST /student
編輯學生資料: PATCH /student
查詢單個學生: GET /student
查詢學生列表: GET /student/list
 */

// 建立所有參數的建構元
@AllArgsConstructor
// 告訴spring這是一個controller
@RestController
// 定義url，url以/student開頭的都會進到這裡
@RequestMapping(path = "/student")
// 建構controller會用到要使用的service
public class MemberController {
    private final MemberService memberService;
    private final ScoreService scoreService;

    // 查詢使用get，url為/student的會進入這裡
    @GetMapping(path = "")
    // 回傳多筆用search、單筆get(團隊習慣)
    public ResponseEntity<String> searchStudent() {
        // JSON: JavaScript 物件的標準格式
        // [] => array、 {} => object
        ArrayData arrayData = new ArrayData();
        // baseService有的searchAll方法 (Service父類別，裡面有很多基礎方法可以使用)
        for (MemberBean memberBean : memberService.searchAll()) {
            ObjectData objectData = arrayData.addObject();
            objectData.add("id", memberBean.getId());
            objectData.add("name", memberBean.getName());
            // 三元運算子
            objectData.add("gender", memberBean.getGender().equals("0") ? "男" : "女");
        }
        // 鏈式，做完一個function後回傳自己，繼續執行下個方法
        return ResponseEntityBuilder.success()
                .message("查詢成功")
                /*
                  arrayData內容:
                  [{
                    id: "id",
                    name: "name",
                    gender: "男",
                  }]
                 */
                .data(arrayData)
                .build();
    }

    // url如果一樣，但是參數(parameter)不一樣可以這樣寫，增加進入條件
    // params: url參數(遇到path一樣才要寫，平常寫@RequestPram就可以了)
    @GetMapping(path = "", params = {"id"})
    // @RequestParam，/url?id=XXX&name=OOO，?後面為請求參數(parameter)，id、name為參數名、OOOXXX為參數值
    // ※此方法url只有這樣/student/url?id=XXX
    public ResponseEntity<String> getStudent(@RequestParam(name = "id") String id) {
        ObjectData objectData = new ObjectData();
        // Optional，避免null造成程式壞掉，所以會先用optional包裝，最後要用的時候再檢查
        // getById如果有其他的操作Service就要覆寫，但是這裡為了展示就使用父類別的方法不覆寫
        Optional<MemberBean> memberBeanOptional = memberService.getById(id);
        /*
            1. isPresent()
            2. orElseThrow() (throw new RuntimeException())
         */
        memberBeanOptional.orElseThrow(() -> new RuntimeException("查無此學生，請確認學號是否正確"));
        MemberBean memberBean = memberBeanOptional.get();
        objectData.add("id", memberBean.getId());
        objectData.add("name", memberBean.getName());
        objectData.add("gender", memberBean.getGender().equals("0") ? "男" : "女");
        return ResponseEntityBuilder.success()
                .message("查詢成功")
                .data(objectData)
                .build();
    }

    @GetMapping(path = "/failed")
    public ResponseEntity<String> searchFailedStudent(@RequestParam(name = "subject") String subject) {
        ArrayData arrayData = new ArrayData();
        for (ScoreBean scoreBean : scoreService.searchFailedStudentBySubject(subject)) {
            ObjectData objectData = arrayData.addObject();
            objectData.add("studentId", scoreBean.getStudentId());
            objectData.add("studentName", scoreBean.getStudentName());
            objectData.add("score", scoreBean.getScore());
        }
        return ResponseEntityBuilder.success()
                .message("查詢成功")
                .data(arrayData)
                .build();
    }

    @PostMapping(path = "/score")
    /*
        @RequestBody，在body裡面取資料(get在url上)，只可以使用一個參數接，前端要用json的格式
        @Valid，驗證傳入參數
        必傳BindingResult bindingResult
     */
    public ResponseEntity<String> createScore(@Valid @RequestBody ScoreBean scoreBean,
                                              BindingResult bindingResult) {
        //表單驗證一定要寫
        BindingResultUtils.validate(bindingResult);
        scoreService.save(scoreBean);
        return ResponseEntityBuilder.success()
                .message("新增成功")
                .build();
    }

    //不同的請求方式(HTTP method)可以使用同個url
    @PatchMapping(path = "/score")
    public ResponseEntity<String> updateScore(@RequestBody ScoreBean scoreBean) {
        scoreService.update(scoreBean.getId(), scoreBean);
        return ResponseEntityBuilder.success()
                .message("更新成功")
                .build();
    }

    //url動態帶編號(不是&參數)
    @DeleteMapping(path = "/score/{id}")
    public ResponseEntity<String> deleteScore(@PathVariable Integer id) {
        /*
            有兩種寫法，如果資料表中有啟用不啟用時，刪除通常指的是調整成不啟用(update)
            第二種是直接刪除，這裡用這種
         */
        scoreService.delete(id);
        return ResponseEntityBuilder.success()
                .message("刪除成功")
                .build();
    }
}