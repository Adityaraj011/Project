package Backing_System;

import java.sql.*;
import java.util.*;

public class User {
  private Connection connection;

  public User(Connection connection) {
    this.connection = connection;
  }

  Scanner scanner = new Scanner(System.in);

  public void register() {
    System.out.println("Full Name = ");
    String full_name = scanner.nextLine();
    System.out.print("Email: ");
    String email = scanner.nextLine();
    System.out.print("Password: ");
    String password = scanner.nextLine();

    if (user_exist(email)) {
      System.out.println("User Already Exists for this Email Address!!");
      return;
    }

    String register_query = "Insert into users(Full_name,Email,Password) VALUES(?,?,?)";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(register_query);

      preparedStatement.setString(1, full_name);
      preparedStatement.setString(2, email);
      preparedStatement.setString(3, password);

      int affectedRow = preparedStatement.executeUpdate();
      if (affectedRow > 0) {
        System.out.println("Registration Successfull!");
      } else {
        System.out.println("Registration Failed!");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public String login() {
    scanner.nextLine();
    System.out.print("Email: ");
    String email = scanner.nextLine();
    System.out.print("Password: ");
    String password = scanner.nextLine();
    String login_query = "SELECT * FROM users WHERE email = ? AND password = ?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(login_query);
      preparedStatement.setString(1, email);
      preparedStatement.setString(2, password);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        return email;
      } else {
        return null;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public boolean user_exist(String email) {
    String query = "Select * from users  where email=? ";

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
