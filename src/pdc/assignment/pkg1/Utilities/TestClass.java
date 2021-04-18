/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.pkg1.Utilities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

/**
 *
 * @author ieuan
 */
public class TestClass {
    private String name;
    private int age;
    
    public TestClass(){
        
    }
    
    public TestClass(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }
    
    public String toString(){
      return "Test [ name: "+name+", age: "+ age+ " ]";
   }
}
