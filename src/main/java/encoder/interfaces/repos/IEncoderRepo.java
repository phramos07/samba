package encoder.interfaces.repos;

import java.io.File;

public interface IEncoderRepo {
    void init();

    String store(File file);

    File load(String filename);

    String loadFilePath(String filename);
};