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
public final class HistoryLog extends JPanel{
    
    private final JScrollPane historyScrollPanel;
    private JTextArea historyTextLog;
    
    public HistoryLog() {
        this.historyScrollPanel = new JScrollPane();
        this.historyScrollPanel.setPreferredSize(new Dimension(200,500));
        this.add(this.historyScrollPanel);
        this.historyTextLog = new JTextArea();
        this.add(this.historyTextLog);
        this.historyScrollPanel.setViewportView(this.historyTextLog);
        
    }

    
    public void addText(String text) {
        this.historyTextLog.append(text);
    }
}
