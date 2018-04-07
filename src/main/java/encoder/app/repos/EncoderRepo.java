package encoder.app.repos;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

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
	public File load(String filename) {

		File tmp = null;
		try {
			tmp = File.createTempFile("video", ".webm");
			InputStream in = this.amazonClient.downloadFile(filename);
			Files.copy(in, tmp.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return tmp;
	}

	@Override
	public String loadFilePath(String filename) {
		return this.amazonClient.getFilePath(filename);
	}
}