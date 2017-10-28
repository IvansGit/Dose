package com.company;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
/**
 * Created by alegaballo on 28/10/17.
 */
public class Person {
    private final String name;
    private final String family_name;
    private Date birthday;
    private String email;
    private String number;
    private List<Drug> drugs;


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

    public void addPrescription(Drug d){
        drugs.add(d);
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
