package selenium; 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class NestedFrames{

    public static void main(String[] args) {
       

        // Initialize the Chrome driver
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to the specified URL
            driver.get("http://the-internet.herokuapp.com/nested_frames");

            // Switch to the "top" frame using CSS selector
            driver.switchTo().frame(driver.findElement(By.cssSelector("frame[name='frame-top']")));

            // Verify that there are three frames within the "top" frame
            List<WebElement> topFrames = driver.findElements(By.cssSelector("frame"));
            if (topFrames.size() == 3) {
                System.out.println("Top frame has 3 frames inside it.");
            } else {
                System.out.println("Top frame does not have 3 frames.");
            }

            // Switch to the "left" frame within the "top" frame and verify text "LEFT"
            driver.switchTo().frame(driver.findElement(By.cssSelector("frame[name='frame-left']")));
            String leftText = driver.findElement(By.tagName("body")).getText();
            if (leftText.equals("LEFT")) {
                System.out.println("Verified text in left frame: " + leftText);
            } else {
                System.out.println("Left frame text verification failed.");
            }
Thread.sleep(3000);
            // Switch back to the "top" frame
            driver.switchTo().parentFrame();
            Thread.sleep(3000);

            // Switch to the "middle" frame within the "top" frame and verify text "MIDDLE"
            driver.switchTo().frame(driver.findElement(By.cssSelector("frame[name='frame-middle']")));
            String middleText = driver.findElement(By.id("content")).getText();
            if (middleText.equals("MIDDLE")) {
                System.out.println("Verified text in middle frame: " + middleText);
            } else {
                System.out.println("Middle frame text verification failed.");
            }
            Thread.sleep(3000);

            // Switch back to the "top" frame
            driver.switchTo().parentFrame();
            
            Thread.sleep(3000);


            // Switch to the "right" frame within the "top" frame and verify text "RIGHT"
            driver.switchTo().frame(driver.findElement(By.cssSelector("frame[name='frame-right']")));
            String rightText = driver.findElement(By.tagName("body")).getText();
            if (rightText.equals("RIGHT")) {
                System.out.println("Verified text in right frame: " + rightText);
            } else {
                System.out.println("Right frame text verification failed.");
            }
            Thread.sleep(3000);

            // Switch back to the "top" frame, then to the main content
            driver.switchTo().defaultContent();
            Thread.sleep(3000);

            // Switch to the "bottom" frame and verify text "BOTTOM"
            driver.switchTo().frame(driver.findElement(By.cssSelector("frame[name='frame-bottom']")));
            String bottomText = driver.findElement(By.tagName("body")).getText();
            if (bottomText.equals("BOTTOM")) {
                System.out.println("Verified text in bottom frame: " + bottomText);
            } else {
                System.out.println("Bottom frame text verification failed.");
            }
            Thread.sleep(3000);

            // Switch back to the main content
            driver.switchTo().defaultContent();
            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }



	}


