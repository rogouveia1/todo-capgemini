/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import controller.ProjectController;
import controller.TaskController;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import model.Project;
import model.Task;

/**
 *
 * @author rogou
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
        ProjectController projectController = new ProjectController ();
        
        Project project = new Project();
        project.setId(0);
        project.setName("Nome do Projeto");
        project.setDescription("description");
        projectController.update(project);
        
        List<Project> projects = projectController.getAll();
        System.out.println("Total de Projetos = " + projects.size());
        
        projectController.removeById(0);
        
        TaskController taskController = new TaskController();
        Task task = new Task();
        task.setId(0);
        task.setName("Criar as telas de aplicação");
        task.setDescription("Deve ser criadas as telas de cadastro");
        task.setNotes("Sem notas");
        task.setIsCompleted(false);
        task.setDeadline(new Date());
        
        taskController.save(task);
        
        task.setName("Alterar telas de Aplicação");
        taskController.update(task);
        List<Task> tasks = taskController.getall(0);
        System.out.println("Total de Tarefas = " + tasks.size());
        
        
        
        
        
        
        
    }
    
}
