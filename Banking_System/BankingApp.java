package Backing_System;

import java.sql.*;
import java.util.Scanner;

public class BankingApp {

  private static final String url = "jdbc:mysql://localhost:3306/";
  private static final String uname = "root";
  private static final String password = "adityaraj@600";

  public static void main(String[] args) throws ClassNotFoundException, SQLException {

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println(e.getMessage());
    }

    try {
      Connection connection = DriverManager.getConnection(url, uname, password);
      Statement statement = connection.createStatement();
      String createDB = "CREATE DATABASE if not exists Banking_System"; // create the database
      statement.executeUpdate(createDB);

      String useDB = "USE Banking_System"; // open the database that we want to use
      statement.executeUpdate(useDB);

      String createTable_1 = "CREATE TABLE IF NOT EXISTS Users("
          + "Full_Name VARCHAR(20),"
          + "Email VARCHAR(100) Primary key,"
          + "password VARCHAR(100) NOT NULL" + ")";

      statement.executeUpdate(createTable_1);

      String alterUsers = "ALTER TABLE Users "
          + "MODIFY Full_Name VARCHAR(50) NOT NULL, "
          + "MODIFY Email VARCHAR(100) NOT NULL, "
          + "MODIFY Password VARCHAR(100) NOT NULL;";
      statement.executeUpdate(alterUsers);

      String createTable_2 = "CREATE TABLE IF NOT EXISTS Accounts("
          + "Account_number Bigint Primary KEY,"
          + "Full_Name VARCHAR(20),"
          + "Email VARCHAR(100) Unique NOT NULL,"
          + "Balance DECIMAL(10,2) DEFAULT 0.00,"
          + "Security_Pin CHAR(4) NOT NULL" + ")";

      statement.executeUpdate(createTable_2);

      String alterAccounts = "ALTER TABLE Accounts "
          + "MODIFY Account_number BIGINT NOT NULL, "
          + "MODIFY Full_Name VARCHAR(50) NOT NULL, "
          + "MODIFY Email VARCHAR(100) NOT NULL, "
          + "MODIFY Balance DECIMAL(10,2) NOT NULL, "
          + "MODIFY Security_Pin CHAR(4) NOT NULL;";
      statement.executeUpdate(alterAccounts);

      Scanner scanner = new Scanner(System.in);
      User user = new User(connection);
      Accounts accounts = new Accounts(connection);
      Account_manager accountManager = new Account_manager(connection);

      String email;
      long account_number;

      while (true) {
        System.out.println("*** WELCOME TO BANKING SYSTEM ***");
        System.out.println();
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.println("Enter your choice: ");
        int choice1 = scanner.nextInt();

        switch (choice1) {
          case 1:
            user.register();
            break;
          case 2:
            email = user.login();
            if (email != null) {
              System.out.println();
              System.out.println("User Logged In!");
              if (!accounts.account_exist(email)) {
                System.out.println();
                System.out.println("1. Open a new Bank Account");
                System.out.println("2. Exit");
                if (scanner.nextInt() == 1) {
                  account_number = accounts.open_account(email);
                  System.out.println("Account Created Successfully");
                  System.out.println("Your Account Number is: " + account_number);
                } else {
                  break;
                }

              }
              account_number = accounts.getAccount_number(email);
              int choice2 = 0;
              while (choice2 != 5) {
                System.out.println();
                System.out.println("1. Debit Money");
                System.out.println("2. Credit Money");
                System.out.println("3. Transfer Money");
                System.out.println("4. Check Balance");
                System.out.println("5. Log Out");
                System.out.println("Enter your choice: ");
                choice2 = scanner.nextInt();
                switch (choice2) {
                  case 1:
                    accountManager.debit_money(account_number);
                    break;
                  case 2:
                    accountManager.credit_money(account_number);
                    break;
                  case 3:
                    accountManager.transfer_money(account_number);
                    break;
                  case 4:
                    accountManager.getBalance(account_number);
                    break;
                  case 5:
                    break;
                  default:
                    System.out.println("Enter Valid Choice!");
                    break;
                }
              }

            } else {
              System.out.println("Incorrect Email or Password!");
            }
          case 3:
            System.out.println("THANK YOU FOR USING BANKING SYSTEM!!!");
            System.out.println("Exiting System!");
            return;
          default:
            System.out.println("Enter Valid Choice");
            break;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }
}