package tw.edu.ntub.imd.birc.firstmvc.dto.file.io;

import java.util.List;
import java.util.stream.Stream;

public interface ReadableFile {
    Stream<String> readLinesWithStream();

    List<String> readLinesWithList();
}
