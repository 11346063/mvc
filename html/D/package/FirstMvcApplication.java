package tw.edu.ntub.imd.birc.firstmvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tw.edu.ntub.imd.birc.firstmvc.config.properties.FileProperties;
import tw.edu.ntub.imd.birc.firstmvc.dto.file.directory.Directory;
import tw.edu.ntub.imd.birc.firstmvc.dto.file.directory.DirectoryImpl;
import tw.edu.ntub.imd.birc.firstmvc.dto.file.uploader.MultipartFileUploader;
import tw.edu.ntub.imd.birc.firstmvc.util.http.ResponseUtils;

import java.nio.file.Paths;

@SpringBootApplication
public class FirstMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstMvcApplication.class, args);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return ResponseUtils.createMapper();
    }

    @Bean("fileDirectory")
    public Directory fileDirectory(FileProperties fileProperties) {
        return new DirectoryImpl(Paths.get(fileProperties.getPath()));
    }

    @Bean
    public MultipartFileUploader multipartFileUploader(Directory fileDirectory, FileProperties fileProperties) {
        return new MultipartFileUploader(fileDirectory, fileProperties.getUrl());
    }

}
