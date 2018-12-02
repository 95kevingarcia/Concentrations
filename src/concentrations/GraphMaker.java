/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concentrations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author Kevin Garcia
 * This is used to read directly from excel files for concentrations
 */
class GraphMaker {
    private String file;
    private String[][] data;
    private String titles[];
        
    
    GraphMaker(String file){
         this.file = file;
    }
    
    //Reads excel files and stores it in the private variables above
    void read() throws FileNotFoundException, IOException, InvalidFormatException, InterruptedException {
        InputStream fileInputStream = new FileInputStream(file);
        Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);
        List<String> key = new ArrayList<>();
        List<Double> values = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();
        data = new String[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()-4];
        //System.out.println(sheet.getLastRowNum());
        //System.out.println(sheet.getRow(0).getLastCellNum()-3);
        
        titles = new String[sheet.getRow(0).getLastCellNum()];
        //System.out.println(titles.length);
        //System.out.println("");
        
        int i = 0;
        for(Row r : sheet){
            i = 0;
            //System.out.println("");
            for (Cell c : r){
                if(r.getRowNum() == 0){
                    titles[c.getColumnIndex()] = formatter.formatCellValue(c);
                }
                else if (c.getColumnIndex() > 3){
                    //System.out.println(titles[c.getColumnIndex()]);
                    data[r.getRowNum()-1][i++] = formatter.formatCellValue(c);
                    //if(i>27){
                    //    System.out.println(i-1);
                    //    System.out.println(formatter.formatCellValue(c));
                    //}
                }
            }
            //System.out.println("");
        }
        
    }
    String[][] getData(){
        return data;
    }
    
    String[] getTitles(){
        return titles;
    }

    
}
