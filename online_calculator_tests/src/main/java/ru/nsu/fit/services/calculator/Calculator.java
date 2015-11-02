package ru.nsu.fit.services.calculator;

import ru.nsu.fit.services.browser.*;
import org.openqa.selenium.By;
import ru.nsu.fit.shared.AllureUtils;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Calculator implements Closeable {
    private static final String PAGE_URL = "http://testmethods.tmweb.ru/";

    private static final By inputElement = By.xpath("//input[@type='text' and @name='Input']");
    private static final By equalElement = By.xpath("//input[@type='button' and @name='DoIt']");
    private static final By clearElement = By.xpath("//input[@type='button' and @name='clear']");

    private Browser browser;

    public Calculator() {
        browser = BrowserService.openNewBrowser();
        AllureUtils.saveTextLog("Open page");
        browser.openPage(PAGE_URL);
        AllureUtils.saveTextLog("The page was opened successfully");
        AllureUtils.saveImageAttach("Main screen", browser.makeScreenshot());
    }

    public EvalResult eval(String expression) {
        return eval(expression, true);
    }

    public EvalResult eval(String expression, boolean autoClear) {
        clearInput();
        setInput(expression);
        AllureUtils.saveImageAttach("The expression is typed", browser.makeScreenshot());
        EvalResultStatus status = EvalResultStatus.OK;
        if (!getInput().equals(expression)) {
            status = EvalResultStatus.MismatchedInput;
        }
        calcInput();
        AllureUtils.saveImageAttach("Result", browser.makeScreenshot());
        String result = getInput();
        if (autoClear) {
            clearInput();
        }
        return new EvalResult(result, status);
    }

    public void appendInput(String appendant) {
        browser.typeText(inputElement, getInput() + appendant);
    }

    public EvalResult appendAndEval(String appendant) {
        return eval(getInput() + appendant);
    }

    public EvalResult appendAndEval(String appendant, boolean autoClear) {
        return eval(getInput() + appendant, autoClear);
    }

    public void setInput(String expression) {
        browser.typeText(inputElement, expression);
    }

    public String getInput() {
        return browser.getValue(inputElement);
    }

    public void clearInput() {
        browser.click(clearElement);
    }

    public String calcInput() {
        browser.click(equalElement);
        return getInput();
    }

    @Override
    public void close() {
        browser.close();
        AllureUtils.saveTextLog("Browser was closed");
    }
}
