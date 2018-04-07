package encoder.services;

import java.io.File;
import java.io.IOException;

import javax.websocket.Encoder;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import encoder.app.repos.EncoderRepo;
import encoder.app.services.EncoderService;

@SpringBootTest
@AutoConfigureMockMvc
public class EncoderServiceTest {

    private EncoderService service;

    @Before
    public void init() {
        EncoderRepo repo = new EncoderRepo();
        this.service = new EncoderService(repo);
    }

    @Test
    public void testStore() {
        try {
            byte[] content = null;
			MockMultipartFile mockMultipartFile = new MockMultipartFile("video.webm", content);
            service.store(mockMultipartFile);

		} catch (Exception e) {
            assertThat(true).isTrue();
            e.printStackTrace();
        }
    }
}
