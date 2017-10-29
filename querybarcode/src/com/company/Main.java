package com.company;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Drug d1, d2;
        Client p =  new Client("Dev", "Foo");
        d1 = new Drug("d1", 100, "123974123" );
        d2 = new Drug("d2", 200, "652451524" );
        d1.SetNDC("66715-9833");
        d2.SetNDC("0002-7140");
        try {
            p.addPrescription(d1);
            System.out.println(p.addPrescription(d2));
        } catch (IOException e) {
            e.printStackTrace();
        }



//        try {
//            d = new BarcodeQuery("064541310790").GetDrug();
//            System.out.println(d);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
