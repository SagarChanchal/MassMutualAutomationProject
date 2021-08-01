package com.quantum.utilities;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import java.util.HashMap;
public class IOExcelActivity {
    Fillo fillo;
    Connection connection;
    Recordset recordset;
    static String filePath;
    public HashMap<String, String> readDataFromExcel(String scenarioID, String sheetName) throws FilloException {
        this.filePath = System.getProperty("user.dir")+ConfigurationManager.getBundle().getString("testDataPath");
        fillo = new Fillo();
        connection = fillo.getConnection(filePath);
        try{
            String strQuery = "Select * from " + sheetName + " where Scenario_ID='" + scenarioID + "' and Execution_Flag='N'";
            System.out.println(strQuery);
            recordset = connection.executeQuery(strQuery);
            recordset.moveFirst();
        }catch (Exception e1){
            try{
                String strQuery = "Select * from " + sheetName + " where Scenario_ID='" + scenarioID + "' and Execution_Flag='In Progress'";
                System.out.println(strQuery);
                recordset = connection.executeQuery(strQuery);
                recordset.moveFirst();
            }catch (Exception e2){
                try{
                    String strQuery = "Select * from " + sheetName + " where Scenario_ID='" + scenarioID + "' and Execution_Flag='Failed'";
                    System.out.println(strQuery);
                    recordset = connection.executeQuery(strQuery);
                    recordset.moveFirst();
                }catch (Exception e3){
                    e2.printStackTrace();
                }
            }
        }
        HashMap<String, String> hm = new HashMap<>();
        for (String x : recordset.getFieldNames()) {
            hm.put(x, recordset.getField(x));
        }
        recordset.close();
        connection.close();
        return hm;
    }

    public void updateColumnValueInExcelSheet(String sheetName, String scenarioID, String setColumn, String setValue) throws Exception {
        fillo= new Fillo();
        connection = fillo.getConnection(filePath);
        String myquery = "UPDATE " + sheetName + " SET " + setColumn + " ='" + setValue + "' WHERE Scenario_ID = '" + scenarioID + "'";
        connection.executeUpdate(myquery);
        connection.close();
    }

}
