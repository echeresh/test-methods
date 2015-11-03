package ru.nsu.fit.tests;
 
import ru.nsu.fit.services.calculator.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

/*
Legent:
Нужно посчить за сколько скачается файл заданного размера с ограниченным трафиком.
*/

@Title("Test for conversion of unit of inormation")
public class InformationUnitTest extends BaseTest {
    final String gbSize = "1.37";
    final String mbitPerSec = "5";

    @Test
    @Title("Test conversion of megabytes to bits")
    @Description("Convert GigaBytes -> bits")
    @Severity(SeverityLevel.BLOCKER)
    @Features("Multiplication")
    public void convertMegabytesToBits() {
        Calculator calculator = getCalculator();
        String expression = gbSize + " * 1024 * 1024 * 1024 * 8";
        EvalResult er = calculator.eval(expression, false);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "11768210391.04");
    }

    @Test(dependsOnMethods = "convertMegabytesToBits")
    @Title("Calculate download time")
    @Description("Calculate download of time using specific speed")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Multiplication", "Division"})
    public void calculateDownloadTime() {
        Calculator calculator = getCalculator();
        String expression = " / (" + mbitPerSec + " * 1024 * 1024)";
        EvalResult er = calculator.appendAndEval(expression, false);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "2244.608");
    }

    @Test(dependsOnMethods = "calculateDownloadTime")
    @Title("Convert download time")
    @Description("Convert download time from seconds to hours")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Multiplication", "Division"})
    public void convertTimeToHours() {
        Calculator calculator = getCalculator();
        String expression = " / 60 / 60";
        EvalResult er = calculator.appendAndEval(expression);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "0.6235022222222223");
    }
}
