package com.quantum.steps;


import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.quantum.pageClasses.*;
import com.quantum.utilities.IOExcelActivity;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import java.util.HashMap;

@QAFTestStepProvider
public class ProblemPageStepDefs {
    private static HashMap<String, String> problemDetails;
    private static String sheetName = ConfigurationManager.getBundle().getString("SheetName");
    private static String scenarioID;
    static WebDriver driver;
    private IOExcelActivity ioExcel = new IOExcelActivity();
    MassMutualProblemPage massMutualProblemPage=new MassMutualProblemPage();

    @Given("^Open MassMutualProblem Application for \"([^\"]*)\"$")
    public void iOpenMassMutualProblemApplicationFor(String scenarioID) throws Throwable {
        this.scenarioID=scenarioID;
        this.problemDetails = ioExcel.readDataFromExcel( scenarioID, sheetName);
        this.driver=new WebDriverTestBase().getDriver();
        driver.manage().window().maximize();
        driver.get(ConfigurationManager.getBundle().getString("baseURL"));
    }

    @Then("^Validate that right count of values appear on the Screen$")
    public void validateThatRightCountOfValuesAppearOnTheScreen() throws Exception {
        try{
        boolean validationResult=massMutualProblemPage.validateCountOfValues(driver,scenarioID,sheetName,problemDetails);
        if(validationResult){
            ioExcel.updateColumnValueInExcelSheet(sheetName, scenarioID,"Execution_Flag", "Passed");
            ioExcel.updateColumnValueInExcelSheet(sheetName, scenarioID,"O_Test_Result", "Validation of Value count is successful");
        }else{
            ioExcel.updateColumnValueInExcelSheet(sheetName, scenarioID,"Execution_Flag", "Failed");
            ioExcel.updateColumnValueInExcelSheet(sheetName, scenarioID,"O_Test_Result", "Validation of Value count has failed");
        }
    }catch (Exception e){
            ioExcel.updateColumnValueInExcelSheet(sheetName, scenarioID,"O_Test_Result", "Error occurred. Please check your script");
        }
        }

    @Then("^Validate that Values on the screen are greater than (\\d+)$")
    public void validateThatValuesOnTheScreenAreGreaterThan(int number) throws Exception {
        try{
            boolean validationResult=massMutualProblemPage.validateTheValues(driver,scenarioID,sheetName,problemDetails, number);
            if(validationResult){
                ioExcel.updateColumnValueInExcelSheet(sheetName, scenarioID,"Execution_Flag", "Passed");
                ioExcel.updateColumnValueInExcelSheet(sheetName, scenarioID,"O_Test_Result", "Validation of individual values greater than Zero is successful");
            }else{
                ioExcel.updateColumnValueInExcelSheet(sheetName, scenarioID,"Execution_Flag", "Failed");
                ioExcel.updateColumnValueInExcelSheet(sheetName, scenarioID,"O_Test_Result", "Validation of individual values greater than Zero has failed");
            }
        }catch (Exception e){
            ioExcel.updateColumnValueInExcelSheet(sheetName, scenarioID,"O_Test_Result", "Error occurred. Please check your script");
        }
    }

    @Then("^Validate that Total balance is correct based on values listed on screen$")
    public void validateThatTotalBalanceIsCorrectBasedOnValuesListedOnScreen() throws Exception {
        try{
            boolean validationResult=massMutualProblemPage.validateTotalBalance(driver,scenarioID,sheetName,problemDetails);
            if(validationResult){
                ioExcel.updateColumnValueInExcelSheet(sheetName, scenarioID,"Execution_Flag", "Passed");
                ioExcel.updateColumnValueInExcelSheet(sheetName, scenarioID,"O_Test_Result", "Validation of total Value based on Values list on screen is Successful");
            }else{
                ioExcel.updateColumnValueInExcelSheet(sheetName, scenarioID,"Execution_Flag", "Failed");
                ioExcel.updateColumnValueInExcelSheet(sheetName, scenarioID,"O_Test_Result", "Validation of total Value based on Values list on screen has failed");
            }
        }catch (Exception e){
            ioExcel.updateColumnValueInExcelSheet(sheetName, scenarioID,"O_Test_Result", "Error occurred. Please check your script");
        }

    }

    @Then("^Validate that Values are formatted as Currencies$")
    public void validateThatValuesAreFormattedAsCurrencies() throws Exception {
        try{
            boolean validationResult = massMutualProblemPage.validateValueCurrency(driver,scenarioID,sheetName,problemDetails);
            if(validationResult){
                ioExcel.updateColumnValueInExcelSheet(sheetName, scenarioID,"Execution_Flag", "Passed");
                ioExcel.updateColumnValueInExcelSheet(sheetName, scenarioID,"O_Test_Result", "Validation of Values formatted as currency is Successful");
            }else{
                ioExcel.updateColumnValueInExcelSheet(sheetName, scenarioID,"Execution_Flag", "Failed");
                ioExcel.updateColumnValueInExcelSheet(sheetName, scenarioID,"O_Test_Result", "Validation of Values formatted as currency has Failed");
            }
        }catch (Exception e){
            ioExcel.updateColumnValueInExcelSheet(sheetName, scenarioID,"O_Test_Result", "Error occurred. Please check your script");
        }
    }
}
