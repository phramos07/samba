package encoder.interfaces.repos;

import java.io.File;
import java.nio.file.Path;

public interface IEncoderRepo {
    void init();

    String store(File file);

    Path load(String filename);
    
};