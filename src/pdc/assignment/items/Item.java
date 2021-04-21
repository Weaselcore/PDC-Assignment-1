/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.items;

import java.util.HashMap;

/**
 *
 * @author Weaselcore
 */
public interface Item {
    
    public Integer getIntegerData(HashMap map, String key);
    public String getStringData(HashMap map, String key);
    
}
