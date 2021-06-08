/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gui.components.gamecomponent;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author wease
 */

/**
 * This is a JPanel that displays the outputs when the player invokes an event.
 * 
 */
public final class HistoryLog extends JPanel{
    
    private final JScrollPane historyScrollPanel;
    private final JTextArea historyTextLog;
    
    public HistoryLog() {
        this.historyScrollPanel = new JScrollPane();
        this.historyScrollPanel.setPreferredSize(new Dimension(500,500));
        this.add(this.historyScrollPanel);
        this.historyTextLog = new JTextArea();
        this.add(this.historyTextLog);
        this.historyScrollPanel.setViewportView(this.historyTextLog);
        
    }

    // Allows for updates in the controller when an action is taken.
    public void addText(String text) {
        this.historyTextLog.append(text);
    }
}
