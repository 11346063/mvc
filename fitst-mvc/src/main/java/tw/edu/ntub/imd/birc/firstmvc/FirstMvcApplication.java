package tw.edu.ntub.imd.birc.firstmvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tw.edu.ntub.imd.birc.firstmvc.util.http.ResponseUtils;

@SpringBootApplication
public class FirstMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstMvcApplication.class, args);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return ResponseUtils.createMapper();
    }

}
