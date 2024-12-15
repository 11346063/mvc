# 範例MVC檔

## 撰寫人員

|  姓名  | 最後修改日      |
|--------|------------|
|  鄭芷晴  | 2022/12/15 |

## 事前準備
一個專案做過之後就不用再做
1. 下載JDK，設置JAVA HOME
2. 環境變數設定log.path
3. IntelliJ IDEA下載Plugins: Lombok(紅色辣椒圖案)


## 啟動步驟
每次拿到新專案都要做
1. 匯入資料庫: MySQL > create schemas > data import選擇專案根目錄的student.sql
2. 新增本地設定： /src/resources > 複製一份application-local-example.yml > 名字修改為application-local.yml > 把{}包著的參數修改成本地資料
3. 修改database config: /modules/first-mvc-database-config/.../databaseconfig > Config.java修改DATABASE_NAME = "資料庫名稱"
4. 啟動伺服

## 名詞解釋
* Bean: 前端與後端資料交換會放置的地方
  * 這裡不會出現Entity類別
* Controller: 定義url，利用url接收前端請求
* Service: 處理商業邏輯，向DAO要資料
  * transformer: 用來轉換bean跟entity
* DAO: 處理SQL相關事情，與資料庫拿資料
* Entity: 後端與資料庫資料交換會放置的地方
  * 資料表的欄位如果後端會用到，就會寫在裡面 
  * 內容物會跟bean很相似

## 注意事項
* 請由/src/main/java/.../controller/MemberController.java開始閱讀
* 平常會使用到的檔案寫有註解，若沒有註解且非上面名詞解釋有出現的，則可以不用閱讀
* 使用Ctrl + 滑鼠左鍵可以點進任一物件