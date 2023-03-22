/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class mydb {
    final String url="jdbc:mysql://localhost:3306/project2";
    final String username="root";
    final String password = "";
    private Connection connection;
    static mydb instance ;
    
      public mydb(){
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("connexion établie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
          
      }
      public static mydb getinstance(){
          if (instance == null )
              instance = new mydb();
          return instance;
      }
      public Connection getConnection() {
        return connection;
    }
      
}
