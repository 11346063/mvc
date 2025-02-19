package tw.edu.ntub.imd.birc.firstmvc.dto.file.directory;

import lombok.experimental.UtilityClass;
import tw.edu.ntub.imd.birc.firstmvc.exception.file.NotDirectoryException;
import tw.edu.ntub.imd.birc.firstmvc
.util.file.FileUtils;

import java.nio.file.Path;

@UtilityClass
public class DirectoryFactory {
    public Directory create(Path path) {
        if (FileUtils.isNotDirectory(path)) {
            throw new NotDirectoryException(path);
        }
        return new DirectoryImpl(path);
    }
}
