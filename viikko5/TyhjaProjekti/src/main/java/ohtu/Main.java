package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;

public class Main {

    public static void main(String[] args) throws IOException {
        String studentNr = "014152902";
        if (args.length > 0) {
            studentNr = args[0];
        }

        String url = "http://ohtustats.herokuapp.com/students/" + studentNr + "/submissions";

        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        client.executeMethod(method);

        InputStream stream = method.getResponseBodyAsStream();

        String bodyText = IOUtils.toString(stream);

//        System.out.println("json-muotoinen data:");
//        System.out.println(bodyText);

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);

        int kokonaisaika = 0;
        int tehtyjenLKM = 0;

        System.out.println("Opiskelijanumero: " + studentNr + "\n");
        for (Submission submission : subs) {
            kokonaisaika += Integer.parseInt(submission.getHours());
            tehtyjenLKM += submission.getTotal();
            System.out.println(submission);
        }

        System.out.println("");
        System.out.println("Yhteensä: " + tehtyjenLKM + " tehtävää, " + kokonaisaika + " tuntia");
    }
}
