/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concentrations;

/**
 *
 * @author Kevin Garcia
 * This is a custom object to store concentrations with getters and setters.
 */
class Concentration {
    private String[] data;
    private boolean right;
    private boolean male;
    private double[] values;
    
    
    Concentration(int i){
        this.data = new String[i];
        this.values = new double[i-1];
    }
    
    String[] getData(){
        return data;
    }
    
    boolean getRight(){
        return right;
    }
    
    boolean getMale(){
        return male;
    }
    
    void setData(String[] info, boolean side, boolean gender){
        data = info;
        right = side;
        male = gender;
    }
    
    void setData(String[] info, boolean gender){
        data = info;
        male = gender;
    }

    void parse() {
        int max = 0;
        for(int i = 0; i < data.length-1; i++){
            values[i] = Double.parseDouble(data[i+1]);
            max = i-1;
        }
        
    }

    double getValues(int index) {
        return values[index];
    }
    
}
