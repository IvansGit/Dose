package com.company;

import javax.print.Doc;
import java.util.List;

/**
 * Created by alegaballo on 29/10/17.
 */
public class Doctor {
    private String name;
    private String f_name;
    private String number;
    private List<Client> clients;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Doctor(String name, String f_name, String number) {

        this.name = name;
        this.f_name = f_name;
        this.number = number;
    }
}
