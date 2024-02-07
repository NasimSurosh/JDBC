package jdbc;
import java.sql.*;
public class Driver {

  public static void main(String[] args) throws SQLException{
    
    Connection connection = null;
    Statement statement = null;
    ResultSet result = null;
    
    try {
      // 1.  get a connection to a database
      connection = DriverManager.getConnection("jdbc:mysql:localhost:3306/example", "root","Nasim646016");
      
      // 2. create statement
      statement = connection.createStatement();
      
      // 3. execute sql quary
      result = statement.executeQuery("select * from example");
      
      // 4. process the result
      while(result.next()) {
        System.out.println(result.getString("lastName")+" " + result.getString("firstName"));
      }
    }catch(Exception e) {
      System.out.println(e.getMessage());
    }finally {
      if(result != null) {
        result.close();
      }
      if(statement !=null) {
        statement.close();
      }
      if(connection != null) {
        connection.close();
      }
    }

  }

}
