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
public class HistoryLogger {
    
    private final static ArrayList<String> messageLogger = new ArrayList<>();
    
    public static void append(String text) {
        messageLogger.add(text);
    }
    
    public static ArrayList unload() {
        return messageLogger;
    }
}
