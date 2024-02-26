package utils;

import java.io.FileInputStream;

//import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class ExcelDataProvider 
{
	Properties tempProp;
	FileInputStream obtained;
	
    WebDriver driver;
	
	@BeforeTest
	public void setUp() throws IOException
	{
		tempProp=new Properties();
		 String path=System.getProperty("user.dir")+"//src//main//resources//PropertiesFiles//propertiesfile.properties";
		obtained=new FileInputStream(path);
		tempProp.load(obtained);
		obtained.close();
		String browser=tempProp.getProperty("browser");
		String url=tempProp.getProperty("url");
		System.out.println("Browser name..."+browser);
		if(browser.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
			driver.get(url);
	     }
		else if(browser.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
			driver.get(url);
		}
		
	}
	@Test(dataProvider="test1data")
	public void test1(String username,String password)
	
	{
//		System.out.println(username+" | "+password);
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(password);
		driver.findElement(By.xpath("//div//button[text()=' Login ']")).click();
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("window.scrollBy(0,750)", "");
//		driver.findElement(By.xpath("//button[@title='Apply Leave']")).click();
		
	}
//	
//	@Test
//	public void test2()  {
//		driver.findElement(By.xpath("//header//div//div//li")).click();
//		
//		driver.findElement(By.cssSelector("ul>li:nth-child(3) a ")).click();
////		
////     
//        
//
//	}
//	@Test
//	public void test2()
//	{
////	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
////	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//header//div//div//i)[1]"))).click();
//		//driver.findElement(By.xpath("(//header//div//div//i)[1]")).click();
//		driver.findElement(By.xpath("//button[@title='Apply Leave']")).click();
//	}
//  
    @DataProvider(name="test1data")
    public Object[][] getData()
    {
    	String excelPath="C:\\Workspace\\OrangeHRM\\src\\test\\resources\\testData\\data.xlsx";
    	Object data[][]=testData(excelPath,"loginPage");
    	return data;
    }
    
    
	public static Object[][] testData(String excelPath,String sheetName)
	{
		ExcelUtils excel=new ExcelUtils(excelPath,sheetName);
		int rowCount=excel.getRowCount();
		int colCount=excel.getColCount();
		
		Object data[][]=new Object[rowCount-1][colCount];
		
		for(int i=1;i<rowCount;i++)
		{
			for(int j=0;j<colCount;j++)
			{
				String cellData=excel.getCellData(i, j);
//				System.out.println(cellData+" | ");
				data[i-1][j]=cellData;
			}
//			System.out.println();
		}
		return data;
	}
	

}
