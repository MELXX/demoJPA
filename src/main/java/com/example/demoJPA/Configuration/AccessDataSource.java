package com.example.demoJPA.Configuration;

import com.example.demoJPA.Models.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.expression.spel.ast.ValueRef;
import org.springframework.stereotype.Component;
import com.healthmarketscience.jackcess.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class AccessDataSource {

    //change this
    //@Value("${accessDbPath}")
    private final String databasePath="C:\\Users\\melusi\\Downloads\\RecycleSA.accdb";

    public boolean Login(String password, String emailToSearch) {

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
                if (email != null
                        && email.equalsIgnoreCase(emailToSearch)
                        && psswrd != null
                        && psswrd.equalsIgnoreCase(password)) {
                    isFound = true;
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
            var d = table.addRow(Column.AUTO_NUMBER, userName, userSName, emailAddress, phoneNumber, dob, password);

            // Save the changes
            db.flush();

            // Close the database
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User findUser(String emailToSearch) {
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
                if (email != null
                        && email.equalsIgnoreCase(emailToSearch)) {
                    user = new User();
                    user.setId(row.getInt("UserID").toString());
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

    public void changePasswordByEmail(String email, String newPassword) throws Exception {

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
                Row r = CursorBuilder.findRowByPrimaryKey(table, randomRowIndex);
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

    public ArrayList<RecyclingPlant> getRecyclingPlants() {
        ArrayList<RecyclingPlant> randomRows = new ArrayList<>();
        try {
            // Open the Access database
            Database db = DatabaseBuilder.open(new File(databasePath));

            // Get the table
            Table table = db.getTable("tblRecyclingPlants");


            // Get the total number of rows in the table
            int totalRows = table.getRowCount();

            // Use a random number generator
            Random random = new Random();

            // Retrieve random rows
            for (int i = 0; i < totalRows; i++) {
                // Generate a random row index


                // Fetch the random row
                Row r = table.getNextRow();
                var temp = new RecyclingPlant();
                temp.setName(r.getString("RecyclingPlantName"));
                temp.setAddress(r.getString("Address"));
                temp.setOperatingHours(r.getString("OperatingHours"));
                //temp.setMaterialtype((Materialtype) r.getInt("Website"));
                temp.setSite(r.getString("Website"));
                temp.setEmail(r.getString("EmailAdd"));
                temp.setContact(r.getString("ContactDetails"));
                temp.setMapsFrame(r.getString("GoogleMapsUrl"));
                randomRows.add(temp);
            }

            // Close the database
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return randomRows;
    }

    public ArrayList<FaqItem> getFaqData() {
        ArrayList<FaqItem> randomRows = new ArrayList<>();
        try {
            // Open the Access database
            Database db = DatabaseBuilder.open(new File(databasePath));

            // Get the table
            Table table = db.getTable("tblFAQs");


            // Get the total number of rows in the table
            int totalRows = table.getRowCount();

            // Use a random number generator
            Random random = new Random();

            // Retrieve random rows
            while ((long) randomRows.size() <3) {
                // Generate a random row index


                int randomRowIndex = random.nextInt(totalRows);

                // Fetch the random row
                Row r = CursorBuilder.findRowByPrimaryKey(table, randomRowIndex);
                if(r != null){
                var temp = new FaqItem();
                temp.setDescription(r.getString("Answer"));
                temp.setTopic(r.getString("Question"));
                randomRows.add(temp);
                }
            }

            // Close the database
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return randomRows;
    }

    public String searchFaqData(String search) {
        String[] data = null;
        try {
            // Open the Access database
            Database db = DatabaseBuilder.open(new File(databasePath));
            Table table = db.getTable("tblFAQs");
             data = new String[table.getRowCount()];
            int i =0;
            for (Row row : table) {
               data[i]=row.getString("Answer");
               i++;
            }
            // Close the database
            db.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return  KeywordSearch.main(search,data);
    }

    public ArrayList<Methods> getMethodData() {
        ArrayList<Methods> lst = new ArrayList<Methods>();
        try {
            // Open the Access database
            Database db = DatabaseBuilder.open(new File(databasePath));
            Table table = db.getTable("tblMethods");

            int i =0;
            for (Row row : table) {
                var temp = new Methods();
                temp.setDifficulty(row.getString("Difficulty"));
                temp.setDescription(row.getString("Method"));
                lst.add(temp);
            }
            // Close the database
            db.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return  lst;
    }

    public ArrayList<Transaction> getTransactionData(String id) throws NullPointerException {
        ArrayList<Transaction> lst = new ArrayList<Transaction>();
        try {
            // Open the Access database
            Database db = DatabaseBuilder.open(new File(databasePath));
            Table table = db.getTable("tblTransactions");

            int i =0;
            for (Row row : table) {
                String rowId = row.getString("UserID");
                if(rowId!=null&& rowId.equals(id)) {
                    var temp = new Transaction();
                    temp.setDate(row.getLocalDateTime("TransDate"));
                    temp.setMaterial(convert(Integer.parseInt(row.getString("MaterialID"))));
                    temp.setQuantity(row.getInt("Quantity"));
                    lst.add(temp);
                }
            }
            // Close the database
            db.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return  lst;
    }

    private static String convert(int i){
        switch (i) {
            case 1:
                return "Plastic";
            case 2:
                return "Metal";
            case 3:
                return "E Waste";
            case 4:
                return "Glass";
            case 5:
                return "Paper";

        }
        return "ERROR";
    }
}

