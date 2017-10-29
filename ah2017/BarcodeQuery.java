package damian.ah2017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.jsoup.Jsoup;
import damian.ah2017.Drug;

import java.net.HttpURLConnection;
import java.net.URL;
/**
 * Created by alegaballo on 28/10/17.
 */
public class BarcodeQuery {

    private String barcode;
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String GET_URL = "http://www.upcindex.com/";
    private String drugName;
    private Drug drug;

    public BarcodeQuery(String code) throws IOException {
        barcode= code;
        String result = sendGET(GET_URL + barcode);
        Integer count = new Integer(0);

        if (!result.isEmpty()){
           org.jsoup.nodes.Document doc =  Jsoup.parse(result);
           drugName = doc.body().getElementById("h2").text();
           for(String s: drugName.split(" ")){
               try{
                   count = Integer.parseInt(s);
                   System.out.println(count);
                   break;
               }
               catch (NumberFormatException e){}
            }
           drug = new Drug(drugName.replace(count.toString(),""), count, barcode);
        }
        else{
            System.out.println("Product not found");
        }
    }

    public String DrugName(){
        return this.drugName;
    }

    public Drug GetDrug(){return drug;}

    private static String sendGET(String url) throws IOException {
        System.out.println(url);
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:56.0) Gecko/20100101 Firefox/56.0");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_MOVED_PERM) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            if (responseCode == HttpURLConnection.HTTP_MOVED_PERM){
                return sendGET(con.getHeaderField("Location"));
            }
            return response.toString();
            // print result
            // System.out.println(response.toString());
        }

        else{
            System.out.println("GET request not worked");System.out.println("GET request not worked");
            return "";
        }
    }
}
