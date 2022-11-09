package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import model.Task;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;  
import util.connectionFactory;
import javax.management.RuntimeErrorException;



/**
 *
 * @author rogou
 */
public class TaskController {
    
    public void save (Task task) throws SQLException {
        String sql = "INSERT INTO tasks (idProject,"
                + "name," 
                + "description,"
                + "completed,"
                + "notes,"
                + "deadline,"
                + "createdAt,"
                + "updateAt) VALUES (?,?,?,?,?,?,?,?)";
               
     Connection connection = null;
     PreparedStatement statement = null;
     
    
         
     try{
         connection = connectionFactory.getConnection ();
         statement = connection.prepareStatement (sql);
         statement.setInt (1, task.getIdProject());
         statement.setString (2, task.getName());
         statement.setString (3, task.getDescription());
         statement.setBoolean (4, task.isIsCompleted());
         statement.setString (5, task.getNotes());
         statement.setDate (6, new Date (task.getCreateAt().getTime()));
         statement.setDate (7, new Date (task.getCreateAt().getTime()));
         statement.setDate (8, new Date (task.getUpdateAt().getTime()));
         statement.execute();
         
     }catch (SQLException ex) {
              throw new SQLException ("Erro ao salvar a tarefa"
                                     + ex.getMessage(), ex);
     
     }finally {
                        connectionFactory.closeConnection (connection, statement);
     
     }  
     }
    
    public void update (Task task) throws SQLException {
     String sql= "UPDATE tasks SET"
             + "IDpROJECT =?, "
             + "name =?," 
             + "description =?,"
             + "notes =?,"
             + "completed =?,"
             + "deadline =?,"
             + "createdAt =?,"
             + "updateAt =?, "
             + "where id =? ";
             
                Connection connection= null;
                PreparedStatement Statement = null;
        
        
        
        try{
            //estabelecendo a conecção com o banco de dados
            connection = connectionFactory.getConnection();
            //preparando a query
         PreparedStatement statement = connection.prepareStatement (sql);
         //setando os valores do statement
            statement.setInt (1, task.getIdProject());
            statement.setString (2, task.getName());
            statement.setString (3, task.getDescription());
            statement.setBoolean (4, task.isIsCompleted());
            statement.setString (5, task.getNotes());
            statement.setDate (6, new Date (task.getCreateAt().getTime()));
            statement.setDate (7, new Date (task.getCreateAt().getTime()));
            statement.setDate (8, new Date (task.getUpdateAt().getTime()));
            Statement.setInt(9, task.getId());
            //executando a query
            statement.execute();
         
       
        }catch (Exception ex){
         throw new RuntimeException ("Erro ao atualizar a tarefa"
                      + ex.getMessage(), ex);   
        }

    }


    public void removeById(int taskId){   
       String sql  = "DELETE FROM tasks WHERE id = ?";
       
       Connection connection = null;
       PreparedStatement statement = null;
       
       try{
           //criação da conexão com o banco de dados
        connection = connectionFactory.getConnection();
        //preparando a query
        statement = connection.prepareStatement (sql);
        //setando os valores
           int tastkId = 0;
        statement.setInt (1, tastkId);
        //executando a query
        statement.execute();
        
    } catch (SQLException ex){
        throw new RuntimeException ("Erro ao deletar a tarefa");
    }finally{
           connectionFactory.closeConnection(connection, statement);
       }
    }

    /**
     *
     * @param idProject
     * @return
     */
    public List<Task> getall(int idProject) throws SQLException{
        
        String sql = "SELECT*FROM task WHERE idProject =? ";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultset = null;
        
        //contrução de uma lista de tarefas
        
        List<Task> tasks = new ArrayList<Task>();
        
        try {
            //criação da conexão
            connection = connectionFactory.getConnection();
            statement =connection.prepareStatement (sql);
            //setando o valor que corresponde ao filtro de busca
            statement.setInt(1, idProject);
            //valor retornado pela execução da query
            resultset = statement.executeQuery();
            //enquanto houverem valores a ser percorridos pelo resultset
            while (resultset.next()){
                Task task = new Task();
                task.setId(resultset.getInt("idProject"));
                task.setDescription(resultset.getString("descreption"));
                task.setNotes(resultset.getString("notes"));
                task.isIsCompleted(resultset.getBoolean("completed"));
                task.setDeadline(resultset.getDate("deadline"));
                task.getCreatedAt(resultset.getDate("cratedAt"));
                task.setUpdateAt(resultset.getDate("update"));
                
                tasks.add(task);       
            }
            
        } catch (Exception ex) {
            throw new SQLException ("Erro ao inserir a tarefa"
                      + ex.getMessage(), ex);
        } finally{
            connectionFactory.closeConnection(connection, statement, resultset);
            
        }
        //devolução da lista de tarefas
        return tasks;
        
    }
}
