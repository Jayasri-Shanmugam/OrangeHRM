package utils;

public class ExcelUtilsDemo 
{

	public static void main(String[] args) 
	{
		
		ExcelUtils excel=new ExcelUtils("C:\\Workspace\\OrangeHRM\\src\\test\\resources\\testData\\data.xlsx", "loginPage");
        excel.getCellData(1,0);
        excel.getCellData(1, 1);
		
	}

}
