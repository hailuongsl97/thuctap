/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cach2;

/**
 *
 * @author HL
 */
public class Word {
    private String value;
    private boolean  check; 
    private  int number; 

    public Word(String value, boolean check, int number) {
        this.value = value;
        this.check = check;
        this.number = number;
    }
        
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Word{" + "value=" + value + ", check=" + check + ", number=" + number + '}';
    }
    
    
}
