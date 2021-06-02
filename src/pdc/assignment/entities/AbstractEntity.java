/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.entities;

import java.io.Serializable;

/**
 *
 * @author ieuan
 */
public abstract class AbstractEntity implements Entity, Serializable{
    
    String name;
    String description;
    int currentHealth;
    int damage;
    int armour;
    int maxHealth;
    
}