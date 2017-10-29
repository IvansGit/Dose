package com.company;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
/**
 * Created by alegaballo on 28/10/17.
 */
public class Person {
    private final String name;
    private final String family_name;
    private Date birthday;
    private String email;
    private String number;
    private List<Drug> drugs = new ArrayList<Drug>();


    public Person(String name, String family_name){
        this.name = name;
        this.family_name = family_name;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String addPrescription(Drug d) throws IOException {
        if (drugs.isEmpty()){
            drugs.add(d);
        }
        else{
            List<String> ndcs =  new ArrayList<String >();
            ndcs.add(d.getNdc());
            for(Drug dr:drugs){
                ndcs.add(dr.getNdc());
            }
            String params = String.join(",", ndcs.toArray(new String[0]));
            System.out.println(params);
            String conflict = sendRequest("https://api.drugbankplus.com/v1/ddi?ndc=", params);
            if(conflict.isEmpty()){
                drugs.add(d);
                return "";
            }
            return conflict; // maybe show a popup
        }
        return "";
    }

    public static String sendRequest(String uri, String params) throws IOException {
        String encoded_p = URLEncoder.encode(params, "UTF-8");
        URL uri_ = new URL(uri+encoded_p);
        HttpURLConnection con = (HttpURLConnection) uri_.openConnection();
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
            return jobs.getJSONArray("interactions").getJSONObject(0).get("description").toString();
        }
        return "";
    }

    public void removePrescription(Drug d){
        Iterator<Drug> it = drugs.iterator();
        while (it.hasNext()){
            if(it.equals(d)){
                it.remove();
            }
        }
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
