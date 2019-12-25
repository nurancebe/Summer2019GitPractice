package utils;


import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

    public class Driver {
        private static WebDriver driver;

        // if constructor is private you cannot do like this, ========>     Driver obj = new Driver()

        private Driver() {

        }
        //if switch statement complains on string parameter
        //change java version to 7+, better at least 8
        //File--> Project Structure--> Set Project language level to at least 8 or above

        public static WebDriver get() {

            //if webdriver object was not created yet
            if (driver == null) {

                //create webdriver object based on browser value from properties file

                String browser = ConfigurationReader.getProperty("browser");

                switch (browser) {
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver();
                        break;
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        driver = new FirefoxDriver();
                        break;
                    default:

                        //if browser type is wrong, stop test and ; throw exception.
                        //no browser will be opened

                        throw new RuntimeException("Wrong browser type!");
                }
            }
            //if webdriver object was created - you can use it

            return driver;
        }

        public static void close() {

            //if driver still exist

            if (driver != null) {

                //close all browsers

                driver.quit();

                //destroy driver object, ready for gc

                driver = null;
            }
        }
    }