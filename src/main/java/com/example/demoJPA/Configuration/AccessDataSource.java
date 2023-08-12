package com.example.demoJPA.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import com.healthmarketscience.jackcess.*;

import java.io.File;
import java.util.Date;

@Component
public class AccessDataSource {

    //change this
    private final String databasePath = "C:\\Users\\melusi\\Downloads\\RecycleSA.accdb";

    public  boolean Login(String password, String emailToSearch) {

        String tableName = "tblUsers"; // Replace with the actual table name
        boolean isFound = false;

        try {
            // Open the Access database
            Database db = DatabaseBuilder.open(new File(databasePath));
            // Get the table
            Table table = db.getTable(tableName);


            // Search for the record with the matching email
            for (Row row : table) {
                String email = row.getString("EmailAddress");
                String psswrd = row.getString("Password");
                if (    email != null
                        && email.equalsIgnoreCase(emailToSearch)
                        && psswrd!=null
                        && psswrd.equalsIgnoreCase(password)) {
                    isFound=true;
                    break; // Assuming there's only one record with the matching email
                }
            }

            // Close the database
            db.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return isFound;
    }

    public void insertUserRow(String userName, String userSName, String emailAddress, String phoneNumber, Date dob, String password) {
        try {
            // Open the Access database
            Database db = DatabaseBuilder.open(new File(databasePath));

            // Get the table
            Table table = db.getTable("tblUsers");

            // Create a new row
            table.addRow(Column.AUTO_NUMBER,userName,userSName,emailAddress,phoneNumber,dob,password);

            // Save the changes
            db.flush();

            // Close the database
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
