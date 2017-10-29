package com.company;
import com.company.BarcodeQuery;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Drug d;
        try {
            d = new BarcodeQuery("064541310790").GetDrug();
            System.out.println(d);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
