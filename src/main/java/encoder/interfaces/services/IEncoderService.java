package encoder.interfaces.services;

import java.nio.file.Path;

import org.springframework.web.multipart.MultipartFile;

public interface IEncoderService {
    void init();

    void store(MultipartFile file);

    Path load(String filename);
};