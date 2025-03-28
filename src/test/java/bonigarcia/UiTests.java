package bonigarcia;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static bonigarcia.Constants.BASE_URL;
import static bonigarcia.Constants.FRAME_HEADER_NAME;
import static bonigarcia.Constants.LANG_ENG;
import static bonigarcia.Constants.LINK_XPATH;
import static bonigarcia.Constants.TITLE_XPATH;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTests {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(LANG_ENG);
        driver = new ChromeDriver(options);
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @ParameterizedTest
    @CsvSource({
            "Chapter 3. WebDriver Fundamentals, web-form.html, Web form",
            "Chapter 3. WebDriver Fundamentals, navigation1.html, Navigation example",
            "Chapter 3. WebDriver Fundamentals, dropdown-menu.html, Dropdown menu",
            "Chapter 3. WebDriver Fundamentals, mouse-over.html, Mouse over",
            "Chapter 3. WebDriver Fundamentals, drag-and-drop.html, Drag and drop",
            "Chapter 3. WebDriver Fundamentals, draw-in-canvas.html, Drawing in canvas",
            "Chapter 3. WebDriver Fundamentals, loading-images.html, Loading images",
            "Chapter 3. WebDriver Fundamentals, slow-calculator.html, Slow calculator",

            "Chapter 4. Browser-Agnostic Features, long-page.html, This is a long page",
            "Chapter 4. Browser-Agnostic Features, infinite-scroll.html, Infinite scroll",
            "Chapter 4. Browser-Agnostic Features, shadow-dom.html, Shadow DOM",
            "Chapter 4. Browser-Agnostic Features, cookies.html, Cookies",
            "Chapter 4. Browser-Agnostic Features, frames.html, Frames",
            "Chapter 4. Browser-Agnostic Features, iframes.html, IFrame",
            "Chapter 4. Browser-Agnostic Features, dialog-boxes.html, Dialog boxes",
            "Chapter 4. Browser-Agnostic Features, web-storage.html, Web storage",

            "Chapter 5. Browser-Specific Manipulation, geolocation.html, Geolocation",
            "Chapter 5. Browser-Specific Manipulation, notifications.html, Notifications",
            "Chapter 5. Browser-Specific Manipulation, get-user-media.html, Get user media",
            "Chapter 5. Browser-Specific Manipulation, multilanguage.html, Multilanguage page",
            "Chapter 5. Browser-Specific Manipulation, console-logs.html, Console logs",

            "Chapter 7. The Page Object Model (POM), login-form.html, Login form",
            "Chapter 7. The Page Object Model (POM), login-slow.html, Slow login form",

            "Chapter 8. Testing Framework Specifics, random-calculator.html, Random calculator",

            "Chapter 9. Third-Party Integrations, download.html, Download files",
            "Chapter 9. Third-Party Integrations, ab-testing.html, A/B Testing",
            "Chapter 9. Third-Party Integrations, data-types.html, Data types",
    })
    public void shouldOpenTheCorrectPagesUsingLinks(String chapter, String href, String pageTitle) {
        String linkXpath = String.format(LINK_XPATH, chapter, href);
        driver.findElement(By.xpath(linkXpath)).click();
        switchToFrameIfNeeded();
        WebElement pageName = driver.findElement(By.xpath(TITLE_XPATH));

        assertEquals(BASE_URL + href, driver.getCurrentUrl());
        assertEquals(pageTitle, pageName.getText());
    }

    private void switchToFrameIfNeeded() {
        if (!driver.findElements(By.name(FRAME_HEADER_NAME)).isEmpty()) {
            driver.switchTo().frame(FRAME_HEADER_NAME);
        }
    }
}
