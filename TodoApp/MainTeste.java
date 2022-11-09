


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import java.sql.Connection;
import util.ConnectionFactory;

/**
 *
 * @author rogou
 */

public class MainTeste {
    

    /**
     * @param args the command line arguments
     */
    public static void mainTeste(String[] args) {
        // TODO code application logic here
        
        Connection c = ConnectionFactory.get.Connection();
        ConnectionFactory.closeConnection(c); 
    }
    
}
