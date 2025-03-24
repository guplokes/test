package com.cat.utilities;

import java.awt.AWTException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;

import com.cat.testCases.BaseClass;

public class ElementActions extends BaseClass {

	private WebDriver driver;

	public ElementActions(WebDriver driver) {
		this.driver = driver;
	}

	// this method enter data in a textbox after clearing the text present in it.(no
	// scrolling to element present)
	public void enterDataInTextbox(WebElement element, String elementName, String value) {
		wait.waitForElementToBeClickable(element);
		doubleClickElement(element, elementName);
		try {
			handleKeyboardEvent.removeText();
			Thread.sleep(400);
		}
			catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Actions performAct = new Actions(driver);
		performAct.sendKeys(element, value).build().perform();
		logger.info(elementName + " : " + value + " is entered successfully: Passed");
	}

	public void enterData(WebElement element, String ElementName, String Value) {
		try {
			wait.waitForElementToBeClickable(element);
			doubleClickElement(element, ElementName);
			element.sendKeys(Value);
			logger.info(ElementName + " is entered successfully.");
		} catch (Exception ex) {
			logger.fail(ElementName + " is not entered. \n" + ex);
		}
	}

	// there is no waitForElement is used, method should be used while scrolling the
	// table to enter the values
	public void enterDataAfterScroll(WebElement element, String ElementName, String Value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		Actions performAct = new Actions(driver);
		performAct.sendKeys(element, Value).build().perform();
		logger.info(ElementName + " is entered successfully.");
	}

	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void enterDataByJS(WebElement element, String ElementName, String Value) {
		try {
			Thread.sleep(200);
			wait.waitForElement(element, ElementName);

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			Actions performAct = new Actions(driver).doubleClick(element);

			performAct.build().perform();
			performAct.sendKeys(element, Value).build().perform();
			logger.info(ElementName + " is entered successfully.");
		}

		catch (ElementNotVisibleException ex) {
			logger.fail(ElementName + " is not found on the web page \n" + ex);
		}

		catch (Exception ex) {
			logger.fail("There are some issue on the element.." + ex);
		}
	}

	public void actionSendKeys(WebElement element, String valueRequired) {
		try {
			Actions performAct = new Actions(driver).doubleClick(element);
			performAct.build().perform();
			performAct.sendKeys(element, valueRequired).build().perform();
			logger.info(valueRequired + " is entered successfully.");
		}

		catch (ElementNotVisibleException ex) {
			logger.fail("Test is interrupted because element " + element + "is not visible on the web page..." + ex);
		}

		catch (Exception ex) {
			logger.fail("There are some issue on the element.." + ex);
		}
	}

	public void clickElement(WebElement element, String ElementName) {
		try {
			Thread.sleep(1000);
			wait.waitForElement(element, ElementName);
			element.click();
			logger.info(ElementName + " is clicked.");
		}

		catch (ElementNotInteractableException ex) {
			logger.fail(ElementName + "Field is not interactable on the web page.\n" + ex);
		}

		catch (Exception ex) {
			logger.fail("There are some issue on the " + ElementName + " . \n" + ex);
			throw new SkipException("This test is skipped due to element " + ElementName + " is not clickable");
		}
	}

	public void enableRadioButton(WebElement element, String ElementName) {
		try {
			Thread.sleep(2000);
			wait.waitForElement(element, ElementName);
			element.click();
			logger.info(ElementName + " is enabled successfully.");
		}

		catch (ElementNotInteractableException ex) {
			logger.fail(ElementName + "Field is not interactable on the web page.\n" + ex);
		}

		catch (Exception ex) {
			logger.fail("There are some issue on the " + ElementName + " . \n" + ex);
			throw new SkipException("This test is skipped due to element " + ElementName + " is not clickable");
		}
	}

	public void disableRadioButton(WebElement element, String ElementName) {
		try {
			Thread.sleep(2000);
			wait.waitForElement(element, ElementName);
			element.click();
			logger.info(ElementName + " is disabled successfully.");
		}

		catch (ElementNotInteractableException ex) {
			logger.fail(ElementName + "Field is not interactable on the web page.\n" + ex);
		}

		catch (Exception ex) {
			logger.fail("There are some issue on the " + ElementName + " . \n" + ex);
			throw new SkipException("This test is skipped due to element " + ElementName + " is not clickable");
		}
	}

	public void clickTab(WebElement element, String ElementName) {
		try {
			Thread.sleep(5000);
			wait.waitForElement(element, ElementName);
			element.click();
			logger.info(ElementName + " is clicked.");
		}

		catch (ElementNotInteractableException ex) {
			logger.fail(ElementName + "Tab is not interactable on the web page.\n" + ex);
		}

		catch (Exception ex) {
			logger.fail("There are some issue on the " + ElementName + " Tab. \n" + ex);
			throw new SkipException("This test is skipped due to element " + ElementName + " is not clickable");
		}
	}

	public void doubleClickElement(WebElement element, String ElementName) {
		try {
			Actions performAct = new Actions(driver).doubleClick(element);
			performAct.build().perform();
		} catch (ElementNotVisibleException ex) {
			logger.fail(
					"Test is interrupted because element " + ElementName + "is not visible on the web page... \n" + ex);
		}

		catch (Exception ex) {
			logger.fail("There are some issue on the element. That's why entire test is skipped. \n" + ex);
			throw new SkipException("This test is skipped due to element " + ElementName + " is not clickable");
		}
	}

	public void moveToElement(WebElement element) {
		Actions performAct = new Actions(driver).moveToElement(element);
		performAct.build().perform();
	}

	public void clickElementByJS(WebElement element, String ElementName) {
		try {
			Thread.sleep(500);
			wait.waitForElement(element, ElementName);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
			logger.info(ElementName + " is clicked.");
		}

		catch (ElementNotVisibleException ex) {
			logger.fail(
					"Test is interrupted because element " + ElementName + "is not visible on the web page... \n" + ex);
		}

		catch (Exception ex) {
			logger.fail("There are some issue on the element. That's why entire test is skipped. \n" + ex);
			throw new SkipException("This test is skipped due to element " + ElementName + " is not clickable");
		}
	}

	public void selectEntityValue(WebElement entityDD, String entity) {
		try {

			enterDataInTextbox(entityDD, "entity", entity);
			Thread.sleep(700);
			WebElement element = driver.findElement(By.xpath(
					"(//div[@class='dx-item-content dx-list-item-content'][normalize-space()='" + entity + "'])[1]")); // changes
			wait.waitForElementToBeClickable(element);
			clickElementByJS(element, "Entity ");

			logger.info(entity + " is selected successfully.");
		} catch (ElementNotVisibleException ex) {
			logger.fail("Test is interrupted because element is not visible on the web page... \n" + ex);
		}

		catch (Exception ex) {
			logger.fail("There are some issue on the element. That's why entire test is skipped. \n" + ex);
			throw new SkipException("This test is skipped due to element is not clickable");
		}

	}

	public void selectOperatorValue(WebElement operatorDD, String operator) {
		Select options = new Select(operatorDD);
		options.selectByVisibleText(operator);
		logger.info(operator + " is selected successfully.");

	}

	public void selectDate(WebElement element, String ElementName) {

		try {
//			String date = helper.getDateAfterAddingDays();
			String date = helper.getCurrentDate();			
			wait.waitForElement(element, ElementName);
			clickElementByJS(element, ElementName);
			String dateparam = "(//*[contains(@aria-label,'" + date + "')])" + "[1]";
			driver.findElement(By.xpath(dateparam)).click();
			logger.pass(ElementName + " selected successfully");
		} catch (ElementNotVisibleException ex) {
			logger.fail(ElementName + "Field is not found on the web page. \n" + ex);
		} catch (Exception ex) {
			logger.fail("Unable to select the " + ElementName + ". \n" + ex);

		}
	}

	public void selectDDOptions(WebElement ddElement, String ddName, String ddOption) {
		try {
			Thread.sleep(100);
			wait.waitForElement(ddElement, ddName);

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", ddElement);

			Actions performAct = new Actions(driver); // .doubleClick(ddElement);{modified for lms:policy blanket add
														// layer}
			performAct.build().perform();
			performAct.sendKeys(ddElement, ddOption).build().perform();
			Thread.sleep(500);

			ddOption = ddOption.trim();

			String dynamicXpath = "//div[contains(text(),'" + ddOption + "')]";

			WebElement element = driver.findElement(By.xpath(dynamicXpath));

			js.executeScript("arguments[0].click()", element);

			logger.info(ddOption + ": is selected successfully.");
		} catch (ElementNotVisibleException ex) {
			logger.fail(ddOption + "Field is not found on the web page. \n" + ex);
		} catch (Exception ex) {
			logger.fail("Unable to select the value of " + ddOption + " \n" + ex);
		}
	}

//this method just click on dd icon and select dd value	(for those dropdown closes automatically)
	public void selectDropdown(WebElement ddElement, String ddName, String ddOption) {
		clickElementByJS(ddElement, ddName);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement option = driver.findElement(By.xpath("//div[contains(text(),'" + ddOption + "')]"));
		clickElementByJS(option, ddOption);
	}

	// this method just click on dd icon and select dd value(for those dropdown who
	// doesn't close automatically)
	public void selectDropdownManual(WebElement ddElement, String ddName, String ddOption) {
		scrollIntoView(ddElement);
		clickElementByJS(ddElement, ddName);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement option = driver.findElement(By.xpath("//div[contains(text(),'" + ddOption + "')]"));
		clickElementByJS(option, ddOption);
		clickElementByJS(ddElement, ddName);// #new line for workTray
		// clickElement(option, ddOption);
	}

	public boolean selectMultiCheckboxDDWithSearchbox(WebElement ddElement, WebElement applyBtn, WebElement cancelBtn,
			WebElement searchBox, String[] values, String ddName) {
		List<WebElement> checkbox;
		boolean flag = false;
		clickElementByJS(ddElement, ddName);
		WebElement selectAllCheckbox = driver.findElement(By.xpath("//div[@class='dx-list-select-all']/div[1]"));
		if (selectAllCheckbox.getAttribute("aria-checked").equals("true"))
			clickElementByJS(selectAllCheckbox, "Unselecting All");
		for (int i = 0; i < values.length; i++) {
			enterDataInTextbox(searchBox, "Search Column", values[i]);
			checkbox = driver.findElements(By.xpath(
					"//div[@class='dx-list-select-checkbox dx-show-invalid-badge dx-checkbox dx-widget']//span[@class='dx-checkbox-icon']"));
			if (checkbox.size() == 0) {
				clickElementByJS(cancelBtn, "Cancel Btn");
				logger.info(ddName + " is cancelled.");
				return flag;
			}
			clickElementByJS(checkbox.get(0), "Select column");
		}
		clickElementByJS(applyBtn, "Apply " + ddName);
		flag = true;
		logger.info(ddName + " is applied successfully.");
		return flag;

	}

	public void selectMultiSelectDDOptions(WebElement ddElement, String ddName, String ddOptions)
			throws AWTException, InterruptedException {
		try {
			String[] tempArr = ddOptions.split("[,]", 0);
			doubleClickElement(ddElement, ddName);
			wait.waitForElement(ddElement, ddName);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", ddElement);
			for (String myStr : tempArr) {
				handleKeyboardEvent.enterText(myStr);
				List<WebElement> element = driver.findElements(By.xpath("//*[contains(text(),'" + myStr + "')]"));
				js.executeScript("arguments[0].click();", element.get(0));
				handleKeyboardEvent.removeText();
			}
			logger.info("All " + ddName + " are selected successfully.");
		} catch (ElementNotVisibleException ex) {
			logger.fail(ddName + "Field is not found on the web page. \n" + ex);
		} catch (Exception ex) {
			logger.fail("Unable to select the value of " + ddName + " \n" + ex);
		}
	}

	/*
	 * this method to select multiple dropdown options where You can not type the
	 * text
	 */
	public void multipleDropdownOptions(WebElement ddElement, String ddName, String ddOptions) {
		try {
			String[] tempArr = ddOptions.split("[,]", 0);
			wait.waitForElement(ddElement, ddName);
			clickElementByJS(ddElement, ddName);

			for (String myStr : tempArr) {
				System.out.println(myStr);
				List<WebElement> element = driver.findElements(By.xpath("//div[contains(text(),'" + myStr + "')]"));
				this.scrollIntoView(element.get(0));
				clickElementByJS(element.get(0), myStr);
				Thread.sleep(200);
			}
			clickElementByJS(ddElement, ddName); // for closing the dropdown in manage user
			logger.info("All " + ddName + " are selected successfully.");
		} catch (ElementNotVisibleException ex) {
			logger.fail(ddName + "Field is not found on the web page. \n" + ex);
		} catch (Exception ex) {
			logger.fail("Unable to select the value of " + ddName + " \n" + ex);
		}
	}

	public void searchElement(String element, String elementName) {
		wait.waitForElement(driver.findElement(By.xpath("//*[text()='" + element + "']")));
		if (driver.findElement(By.xpath("//*[text()='" + element + "']")) != null) {
			logger.pass(elementName + " " + element + " is successfully search on the page.");
		} else {
			logger.fail("Account is not present on the page.");
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
			logger.fail(ex);
		}
	}

	// to check the page title,: assert statement should be in respective @Test
	public boolean verifyWebPageTitle(String expTitle, String pageName) {
		wait.waitForTitle(expTitle);
		String actPageTitle = driver.getTitle();

		try {
			if (actPageTitle.equalsIgnoreCase(expTitle)) {
				logger.info("User is successfully navigated to " + pageName);
				return true;
			} else {
				logger.info(pageName + " is not get open..");
			}
			// Assert.assertTrue(actPageTitle.contains(expTitle));
		} catch (Exception ex) {
			logger.fail(ex);
		}

		return false;
	}

	public boolean verifyMessage(WebElement element, String expMsg, String msgElement) {
		String actualMsg = element.getText();

		try {
			if (actualMsg.equalsIgnoreCase(expMsg)) {
				logger.pass(msgElement + "is successfull.");
				return true;
			} else {
				logger.fail("Test case failed : " + msgElement + " is not happen.");
			}
			// Assert.assertTrue(actualMsg.contains(expMsg));
		} catch (Exception ex) {
			logger.fail(ex);
		}
		return false;
	}

	public boolean verifyText(String actualText, String expectedText) {
		boolean flag = expectedText.equalsIgnoreCase(actualText);
		if (flag)
			logger.info(actualText + "is same as" + expectedText);
		else
			logger.info(actualText + "is different from" + expectedText);

		return flag;
	}

	public void clearElementText(WebElement element) {
		// String del = Keys.chord(Keys.CONTROL, "a");
		Actions performAct = new Actions(driver).doubleClick(element);
		performAct.build().perform();
		try {
			Thread.sleep(500);
			// Robot class Helper
			handleKeyboardEvent.removeText();
			logger.info("Textbox's text is cleared");
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
