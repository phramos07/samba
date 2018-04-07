package encoder.interfaces.services;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import encoder.app.services.EncoderException;

public interface IEncoderService {
    void init();

    void store(MultipartFile file) throws EncoderException, IOException;

    File load();

    String loadLastUploadedFilePath();
};