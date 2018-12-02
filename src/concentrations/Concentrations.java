/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concentrations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
/**
 *
 * @author Kevin Garcia
 * This program grabs excel files and uses them to do KNN against data to classify them
 */

public class Concentrations {

    /**
     * @param args the command line arguments
     */
    static public void main(String args[]) throws IOException, FileNotFoundException, InvalidFormatException, InterruptedException {
        //DragDrop frame;
        GraphMaker make;
        String file = null;
        String[][] data;
        String titles[];
        int correctHand = 0;
        int correctGender = 0;
        int correctHandWeighted = 0;
        int correctGenderWeighted = 0;
        String hander;
        String genderer;
        String armpiter;
        boolean[] guess;
        double values[];
        boolean right;
        boolean gender;
        boolean hand;
        Concentration concentrations[];
        
        //frame = new DragDrop();
        //file = frame.getFile();
        
        file = "src/SourceFiles/TestConcentrations.xlsx";
        
        if(file.isEmpty()){
            System.exit(0);
        }
        System.out.println("First file: " + file);
        
        make = new GraphMaker(file);
        make.read();
        data = make.getData();
        concentrations = new Concentration[data.length];
        //fills in concentrations
        for(int i = 0; i < data.length; i++){
            concentrations[i] = new Concentration(data[i].length);
            right = data[i][0].contains("R");
            gender = data[i][0].contains("M");
            concentrations[i].setData(data[i], right, gender);
            concentrations[i].parse();
        }
        //classifies concentrations using KNN for first file
        for(int i = 0; i < data.length; i++){
            values = new double[(data[i].length-1)];
            for(int j = 0; j < concentrations[i].getData().length-1; j++){
                values[j] = concentrations[i].getValues(j);
            }
            //guess = getKNearestNeighbors(concentrations, values, 3);
            //correctHand = ((guess[0] == concentrations[i].getRight()) ? correctHand+1 : correctHand);
            //correctGender = ((guess[1] == concentrations[i].getMale()) ? correctGender+1 : correctGender);
            guess = getKNearestNeighborsWeighted(concentrations, values, 3);
            correctHandWeighted = ((guess[0] == concentrations[i].getRight()) ? correctHandWeighted+1 : correctHandWeighted);
            correctGenderWeighted = ((guess[1] == concentrations[i].getMale()) ? correctGenderWeighted+1 : correctGenderWeighted);
        }
        //System.out.println("The total amount of Hands correctly identified are " + correctHand + " out of " + data.length);
        //System.out.println("The total amount of Genders correctly identified are " + correctGender + " out of " + data.length);
        System.out.println("The total amount of Hands correctly identified are " + correctHandWeighted + " out of " + data.length);
        System.out.println("The total amount of Genders correctly identified are " + correctGenderWeighted + " out of " + data.length);
        
        
        file = "src/SourceFiles/Armpit.xlsx";
        
        if(file.isEmpty()){
            System.exit(0);
        }
        System.out.println("Second file: " + file);
        
        make = new GraphMaker(file);
        make.read();
        data = make.getData();
        concentrations = new Concentration[data.length];
        //fills in concentrations
        for(int i = 0; i < data.length; i++){
            concentrations[i] = new Concentration(data[i].length);
            gender = data[i][0].contains("M");
            concentrations[i].setData(data[i], gender);
            concentrations[i].parse();
        }
        correctGender = 0;
        correctGenderWeighted = 0;
        //classifies concentrations using KNN for second file
        for(int i = 0; i < data.length; i++){
            values = new double[(data[i].length-1)];
            for(int j = 0; j < concentrations[i].getData().length-1; j++){
                values[j] = concentrations[i].getValues(j);
            }
            //guess = getKNearestNeighbors(concentrations, values, 3);
            //correctGender = ((guess[1] == concentrations[i].getMale()) ? correctGender+1 : correctGender);
            guess = getKNearestNeighborsWeighted(concentrations, values, 3);
            correctGenderWeighted = ((guess[1] == concentrations[i].getMale()) ? correctGenderWeighted+1 : correctGenderWeighted);
        }
        //System.out.println("The total amount of Genders correctly identified are " + correctGender + " out of " + data.length);
        System.out.println("The total amount of Genders correctly identified are " + correctGenderWeighted + " out of " + data.length);
        
        
        file = "src/SourceFiles/HandArmpitRevised.xlsx";
        
        if(file.isEmpty()){
            System.exit(0);
        }
        System.out.println("Third file: " + file);
        
        make = new GraphMaker(file);
        make.read();
        data = make.getData();
        concentrations = new Concentration[data.length];
        //fills in concentrations
        for(int i = 0; i < data.length; i++){
            concentrations[i] = new Concentration(data[i].length);
            right = data[i][0].contains("-");
            gender = data[i][0].contains("M");
            concentrations[i].setData(data[i], right, gender);
            concentrations[i].parse();
        }
        correctHand = 0;
        correctGender = 0;
        correctHandWeighted = 0;
        correctGenderWeighted = 0;
        //classifies concentrations using KNN for third file
        for(int i = 0; i < data.length; i++){
            values = new double[(data[i].length-1)];
            for(int j = 0; j < concentrations[i].getData().length-1; j++){
                values[j] = concentrations[i].getValues(j);
            }
            //guess = getKNearestNeighbors(concentrations, values, 3);
            //correctHand = ((guess[2] == concentrations[i].getRight()) ? correctHand+1 : correctHand);
            //correctGender = ((guess[1] == concentrations[i].getMale()) ? correctGender+1 : correctGender);
            guess = getKNearestNeighborsWeighted(concentrations, values, 3);
            correctHandWeighted = ((guess[2] == concentrations[i].getRight()) ? correctHandWeighted+1 : correctHandWeighted);
            correctGenderWeighted = ((guess[1] == concentrations[i].getMale()) ? correctGenderWeighted+1 : correctGenderWeighted);
            
        }
        //System.out.println("The total amount of Hand or Armpit correctly identified are " + correctHand + " out of " + data.length);
        //System.out.println("The total amount of Genders correctly identified are " + correctGender + " out of " + data.length);
        System.out.println("The total amount of Hand or Armpit correctly identified are " + correctHandWeighted + " out of " + data.length);
        System.out.println("The total amount of Genders correctly identified are " + correctGenderWeighted + " out of " + data.length);
    }
    
    /* this was used if we are not scaling the distance with 1/d, however scaling obviously gives better results
    public static boolean[] getKNearestNeighbors(Concentration concentrations[], double[] data, int k) {
        double[][] nearest = new double[k][2];
        int index;
        double difference;
        double minDiff;
        int count = 0;
        boolean[] guess = new boolean[3];
        
        for(int j = 0; j < k; j++){
            difference = 0;
            minDiff = Double.MAX_VALUE-1;
            index = -1;
            for(int i = 0; i < concentrations.length; i++){
                for(int h = 0; h < data.length; h++){
                    difference += ((data[h] - concentrations[i].getValues(h)) * (data[h] - concentrations[i].getValues(h)));
                }
                difference = Math.sqrt(difference);
                if(difference == 0){
                    difference = Double.MAX_VALUE;
                }
                if(minDiff > difference){
                    if(j > 0){
                        if(difference > nearest[j-1][0]){
                            minDiff = difference;
                            index = i;
                        }
                        if(difference == nearest[j-1][0] && i != nearest[j-1][1]){
                            minDiff = difference;
                            index = i;
                        }
                    }
                    else{
                        minDiff = difference;
                        index = i;
                    }
                }
            }
            nearest[j][0] = minDiff;
            nearest[j][1] = index;
        }
        count = 0;
        for(int i = 0; i < k; i++){
            //ystem.out.println("Nearest neighbor " + i + " has " + concentrations[(int)nearest[i][1]].getData()[0]);
            if(concentrations[(int)nearest[i][1]].getData()[0].contains("L")){
                count++;
            }
        }
        if(count > 1){
            //System.out.println("THIS IS A LEFT HAND");
            //System.out.println(count);
            guess[0] = false;
        }
        else{
            //System.out.println("THIS IS A RIGHT HAND");
            //System.out.println(count);
            guess[0] = true;
        }
        
        count = 0;
        for(int i = 0; i < k; i++){
            if(concentrations[(int)nearest[i][1]].getData()[0].contains("M")){
                count++;
            }
        }
        if(count > 1){
            //System.out.println("THIS IS A MALE HAND");
            //System.out.println(count);
            guess[1] = true;
        }
        else{
            //System.out.println("THIS IS A FEMALE HAND");
            //System.out.println(count);
            guess[1] = false;
        }
        
        count = 0;
        for(int i = 0; i < k; i++){
            if(concentrations[(int)nearest[i][1]].getData()[0].contains("-")){
                count++;
            }
        }
        if(count > 1){
            //System.out.println("THIS IS A MALE HAND");
            //System.out.println(count);
            guess[2] = true;
        }
        else{
            //System.out.println("THIS IS A FEMALE HAND");
            //System.out.println(count);
            guess[2] = false;
        }
        
        return guess;
        
    }
    */
    
    //does KNN weighted on the data given against the concentrations given with given k neighbors
    public static boolean[] getKNearestNeighborsWeighted(Concentration concentrations[], double[] data, int k) {
        double[][] nearest = new double[k][2];
        int index;
        double difference;
        double minDiff;
        double left, right, male, female, hand, armpit;
        boolean[] guess = new boolean[3];
        
        for(int j = 0; j < k; j++){
            difference = 0;
            minDiff = Double.MAX_VALUE-1;
            index = -1;
            for(int i = 0; i < concentrations.length; i++){
                for(int h = 0; h < data.length; h++){
                    difference += ((data[h] - concentrations[i].getValues(h)) * (data[h] - concentrations[i].getValues(h)));
                }
                difference = Math.sqrt(difference);
                if(difference == 0){
                    difference = Double.MAX_VALUE;
                }
                if(minDiff > difference){
                    if(j > 0){
                        if(difference > nearest[j-1][0]){
                            minDiff = difference;
                            index = i;
                        }
                        if(difference == nearest[j-1][0] && i != nearest[j-1][1]){
                            minDiff = difference;
                            index = i;
                        }
                    }
                    else{
                        minDiff = difference;
                        index = i;
                    }
                }
            }
            nearest[j][0] = minDiff;
            nearest[j][1] = index;
        }
        left = 0;
        right = 0;
        for(int i = 0; i < k; i++){
            if(concentrations[(int)nearest[i][1]].getData()[0].contains("L")){
                left += 1.0 / nearest[i][0];
            }
            else{
                right += 1.0 / nearest[i][0];
            }
        }
        male = 0;
        female = 0;
        for(int i = 0; i < k; i++){
            if(concentrations[(int)nearest[i][1]].getData()[0].contains("M")){
                male += 1.0 / nearest[i][0];
            }
            else{
                female += 1.0 / nearest[i][0];
            }
        }
        
        hand = 0;
        armpit = 0;
        for(int i = 0; i < k; i++){
            if(concentrations[(int)nearest[i][1]].getData()[0].contains("-")){
                hand += 1.0 / nearest[i][0];
            }
            else{
                armpit += 1.0 / nearest[i][0];
            }
        }
        guess[0] = right>left;
        guess[1] = male>female;
        guess[2] = hand>armpit;
        return guess;
        
    }
}