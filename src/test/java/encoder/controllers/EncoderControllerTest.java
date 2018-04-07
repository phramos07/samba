package encoder.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import encoder.app.controllers.EncoderController;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EncoderControllerTest {

    @Autowired
    private EncoderController controller;

    @Test
    public void getIndex() throws Exception {
        assertThat(controller).isNotNull();
        assertThat(controller.index().equals("redirect:/upload")).isTrue();
    }

    @Test
    public void getUpload() throws Exception {
        assertThat(controller).isNotNull();
        assertThat(controller.index().equals("upload")).isTrue();
    }

    @Test
    public void getSorry() throws Exception {
        assertThat(controller).isNotNull();
        assertThat(controller.index().equals("sorry")).isTrue();
    }

    @Test
    public void getLoading() throws Exception {
        assertThat(controller).isNotNull();
        assertThat(controller.index().equals("loading")).isTrue();
    }
}
