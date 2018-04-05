package encoder.interfaces.repos;

import java.io.File;
import java.nio.file.Path;

import org.springframework.core.io.Resource;

public interface IEncoderRepo {
    void init();

    String store(File file);

    File load(String filename);

    String loadFilePath(String filename);
};