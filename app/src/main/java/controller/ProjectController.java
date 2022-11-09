/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.PreparedStatement;
import model.Project;
import util.connectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author rogou
 */
public class ProjectController {

    private ResultSet ResultSet;
    
    public void save (Project project) {
        String sql = "Insert into projects name,"
                + "description,"
                + "createdAt,"
                + "updateAt,"
                +"values ?, ?, ?, ?";
        
                Connection connection = null;
                PreparedStatement statement = null;
                
        try {
          connection = connectionFactory.getConnection();
        statement = connection.prepareStatement(sql);
        statement.setString(1, project.getName());
        statement.setString(2, project.getDescription());
        statement.setDate(3, new Date(project.getCreatedAt().getTime()));
        statement.setDate(4, new Date(project.getUpdateAt().getTime()));
        statement.execute();
               
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar o projeto"
            + ex.getMessage(), ex);
        }finally{
            connectionFactory.closeConnection(connection, statement);
        }    
    
}
    public void update (Project project){
        String sql = "UPDATE project SET"
        +"name =?, "
        +"Description =? "
        +"Created =? "
        +"Update =? "
        + "Where Id =?";
        
                Connection connection = null;
                PreparedStatement statement = null;
                
        try {
          connection = connectionFactory.getConnection();
        statement = connection.prepareStatement(sql);
        
        statement.setString(1, project.getName());
        statement.setString(2, project.getDescription());
        statement.setDate(3, new Date(project.getCreatedAt().getTime()));
        statement.setDate(4, new Date(project.getUpdateAt().getTime()));
        statement.setInt(5, project.getId());
        statement.execute();
            
            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao atualizar o projeto"
            + ex.getMessage(), ex);
        }finally{
            connectionFactory.closeConnection(connection, statement);
        }
    }
 
    public List<Project> getAll() throws SQLException{
        
     String sql = "SELECT*FROM project";
     
      List<Project> projects = new ArrayList<Project>();
      
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultset = null;
       
        try {
            connection = connectionFactory.getConnection();
        statement = connection.prepareStatement(sql);
        
            //valor retornado pela execução da query
            resultset = statement.executeQuery();
            //enquanto houverem valores a ser percorridos pelo resultset
            while (resultset.next()){
             Project project = new Project();
             
              project.setId(resultset.getInt("id"));
              project.setName(resultset.getString("name"));
                project.setDescription(resultset.getString("descreption"));
                project.setCreatedAt(resultset.getDate("createdAt"));
                project.setUpdateAt(resultset.getDate("updateAt"));
                  
                 
                 projects.add(project);
            }
        } catch (Exception ex) {
            throw new SQLException ("Erro ao buscar os projetos"
                      + ex.getMessage(), ex);
            
        } finally {
            connectionFactory.closeConnection(connection, statement, resultset);
            
        }
        return projects;
    }
    
    public void removeById (int IdProject){
        String sql = "DELETE FROM project WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
                
        try {
           connection = connectionFactory.getConnection();
        statement = connection.prepareStatement(sql);
        statement.setInt(1, IdProject); 
        statement.execute();
           
        } catch (Exception ex) {
            throw new RuntimeException ("Erro ao deletar a tarefa", ex);
        }
        finally {
            connectionFactory.closeConnection(connection, statement);
            
        }
    }
}