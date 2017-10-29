package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.jsoup.Jsoup;
import org.json.JSONObject;
import com.company.Drug;

import javax.swing.plaf.synth.SynthDesktopIconUI;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by alegaballo on 28/10/17.
 */
public class BarcodeQuery {

    private String barcode;
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String GET_URL = "https://api.upcitemdb.com/prod/trial/lookup?upc=";
    private String drugName;
    private Drug drug;

    public BarcodeQuery(String code) throws IOException {
        barcode= code;
        drugName = sendGET(GET_URL + barcode.trim());
        drugName = removeDuplicate(drugName);
        Integer count = new Integer(0);

        if (!drugName.isEmpty()){
           for(String s: drugName.split(" ")){
               try{
                   count = Integer.parseInt(s);
                   System.out.println(count);
                   break;
               }
               catch (NumberFormatException e){}
            }
            String ndc = GetNDC(drugName);
            drug = new Drug(drugName.replace(count.toString(),""), count, barcode);
            System.out.println(ndc);
        }
        else{
            System.out.println("Product not found");
        }
    }

    public String DrugName(){
        return this.drugName;
    }

    public Drug GetDrug(){return drug;}

    private String GetNDC(String drugName) throws IOException {
        String[] split = drugName.split(" ");
        List<String> l = new ArrayList<String>();
        for(String s: split){
            l.add(s);
        }
        drugName = URLEncoder.encode(drugName, "UTF-8");
        for(int i=0; i< split.length;i++){
            String url = "https://api.drugbankplus.com/v1/us/drug_names?q="+drugName;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization","ec5f82842a00877930c6a6ff829ec48f");

            int responseCode = con.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                JSONObject jobs = new JSONObject(response.toString());
                return jobs.getJSONArray("products").getJSONObject(0).getJSONArray("ndc_product_codes").get(0).toString();
            }
            else{
                l.remove(split.length-1-i);
                drugName = URLEncoder.encode(String.join(" ", l.toArray(new String[0])), "UTF-8");
            }
        }
        return "";
    }

    private static String sendGET(String url) throws IOException {
        System.out.println(url);
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:56.0) Gecko/20100101 Firefox/56.0");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        System.out.println(con.getResponseMessage());
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
            JSONObject jobj = new JSONObject(response.toString());
            return jobj.getJSONArray("items").getJSONObject(0).getString("title");
            // return response.toString();
            // print result
            // System.out.println(response.toString());
        }

        else{
            System.out.println("GET request not worked");
            return "";
        }
    }

    private static String removeDuplicate(String str){
        String[] l = str.split(" ");
        ArrayList<String> l_s = new ArrayList<>();
        l_s.add(l[0]);
        for(int i=1; i < l.length;i++){
            if(l[i - 1].compareTo(l[i])!=0){
                l_s.add(l[i]);
            }
        }
        return String.join(" ",l_s.toArray(new String[0]));

    }
}
