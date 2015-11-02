package ru.nsu.fit.tests;
 
import ru.nsu.fit.services.calculator.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;


@Title("Test for conversion of unit of inormation")
public class InformationUnitTest extends BaseTest {
    @Test
    @Title("Test conversion of megabytes to bits")
    @Description("24.4567 Mb -> bits")
    @Severity(SeverityLevel.BLOCKER)
    @Features("Multiplication")
    public void convertMegabytesToBits() {
        Calculator calculator = getCalculator();
        String expression = "24.4567 * 1024 * 1024 * 8";
        EvalResult er = calculator.eval(expression, false);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "2051576692736");
    }
}