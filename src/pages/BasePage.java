package pages;

import org.openqa.selenium.*;

class BasePage {
    WebDriver browser;

    void click(By locator) throws InterruptedException {
        int count = 0;
        int maxTries = 60;
        while(true) {
            try {
                browser.findElement(locator).click();
                return;
            } catch (NoSuchElementException | ElementNotVisibleException e) {
                Thread.sleep(500);
                if (++count == maxTries) throw e;
            }
        }
    }

    String getText(By locator) throws InterruptedException {
        int count = 0;
        int maxTries = 60;
        while(true) {
            try {
                return browser.findElement(locator).getText();
            } catch (NoSuchElementException | ElementNotVisibleException e) {
                Thread.sleep(500);
                if (++count == maxTries) throw e;
            }
        }
    }

    void sendKeys(By locator, String text) throws InterruptedException {
        int count = 0;
        int maxTries = 60;
        while(true) {
            try {
                browser.findElement(locator).sendKeys(text);
                return;
            } catch (NoSuchElementException | ElementNotVisibleException e) {
                Thread.sleep(500);
                if (++count == maxTries) throw e;
            }
        }
    }
}


