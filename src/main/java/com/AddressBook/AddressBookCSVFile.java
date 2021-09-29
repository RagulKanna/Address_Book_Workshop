package com.AddressBook;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.util.Iterator;

public class AddressBookCSVFile {

    public static File addressbook_csv = new File("AddressBook.csv");


    public static void createfile() {
        try {
            addressbook_csv.createNewFile();
            System.out.println("\nEmpty File is created successfully....");
        } catch (IOException e) {
            System.out.println("" + e);
        }
    }

    public static void write_csvfile() throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

        try {
            FileWriter outputfile = new FileWriter(addressbook_csv);

            StatefulBeanToCsv<Contact> beanToCsv = new StatefulBeanToCsvBuilder<Contact>(outputfile)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            beanToCsv.write(AddressBookMain.contactList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read_csvfile() throws IOException {

        try {
            FileReader readfile = new FileReader(addressbook_csv);

            CsvToBean<CSVUser> csvToBean = new CsvToBeanBuilder<CSVUser>(readfile)
                    .withType(CSVUser.class)
                    .build();

            Iterator<CSVUser> csvUserIterator = csvToBean.iterator();

            while (csvUserIterator.hasNext()) {
                CSVUser csvUser = new csvUserIterator.next();
                System.out.println("\nFirstName - " + csvUser.getFirstname() +
                        "\nLastname -  " + csvUser.getLastname() +
                        "\nAddress -  " + csvUser.getAddress() +
                        "\nCity -  " + csvUser.getCity() +
                        "\nState -  " + csvUser.getState() +
                        "\nZip -  " + csvUser.getZip() +
                        "\nEmail -  " + csvUser.getEmail());
            }
        } catch (FileNotFoundException | IllegalStateException e) {
            e.printStackTrace();
        }
    }
}