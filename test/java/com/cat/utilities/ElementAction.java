package com.cat.utilities;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;

import com.cat.testCases.BaseClass;

public class ElementAction extends BaseClass {

	private WebDriver driver;

	public ElementAction(WebDriver driver) {
		this.driver = driver;
	}

	public void selectDDOptions(WebElement element, String ddName, String ddOptions) {
		clickElementByJS(element);
		driver.findElement(By.xpath("//div[contains(text(),'" + ddOptions + "')]")).click();
		logger.info(ddName + " " + ddOptions + " is selected successfully");
	}

	public void selectGridDDOptions(WebElement element, String ddName, String ddOptions) {
		clickElementByJS(element);
		eleAct.actionSendKeys(element, ddOptions);
		List<WebElement> ele = driver.findElements(By.xpath("//div[text()='" + ddOptions + "']"));
		eleAct.clickElementByJS(ele.get(0));
		logger.info(ddName + " is selected successfully: Passed");
	}

	public void enterData(WebElement element, String Value) {
		element.sendKeys(Value);
		logger.info(element + " is entered successfully.");
	}

	public void enterDataInTextbox(WebElement element, String Key, String value)
			throws AWTException, InterruptedException {
		moveToAnyElement(element);
		handleKeyboardEvent.removeText();
		eleAct.actionSendKeys(element, value);
		logger.info(Key + " : " + value + " is entered successfully: Passed");
	}

	public void clickElement(WebElement element, String ElementName) {
		try {
			element.click();
			logger.info(ElementName + " is clicked.");
		}

		catch (ElementNotVisibleException ex) {
			logger.fail("Test is interrupted because element " + element + "is not visible on the web page..." + ex);
		}

		catch (Exception ex) {
			logger.fail("There are some issue on the element. That's why entire test is skipped." + ex);
			throw new SkipException("This test is skipped due to element " + ElementName + " is not clickable");
		}
	}

	public void clickElements(List<WebElement> element, String ElementName) {
		try {
			element.get(1).click();
			logger.info(ElementName + " is clicked.");
		}

		catch (ElementNotVisibleException ex) {
			logger.fail("Test is interrupted because element " + element + "is not visible on the web page..." + ex);
		}

		catch (Exception ex) {
			logger.fail("There are some issue on the element. That's why entire test is skipped." + ex);
			throw new SkipException("This test is skipped due to element " + ElementName + " is not clickable");
		}
	}

	public void enterDetails(WebElement Element, String Data) throws InterruptedException {
		Actions actions = new Actions(driver);
		actions.moveToElement(Element);
		actions.click();
		actions.sendKeys(Data);
		actions.build().perform();
	}

	public void verifyTest(String OrgText, String ExpText) {
		Assert.assertEquals(OrgText, ExpText);
	}

	public void verifyTest(String OrgText, String ExpText, String msg) {
		try {
			Assert.assertTrue(OrgText.contains(ExpText), msg);
		} catch (Exception ex) {
			// System.out.println(ex);
			logger.info(ex);
		}
	}

	public void verifyWebPage(String expTitle, String pageName) {
		String actPageTitle = driver.getTitle();
		try {
			if (actPageTitle.equalsIgnoreCase(expTitle)) {
				logger.info("User is successfully navigated to " + pageName);
			} else {
				logger.info(pageName + " is not get open..");
			}
			Assert.assertTrue(actPageTitle.contains(expTitle));
		} catch (Exception ex) {
			logger.info(ex);
		}
	}

	public void clickElementByJS(WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
		}

		catch (ElementNotVisibleException ex) {
			logger.fail("Test is interrupted because element " + element + "is not visible on the web page..." + ex);
		}

		catch (Exception ex) {
			logger.fail("There are some issue on the element.." + ex);
		}
	}

	public void clearElement(WebElement element) {
		String b = Keys.BACK_SPACE.toString();
		Actions performAct = new Actions(driver).doubleClick(element);
		performAct.build().perform();
		performAct.sendKeys(element, b).build().perform();
	}

	public void clearElements(WebElement element) {
		String del = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;
		Actions performAct = new Actions(driver).doubleClick(element);
		performAct.build().perform();
		performAct.sendKeys(element, del).build().perform();
	}

	public void clearElementText(WebElement element) {
		String del = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;
		Actions performAct = new Actions(driver);// .doubleClick(element);
		// performAct.build().perform();
		performAct.sendKeys(element, del).build().perform();
	}

	public void moveToAnyElement(WebElement element) {
		Actions performAct = new Actions(driver).doubleClick(element);
		performAct.build().perform();
	}

	public void actionSendKeys(WebElement element, String valueRequired) {
		try {
			Actions performAct = new Actions(driver).doubleClick(element);
			performAct.build().perform();
			performAct.sendKeys(element, valueRequired).build().perform();
		}

		catch (ElementNotVisibleException ex) {
			logger.fail("Test is interrupted because element " + element + "is not visible on the web page..." + ex);
		}

		catch (Exception ex) {
			logger.fail("There are some issue on the element.." + ex);
		}
	}

	public void actionSendKey(WebElement element, String valueRequired) {
		try {
			Actions performAct = new Actions(driver).doubleClick(element);
			// performAct.build().perform();
			performAct.sendKeys(element, valueRequired).build().perform();
		}

		catch (ElementNotVisibleException ex) {
			logger.fail("Test is interrupted because element " + element + "is not visible on the web page..." + ex);
		}

		catch (Exception ex) {
			logger.fail("There are some issue on the element.." + ex);
		}
	}

	public void clickElementByLocator(WebElement element) {
		try {
			element.click();
		}

		catch (ElementNotVisibleException ex) {
			logger.fail("Test is interrupted because element " + element + "is not visible on the web page..." + ex);
		}

		catch (Exception ex) {
			logger.fail("There are some issue on the element.." + ex);
		}
	}

	public void waitForElement(WebElement element, long timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
		//Log.info("element found..." + element.getText());
	}

	/*
	 * @SuppressWarnings("deprecation") public void
	 * waitForElementTimePoll(WebElement element, int timeOut, int pollingTime) {
	 * WebDriverWait wait = new WebDriverWait(driver, timeOut);
	 * wait.pollingEvery(pollingTime,
	 * TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
	 * .ignoring(StaleElementReferenceException.class).ignoring(WebDriverException.
	 * class) .withMessage("Couldn't find " +
	 * element).until(ExpectedConditions.visibilityOf(element));
	 * System.out.println("Element visible." + element); }
	 */

}
