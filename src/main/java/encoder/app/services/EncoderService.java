package encoder.app.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import encoder.interfaces.repos.IEncoderRepo;
import encoder.interfaces.services.IEncoderService;

@Service
public class EncoderService implements IEncoderService {

    private IEncoderRepo _encoderRepo;
	
	private String lastUploadedVideo;

	private ZencoderUtil zencoder;

	private static String encodedSuffix = "-ENCODED.webm";

    @Autowired
    public EncoderService(IEncoderRepo encoderRepo) {
		this._encoderRepo = encoderRepo;
		this.lastUploadedVideo = "";
		this.zencoder = new ZencoderUtil();
    }

	public String getLastUploadedVideo() {
		return lastUploadedVideo;
	}

	@Override
	public void init() {}

	@Override
	public void store(MultipartFile file) {
		try {
			this.lastUploadedVideo = _encoderRepo.store(this.convertMultiPartToFile(file));
		} catch (IOException e) {
			e.printStackTrace();
		}

		zencoder.encode(this.lastUploadedVideo, this.lastUploadedVideo + encodedSuffix);
	}

	@Override
	public File load() {
		return this._encoderRepo.load(this.lastUploadedVideo);
	}

	private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(generateFileName(file));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private String generateFileName(MultipartFile file) {
        return new Date().getTime() + "-" + file.getOriginalFilename().replace(" ", "_");
    }

	@Override
	public String loadLastUploadedFilePath() {
		return this._encoderRepo.loadFilePath(this.lastUploadedVideo + encodedSuffix);
	}
}