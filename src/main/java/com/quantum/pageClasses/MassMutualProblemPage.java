package com.quantum.pageClasses;

import com.quantum.utilities.IOExcelActivity;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;

public class MassMutualProblemPage {
    @FindBy(id = "lbl_val_1")
    WebElement value1Label;
    @FindBy(id = "lbl_val_2")
    WebElement value2Label;
    @FindBy(id = "lbl_val_3")
    WebElement value3Label;
    @FindBy(id = "lbl_val_4")
    WebElement value4Label;
    @FindBy(id = "lbl_val_5")
    WebElement value5Label;
    @FindBy(id = "lbl_ttl_val")
    WebElement valueTotalLabel;
    @FindBy(id = "txt_val_1")
    WebElement value1TextBox;
    @FindBy(id = "txt_val_2")
    WebElement value2TextBox;
    @FindBy(id = "txt_val_3")
    WebElement value3TextBox;
    @FindBy(id = "txt_val_4")
    WebElement value4TextBox;
    @FindBy(id = "txt_val_5")
    WebElement value5TextBox;
    @FindBy(id = "txt_ttl_val")
    WebElement valueTotalTextBox;
    IOExcelActivity ioExcel = new IOExcelActivity();

    public boolean validateCountOfValues(WebDriver driver, String scenarioID, String sheetName, HashMap<String, String> problemDetails) throws Exception {
        WebDriverWait innerwait = new WebDriverWait(driver, 90);
        innerwait.until(ExpectedConditions.elementToBeClickable(value1TextBox));

//      Storing all the Label elements in List and getting count
        List<WebElement> allValuesLabel = driver.findElements(By.xpath("//*[starts-with(@id,'lbl_val_')]"));
        int countLabel = allValuesLabel.size();

//      Storing all the Text elements in List and getting count
        List<WebElement> allValuesTextBox = driver.findElements(By.xpath("//*[starts-with(@id,'txt_val_')]"));
        int countTextBox = allValuesTextBox.size();

//      Updating Test Data Excel with the fetched count of label and Value TextBox from the Page
        ioExcel.updateColumnValueInExcelSheet(sheetName, scenarioID, "O_ActualValueCount", "Label = " + countLabel + " & Value = " + countTextBox);
        String expectedCount = problemDetails.get("I_ExpectedValueCount");
        int expCount = Integer.valueOf(expectedCount);

        if (countLabel == expCount && countTextBox == expCount)
            return true;
        else
            return false;
    }

    public boolean validateTheValues(WebDriver driver, String scenarioID, String sheetName, HashMap<String, String> problemDetails, int number) {
        WebDriverWait innerwait = new WebDriverWait(driver, 90);
        innerwait.until(ExpectedConditions.elementToBeClickable(value1TextBox));
//      Getting the Amount from Value TextBox and removing "$" and   ","
        String value1 = value1TextBox.getText().replace("$","").replace(",","");
        double val1 = Double.valueOf(value1);

        String value2 = value2TextBox.getText().replace("$","").replace(",","");
        double val2 = Double.valueOf(value2);

        String value3 = value3TextBox.getText().replace("$","").replace(",","");
        double val3 = Double.valueOf(value3);

        String value4 = value4TextBox.getText().replace("$","").replace(",","");
        double val4 = Double.valueOf(value4);

        String value5 = value5TextBox.getText().replace("$","").replace(",","");
        double val5 = Double.valueOf(value5);

//      Verifying if Amount values is greater than Zero
        boolean isGreaterZero = true;
        if(val1<=number)
            isGreaterZero = false;
        if(val2<=number)
            isGreaterZero = false;
        if(val3<=number)
            isGreaterZero = false;
        if(val4<=number)
            isGreaterZero = false;
        if(val5<=number)
            isGreaterZero = false;

        if(isGreaterZero)
            return true;
        else
            return false;

    }

    public boolean validateTotalBalance(WebDriver driver, String scenarioID, String sheetName, HashMap<String, String> problemDetails) throws Exception {
        WebDriverWait innerwait = new WebDriverWait(driver, 90);
        innerwait.until(ExpectedConditions.elementToBeClickable(value1TextBox));
//      Getting the Amount from Value TextBox and removing "$" and   ","
        String value1 = value1TextBox.getText().replace("$","").replace(",","");
        double val1 = Double.valueOf(value1);

        String value2 = value2TextBox.getText().replace("$","").replace(",","");
        double val2 = Double.valueOf(value2);

        String value3 = value3TextBox.getText().replace("$","").replace(",","");
        double val3 = Double.valueOf(value3);

        String value4 = value4TextBox.getText().replace("$","").replace(",","");
        double val4 = Double.valueOf(value4);

        String value5 = value5TextBox.getText().replace("$","").replace(",","");
        double val5 = Double.valueOf(value5);

        String valueTotal = valueTotalTextBox.getText().replace("$","").replace(",","");
        double valTot = Double.valueOf(valueTotal);


        double actualTotal = val1+val2+val3+val4+val5 ;

//      Updating Test Data Excel sheet with Actual total and value of amount from Total Value Text Box
        ioExcel.updateColumnValueInExcelSheet(sheetName, scenarioID, "O_ActualTotal",String.valueOf(actualTotal));
        ioExcel.updateColumnValueInExcelSheet(sheetName, scenarioID, "O_ExpectedTotal",String.valueOf(valTot));

        if (valTot == actualTotal)
            return true;
        else
            return false;
    }

    public boolean validateValueCurrency(WebDriver driver, String scenarioID, String sheetName, HashMap<String, String> problemDetails) throws Exception {
        WebDriverWait innerwait = new WebDriverWait(driver, 90);
        innerwait.until(ExpectedConditions.elementToBeClickable(value1TextBox));
//      Fetching the Expected currency Value from Test Data Excel sheet
        String expectedCurrency = problemDetails.get("I_ExpectedCurrency");

        String value1 = value1TextBox.getText().replace(" ","");
        String value2 = value2TextBox.getText().replace(" ","");
        String value3 = value3TextBox.getText().replace(" ","");
        String value4 = value4TextBox.getText().replace(" ","");
        String value5 = value5TextBox.getText().replace(" ","");
        String valueTotal = valueTotalTextBox.getText().replace(" ","");


//      Validating if Amount in the Value Text Box starts with "$"
        boolean startsWithCurr = true;
//        if(value1.charAt(0) != expectedCurrency.charAt(0))
//            startsWithCurr = false;
        if (!value1.startsWith(expectedCurrency))
            startsWithCurr = false;

        if (!value2.startsWith(expectedCurrency))
            startsWithCurr = false;

        if (!value3.startsWith(expectedCurrency))
            startsWithCurr = false;

        if (!value4.startsWith(expectedCurrency))
            startsWithCurr = false;

        if (!value5.startsWith(expectedCurrency))
            startsWithCurr = false;

        if (!valueTotal.startsWith(expectedCurrency))
            startsWithCurr = false;

        ioExcel.updateColumnValueInExcelSheet(sheetName, scenarioID, "O_ActualCurrency", "Value1 : "+value1.charAt(0)+"\nValue2 : "+value2.charAt(0)+"\nValue3 : "+value3.charAt(0)+"\nValue4 : "+value4.charAt(0)+"\nValue5 : "+value5.charAt(0)+"\nTotal Balance : "+valueTotal.charAt(0));
        if(startsWithCurr)
            return true;
        else
            return false;
    }
}
