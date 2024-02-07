package jdbcConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBC {
  
  // Define your database connection details
  static final String JDBC_DRIVER = "your_jdbc_driver"; // e.g., "com.mysql.cj.jdbc.Driver"
  static final String DB_URL = "your_database_url"; // e.g., "jdbc:mysql://localhost:3306/your_database"

  static final String USER = "your_username";
  static final String PASS = "your_password";

  public static void main(String[] args) {
      Connection conn = null;
      PreparedStatement preparedStatement = null;
      Scanner scanner = new Scanner(System.in);

      try {
          // Register JDBC driver and open a connection
          
          conn = DriverManager.getConnection(DB_URL, USER, PASS);

          // Insert a new animal
          System.out.println("Enter animal name:");
          String animalName = scanner.nextLine();
          System.out.println("Enter animal species:");
          String species = scanner.nextLine();
          String insertQuery = "INSERT INTO animals (name, species) VALUES (?, ?)";
          preparedStatement = conn.prepareStatement(insertQuery);
          preparedStatement.setString(1, animalName);
          preparedStatement.setString(2, species);
          preparedStatement.executeUpdate();
          System.out.println("Animal inserted successfully!");

          // Update species of a specific animal
          System.out.println("Enter animal ID to update species:");
          int animalIdToUpdate = scanner.nextInt();
          scanner.nextLine(); // Consume newline
          System.out.println("Enter new species:");
          String newSpecies = scanner.nextLine();
          String updateQuery = "UPDATE animals SET species = ? WHERE id = ?";
          preparedStatement = conn.prepareStatement(updateQuery);
          preparedStatement.setString(1, newSpecies);
          preparedStatement.setInt(2, animalIdToUpdate);
          preparedStatement.executeUpdate();
          System.out.println("Animal species updated successfully!");

          // Delete an animal with a specific ID
          System.out.println("Enter animal ID to delete:");
          int animalIdToDelete = scanner.nextInt();
          String deleteQuery = "DELETE FROM animals WHERE id = ?";
          preparedStatement = conn.prepareStatement(deleteQuery);
          preparedStatement.setInt(1, animalIdToDelete);
          preparedStatement.executeUpdate();
          System.out.println("Animal deleted successfully!");

          // Retrieve and display all animals' information
          String selectQuery = "SELECT * FROM animals";
          preparedStatement = conn.prepareStatement(selectQuery);
          ResultSet resultSet = preparedStatement.executeQuery();
          System.out.println("Animals in the zoo:");
          while (resultSet.next()) {
              int id = resultSet.getInt("id");
              String name = resultSet.getString("name");
              String animalSpecies = resultSet.getString("species");
              System.out.println("ID: " + id + ", Name: " + name + ", Species: " + animalSpecies);
          }

      } catch (SQLException se) {
          // Handle JDBC errors
          se.printStackTrace();
      } catch (Exception e) {
          // Handle other errors
          e.printStackTrace();
      } finally {
          // Close resources
          try {
              if (preparedStatement != null) {
                  preparedStatement.close();
              }
          } catch (SQLException se2) {
              // Error closing preparedStatement
          }
          try {
              if (conn != null) {
                  conn.close();
              }
          } catch (SQLException se) {
              se.printStackTrace();
          }
          scanner.close();
      }
  }
}


