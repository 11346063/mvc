package tw.edu.ntub.imd.birc.firstmvc
.dto.file.uploader;

import org.springframework.lang.NonNull;
import tw.edu.ntub.imd.birc.firstmvc
.dto.file.directory.Directory;
import tw.edu.ntub.imd.birc.firstmvc
.exception.file.FileException;

@FunctionalInterface
public interface Uploader {
    @NonNull
    UploadResult upload(@NonNull Directory uploadTo) throws FileException;
}
