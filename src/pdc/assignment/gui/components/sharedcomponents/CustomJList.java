/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gui.components.sharedcomponents;

import java.util.LinkedHashMap;
import java.util.LinkedList;
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
    
    private DefaultListModel<String> listModel = null;
    private int selectedOption = -1;
    
    public CustomJList() {
        this.listModel = new DefaultListModel();
        this.addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()){
                JList source = (JList)e.getSource();
                selectedOption = source.getSelectedIndex();
                System.out.println(selectedOption);
            }
        });
    }

    public void populateListModelHashMap(LinkedHashMap hashmap) {
        this.listModel = new DefaultListModel();
        hashmap.keySet().forEach(key -> {
            this.listModel.addElement(key + " " + hashmap.get(key));
        });
        
        this.setModel(this.listModel);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setCellRenderer(new DefaultListCellRenderer());
        this.setLayout(null);
        this.setVisibleRowCount(1);
        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }
    
    public void populateListModelLinkedList(LinkedList list) {
        this.listModel = new DefaultListModel();
        list.forEach(element -> {
            this.listModel.addElement(element.toString());
        });
        
        this.setModel(this.listModel);
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
