package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;

public class Main {

    public static void main(String[] args) throws IOException {
        String studentNr = "014100653";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "http://ohtustats.herokuapp.com/students/"+studentNr+"/submissions";

        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        client.executeMethod(method);

        InputStream stream =  method.getResponseBodyAsStream();

        String bodyText = IOUtils.toString(stream);

        //System.out.println("json-muotoinen data:");
        //System.out.println( bodyText );

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);

        //System.out.println("Oliot:");
        System.out.println("opiskelijanumero: " + subs[0].getStudent_number() + "\n");
        
        int total = 0;
        int hours = 0;
        
        for (Submission submission : subs) {
            System.out.println(submission);
            total = total + submission.get_total_exe();
            hours = hours + submission.get_hours();
        }
        
        System.out.print("\nyhteensä: " + total + " tehtävää " + hours + " tuntia");
    }
}
