/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gui.components.sharedcomponents;

import java.util.LinkedHashMap;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author wease
 */
public class CustomJList extends JList{
    
    private DefaultListModel<String> saveListToAdd = null;
    private int selectedOption = 0;
    
    public CustomJList() {
        this.saveListToAdd = new DefaultListModel();
        this.addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()){
                JList source = (JList)e.getSource();
                selectedOption = source.getSelectedIndex();
                System.out.println(selectedOption);
            }
        });
    }

    public void populateSaveList(LinkedHashMap saveList) {
        this.saveListToAdd = new DefaultListModel();
        saveList.keySet().forEach(key -> {
            this.saveListToAdd.addElement(key + " " + saveList.get(key));
        });
        
        this.setModel(this.saveListToAdd);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setCellRenderer(new DefaultListCellRenderer());
        this.setLayout(null);
        this.setVisibleRowCount(1);
        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    /**
     * @return the selectedOption
     */
    public int getSelectedOption() {
        return selectedOption;
    }
    
}
