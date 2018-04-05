package encoder.interfaces.services;

import java.io.File;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IEncoderService {
    void init();

    void store(MultipartFile file);

    File load();

    String loadLastUploadedFilePath();
};