/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.awt.Color;
import java.awt.Component;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTable;
import model.Task;
import sun.swing.table.DefaultTableCellHeaderRenderer;


/**
 *
 * @author rogou
 */
public class DeadlineColumnCellRedere extends DefaultTableCellHeaderRenderer{
    
    
   @Override
    public Component getTableCellRendererComponent (JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int col){
    
        JLabel label;
        label = (JLabel) super.getTableCellRendererComponent(table,
                value, isSelected, hasFocus, row, col);
        //ALINHO O TEXTO para ficar no centro da tebela
        label.setHorizontalAlignment(CENTER);
        
        TaskTableModel taskModel = (TaskTableModel) table.getModel();
        Task task  = taskModel.getTasks().get(row);
        //se o deadeline(data) estiver no prrazo a coluna fica verde
        if (task.getDeadline().after(new Date())){
            label.setBackground(Color.GREEN);
         //se passou do prazo fica vermelho   
        }    else{
            label.setBackground(Color.RED);
        }
        
        
        
    return label;
    }
   
}
