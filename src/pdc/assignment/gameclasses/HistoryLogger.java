/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gameclasses;

import java.util.ArrayList;

/**
 *
 * @author wease
 */

/**
 * The HistoryLogger is a static class that gets fed messages from all object 
 * that need to output a message.
 */
public class HistoryLogger {
    
    private final static ArrayList<String> messageLogger = new ArrayList<>();
    
    /**
    * This method appends the messages to be offloaded.
     * @param text
    */
    public static void append(String text) {
        messageLogger.add(text);
    }
    
    /**
     * This treats the array list like a buffer and offload the messages to 
     * the history log JPanel's TextArea.
     * @return 
     */
    public static ArrayList unload() {
        ArrayList<String> messageToSend = new ArrayList<>();
        
        // This copies all elements instead of doing a shallow copy.
        // This is required to empty the arraylist for future messages.
        messageLogger.forEach(element -> {
            messageToSend.add(element);
        });
        messageLogger.clear();
        return messageToSend;
    }
}
