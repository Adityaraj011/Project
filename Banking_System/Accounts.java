package Backing_System;

import java.sql.*;
import java.util.Scanner;

public class Accounts {
  private Connection connection;

  public Accounts(Connection connection) {
    this.connection = connection;
  }

  Scanner scanner = new Scanner(System.in);

  public long open_account(String email) {
    if (!account_exist(email)) {
      String query = "Insert into Accounts(Account_Number,Full_Name,Email,Balance,Security_Pin) VALUES (?,?,?,?)";

      scanner.nextLine();
      System.out.print("Enter Full Name: ");
      String full_name = scanner.nextLine();
      System.out.print("Enter Initial Amount: ");
      double balance = scanner.nextDouble();
      scanner.nextLine();
      System.out.print("Enter Security Pin: ");
      String security_pin = scanner.nextLine();

      try {

        long account_number = generateAccountNumber();
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setLong(1, account_number);
        preparedStatement.setString(2, full_name);
        preparedStatement.setString(3, email);
        preparedStatement.setDouble(4, balance);
        preparedStatement.setString(5, security_pin);
        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
          return account_number;
        } else {
          throw new RuntimeException("Account Creation failed!!");
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    throw new RuntimeException("Account Already Exist");
  }

  public long getAccount_number(String email) {
    String query = "SELECT account_number from Accounts WHERE email = ?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, email);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        return resultSet.getLong("account_number");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    throw new RuntimeException("Account Number Doesn't Exist!");
  }

  private long generateAccountNumber() {
    try {
      Statement statement = connection.createStatement();
      String query = "Select Account_Number from Accounts order by Account_order DESC LIMIT 1";
      ResultSet resultSet = statement.executeQuery(query);
      if (resultSet.next()) {
        long last_account_number = resultSet.getLong("Account_Number");
        return last_account_number + 1;
      } else {
        return 10000100;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return 10000100;
  }

  public boolean account_exist(String email) {
    String query = "Select Account_number from Accounts where email=?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, email);

      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        return true;
      } else {
        return false;
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return false;
  }

}
