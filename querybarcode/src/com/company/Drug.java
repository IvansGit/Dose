package com.company;

/**
 * Created by alegaballo on 28/10/17.
 */
public class Drug {
    private String name;
    private String barcode;
    private Integer count;
    private final Integer full_value;
    private Integer dosage;
    private String ndc;

    public Drug(String name, Integer count, String barcode){
        this.name = name;
        this.count = count;
        this.barcode = barcode;
        this.full_value = count;
        this.dosage = 1;
    }

    public Drug(String name, Integer count, String barcode, Integer dosage){
        this(name,count, barcode);
        this.dosage=dosage;
    }

    public void TakeDosage(){count-=dosage;}
    public void Refill(){count=full_value;}
    public void Refill(Integer value){count=value;}
    public void SetDosage(Integer value){dosage=value;}
    public void SetNDC(String ndc){this.ndc=ndc};
    @Override
    public String toString() {
        return "Drug{" +
                "name='" + name + '\'' +
                ", barcode='" + barcode + '\'' +
                ", count=" + count +
                '}';
    }
}

