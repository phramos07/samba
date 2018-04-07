package encoder.app.controllers;

import java.lang.ProcessBuilder.Redirect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import encoder.interfaces.services.IEncoderService;

@Controller
// @Configuration
// @EnableAutoConfiguration
@EnableConfigurationProperties
public class EncoderController {
    
    private IEncoderService _encoderService;

    @Autowired
    public EncoderController(IEncoderService encoderService) {
        this._encoderService = encoderService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/upload";
    }

    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    @GetMapping("/sorry")
    public String sorry() {
        return "sorry";
    }

    @GetMapping("/loading")
    public String loading() {
        return "loading";
    }

    @PostMapping("/uploadVideo")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, Model model) {
        if (file == null || file.isEmpty())
            return "redirect:/sorry";
        
        try {
            this._encoderService.store(file);
            redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

            return "redirect:/stream";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/sorry";
        }
    }

    @GetMapping("/stream")
    public String watchVideo(Model model) {
        String link = _encoderService.loadLastUploadedFilePath();
        model.addAttribute("source", link);

        return "stream";
    }

}
