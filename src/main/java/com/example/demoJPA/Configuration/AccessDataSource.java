package com.example.demoJPA.Configuration;

import com.example.demoJPA.Models.RecyclingFacts;
import com.example.demoJPA.Models.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import com.healthmarketscience.jackcess.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

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
            var d = table.addRow(Column.AUTO_NUMBER,userName,userSName,emailAddress,phoneNumber,dob,password);

            // Save the changes
            db.flush();

            // Close the database
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User findUser(String emailToSearch){
        String tableName = "tblUsers"; // Replace with the actual table name

         User user = null;
        try {
            // Open the Access database
            Database db = DatabaseBuilder.open(new File(databasePath));
            // Get the table
            Table table = db.getTable(tableName);


            // Search for the record with the matching email
            for (Row row : table) {
                String email = row.getString("EmailAddress");
                String name = row.getString("UserName");
                if (    email != null
                        && email.equalsIgnoreCase(emailToSearch)) {
                    user = new User();
                    user.setEmail(emailToSearch);
                    user.setName(name);
                    break; // Assuming there's only one record with the matching email
                }
            }

            // Close the database
            db.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return user;
    }

    public void changePasswordByEmail(String email, String newPassword) throws Exception{

            // Open the Access database
            Database db = DatabaseBuilder.open(new File(databasePath));

            // Get the table
            Table table = db.getTable("tblUsers");

            // Iterate through the rows and find the row with the matching email
            for (Row row : table) {
                String emailAddress = row.getString("EmailAddress");
                if (emailAddress != null && emailAddress.equalsIgnoreCase(email)) {
                    // Update the password field
                    row.put("Password", newPassword);
                    table.updateRow(row);

                    break; // Assuming there's only one record with the matching email
                }
            }

            // Save the changes
            db.flush();

            // Close the database
            db.close();
    }

    public ArrayList<RecyclingFacts> getRandomRows(int number) {
        ArrayList<RecyclingFacts> randomRows = new ArrayList<>();
        try {
            // Open the Access database
            Database db = DatabaseBuilder.open(new File(databasePath));

            // Get the table
            Table table = db.getTable("tblFacts");


            // Get the total number of rows in the table
            int totalRows = table.getRowCount();

            // Use a random number generator
            Random random = new Random();

            // Retrieve random rows
            for (int i = 0; i < number; i++) {
                // Generate a random row index
                int randomRowIndex = random.nextInt(totalRows);

                // Fetch the random row
                Row r = CursorBuilder.findRowByPrimaryKey(table,randomRowIndex);
                var temp = new RecyclingFacts();
                temp.setDescription(r.getString("Fact"));
                randomRows.add(temp);
            }

            // Close the database
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return randomRows;
    }
}

