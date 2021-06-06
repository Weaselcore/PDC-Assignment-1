/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gui.components.gamecomponent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author wease
 */
public final class HistoryLog extends JPanel{
    
    private final JScrollPane historyScrollPanel;
    private JTextArea historyTextLog;
    
    public HistoryLog() {
        this.historyScrollPanel = new JScrollPane();
        this.add(this.historyScrollPanel);
        
        this.historyTextLog = new JTextArea();
        this.add(this.historyTextLog);
    }

    
    public void addText(String text) {
        this.historyTextLog.append(text);
    }
}
