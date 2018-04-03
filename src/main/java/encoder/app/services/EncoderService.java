package encoder.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import encoder.interfaces.repos.IEncoderRepo;
import encoder.interfaces.services.IEncoderService;

@Service
public class EncoderService implements IEncoderService {

    private IEncoderRepo _encoderRepo;
    
    @Autowired
    public EncoderService(IEncoderRepo encoderRepo) {
        this._encoderRepo = encoderRepo;
    }

}