/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seleniumupfile;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author damien.gygi
 */
public class ViewFile {
    private static WebDriver driver;
    private static String baseUrl;
    private static boolean acceptNextAlert = true;
    private static StringBuffer verificationErrors = new StringBuffer();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "c:/temp/chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "http://localhost:8080/test_upfile/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        
        
        driver.get("http://localhost:8080/test_upfile/");
        Thread.sleep(1000);
        driver.findElement(By.linkText("Liste de fichier")).click();
        Thread.sleep(1000);
        /*driver.findElement(By.name("j_username")).clear();
        driver.findElement(By.name("j_username")).sendKeys("test");
        
        Thread.sleep(1000);
        driver.findElement(By.name("j_password")).clear();
        driver.findElement(By.name("j_password")).sendKeys("test");
        Thread.sleep(1000);
        
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        */
        //driver.close();
        //driver.quit();
    }
}
