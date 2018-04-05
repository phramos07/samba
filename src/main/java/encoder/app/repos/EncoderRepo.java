package encoder.app.repos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import encoder.interfaces.repos.IEncoderRepo;

@Service
@EnableConfigurationProperties
public class EncoderRepo implements IEncoderRepo {
	
	private final AmazonClient amazonClient;
	
	@Override
	public void init() {}
	
    @Autowired
    public EncoderRepo() {
		amazonClient = new AmazonClient();
	}

	@Override
	public String store(File file) {
		return this.amazonClient.uploadFile(file);
	}

	@Override
	public Path load(String filename) {
		return null;
	}
}