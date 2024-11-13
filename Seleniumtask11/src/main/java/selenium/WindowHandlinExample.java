package selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class WindowHandlinExample {

    public static void main(String[] args) {
       

        // Initialize the Chrome driver
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to the specified URL
            driver.get("https://the-internet.herokuapp.com/windows");

            // Maximize the browser window
            driver.manage().window().maximize();
          Thread.sleep(5000);

            // Store the original window handle
            String originalWindow = driver.getWindowHandle();

            // Verify we are on the correct page by checking for the presence of the "Click Here" button
            WebElement clickHereButton = driver.findElement(By.linkText("Click Here"));
            clickHereButton.click();
Thread.sleep(5000);
            // Wait for the new window to appear
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            // Switch to the new window
            Set<String> allWindows = driver.getWindowHandles();
            for (String windowHandle : allWindows) {
                if (!windowHandle.equals(originalWindow)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }

            // Verify that the text "New Window" is present on the page
            WebElement newWindowText = driver.findElement(By.tagName("h3"));
            if (newWindowText.getText().equals("New Window")) {
                System.out.println("Text verification passed: " + newWindowText.getText());
            } else {
                System.out.println("Text verification failed.");
            }
Thread.sleep(3000);
            // Close the new window
            driver.close();
Thread.sleep(2000);
            // Switch back to the original window
            driver.switchTo().window(originalWindow);

            // Verify that we are back in the original window
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.equals("https://the-internet.herokuapp.com/windows")) {
                System.out.println("Switched back to the original window successfully.");
            } else {
                System.out.println("Failed to switch back to the original window.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}

