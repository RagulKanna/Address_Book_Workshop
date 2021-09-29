package com.AddressBook;

import com.opencsv.bean.CsvBindByName;

public class CSVUser {

    @CsvBindByName
    private String firstname;
    @CsvBindByName
    private String lastname;
    @CsvBindByName
    private String address;
    @CsvBindByName
    private String city;
    @CsvBindByName
    private String state;
    @CsvBindByName
    private int zip;
    @CsvBindByName
    private long phonenumber;
    @CsvBindByName
    private String email;

    @Override
    public String toString() {
        return "CSVUser{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip=" + zip +
                ", phonenumber=" + phonenumber +
                ", email='" + email + '\'' +
                '}';
    }
}
