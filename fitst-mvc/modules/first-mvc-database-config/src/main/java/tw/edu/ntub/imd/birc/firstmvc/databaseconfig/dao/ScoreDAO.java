package tw.edu.ntub.imd.birc.firstmvc.databaseconfig.dao;

import org.springframework.stereotype.Repository;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.Score;

import java.util.List;

@Repository
public interface ScoreDAO extends BaseDAO<Score, Integer> {
    /*
        動態查詢，對順序，如果條件很多可以使用@Query處理(SQL語法)
        ex: @Query("FROM Score s WHERE s.subject = :subject and s.score < :score")

        Query寫法團隊習慣:
        關鍵字大寫，並且一定在首行，關鍵字比如說: SELECT、FROM、AND等
     */
    List<Score> findBySubjectAndScoreLessThan(String subject, Integer score);
}
