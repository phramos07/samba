package encoder.app.services;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class ZencoderUtil {
    private static String FULLACCESS_KEY = "ca5923adbe8a0d0d896212c4ca94e5c3";

    private static String READONLY_KEY = "5c2464cb6b472667979f90fb80028e2e";

    private static String S3REGION_BUCKET = "s3+us-west-1://phsamba/";

    private static String ZENCODER_JOBS_URL = "https://app.zencoder.com/api/v2/jobs";
 
    private String requestJSON(String inputFilename, String outputFilename) {
        try {
            String inputstr = S3REGION_BUCKET + inputFilename;
            String outputstr = S3REGION_BUCKET + outputFilename;

            JSONObject JSONinputbody = new JSONObject();
            JSONObject JSONoutputBody = new JSONObject();

            // Set API key with full access to Zencoder service.
            JSONinputbody.put("api_key", FULLACCESS_KEY);
            
            // Set input url from Amazon AWS.
            JSONinputbody.put("input", inputstr);
            // Set output configuration data
            JSONoutputBody.put("url", outputstr);
            JSONoutputBody.put("public", "true");

            JSONinputbody.put("output", JSONoutputBody);
            
            // return request JSON
            return JSONinputbody.toString();
        } catch(Exception e){
          e.printStackTrace();
          return null;
        }
    }

    public void encode(String inputFilename, String outputFilename) {
        try {
            // Create connection.
            URL url = new URL(ZENCODER_JOBS_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Setting Header
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            // Setting output and Sending
            connection.setDoOutput(true);
            DataOutputStream st = new DataOutputStream(connection.getOutputStream());

            String JSONrequestBody = this.requestJSON(inputFilename, outputFilename);

            st.writeBytes(JSONrequestBody);
            st.flush();
            st.close();
            connection.getResponseCode();
          } catch(Exception e){
            e.printStackTrace();
          }
    }

}