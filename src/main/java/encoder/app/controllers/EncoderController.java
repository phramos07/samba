package encoder.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import encoder.interfaces.services.IEncoderService;

@Controller
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
}
