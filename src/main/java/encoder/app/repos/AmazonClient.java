package encoder.app.repos;

import java.io.File;

import javax.annotation.PostConstruct;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@ConfigurationProperties
public class AmazonClient {
    private AmazonS3 s3Client;

    //Obs: I tried to make this configuration thing work but I had no success. So I just hardcoded
    //the credentials in here. I know it's terrible, sorry for that. I really had a hard time
    //understanding where I should put the configuration file and which annotation systems I should use.

    // @Value("${amazonProperties.endpointUrl}")
    private static String endpointUrl = "https://s3.us-east-2.amazonaws.com";
    
    // @Value("${amazonProperties.bucketName}")
    private static String bucketName = "phsamba";
    
    // @Value("${amazonProperties.accessKey}")
    private static String accessKey = "AKIAIMKOYOYHI25RNSCQ";
    
    // @Value("${amazonProperties.secretKey}")
    private static String secretKey = "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";
 
    public AmazonClient() {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        this.s3Client = new AmazonS3Client(credentials);
    }

    private void uploadFileTos3bucket(String fileName, File file) {
        try {
            s3Client.putObject(new PutObjectRequest(bucketName, fileName, file)
                    .withCannedAcl(CannedAccessControlList.PublicReadWrite));
        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
            		"means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
            		"means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
    }

    public String uploadFile(File file) {
        String fileUrl = "";
        try {
            fileUrl = endpointUrl + "/" + bucketName + "/" + file.getName() + ".webm";
            uploadFileTos3bucket(file.getName(), file);
            file.delete();
        } catch (Exception e) {
           e.printStackTrace();
        }
        return fileUrl;
    }
}