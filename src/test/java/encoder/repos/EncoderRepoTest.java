package encoder.repos;

import java.io.File;
import java.io.IOException;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import encoder.app.repos.EncoderRepo;

@SpringBootTest
@AutoConfigureMockMvc
public class EncoderRepoTest {

    private EncoderRepo repo;

    @Before
    public void init() {
        this.repo = new EncoderRepo();
    }

    @Test
    public void testUpload() {

        File file;
		try {
			file = File.createTempFile("video", ".webm");
            repo.store(file);
            assertThat(repo.store(file).toString().isEmpty()).isFalse();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @Test
    public void testLoadFilePath() {
        File file;
		try {
			file = File.createTempFile("video", ".webm");
            String url = repo.store(file);
            String path = repo.loadFilePath(url);
            assertThat(path.isEmpty()).isFalse();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
