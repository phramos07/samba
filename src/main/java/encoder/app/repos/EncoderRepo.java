package encoder.app.repos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import encoder.interfaces.repos.IEncoderRepo;

@Service
public class EncoderRepo implements IEncoderRepo {

    @Autowired
    public EncoderRepo() {}
}