/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author rogou
 */
public class connectionFactory {
    
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost: 3306/todoApp";
    public static final String USER = "root";
    public static final String PASS = "";

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexao com o banco de dados", ex);

        }
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null);
            
            
                {
             connection.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao fechar a conexao com o banco de dados");
        }
    }
    
    public static void closeConnection(Connection connection, 
            PreparedStatement statement, ResultSet resultset) {
        try {
            if (connection != null);{
             connection.close();
            }
             if(statement != null);{
            statement.close();
        }
             if (resultset != null);{
            resultset.close();
        }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao fechar a conexao com o banco de dados");
        }
    
    }

    public static void closeConnection(Connection connection, PreparedStatement statement) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
