/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author ieuan
 */
public class MainGui extends JFrame implements ActionListener {
    
    private JPanel mainPanel;
    private JPanel secPanel;
    private JPanel thrdPanel;
    private JButton button1;
    
    public MainGui(){
        mainPanel = new JPanel();
        secPanel = new JPanel();
        thrdPanel = new JPanel();

        button1 = new JButton("NEW GAME");
        JButton button2 = new JButton("LOAD GAME");
        JButton button3 = new JButton("RULES");
        JButton button4 = new JButton("DELETE SAVE");
        JButton button5 = new JButton("EXIT");
        
        Component myComp = new Component();
        
        mainPanel.add(myComp);
        secPanel.add(button1);
        secPanel.add(button2);
        secPanel.add(button3);
        secPanel.add(button4);
        secPanel.add(button5);
        
        button1.addActionListener(this);
        
        this.add(mainPanel, BorderLayout.NORTH);
        this.add(secPanel, BorderLayout.SOUTH);
        
        //thrdPanel.setVisible(false);
        
        this.setSize(500, 700);
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        if (source == button1){
            secPanel.setVisible(false);
            thrdPanel.setVisible(true);
        }
    }
}