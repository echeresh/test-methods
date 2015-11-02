package ru.nsu.fit.tests;
 
import ru.nsu.fit.services.calculator.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;


@Title("Test for operations with binary numbers")
public class BinaryNumberTest extends BaseTest {
    @Test
    @Title("Binary addition")
    @Description("Sum up two binaries and check result")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Addition", "Binary"})
    public void addBinary() {
        Calculator calculator = getCalculator();
        String expression = "0b10 + 0b101";
        EvalResult er = calculator.eval(expression, false);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "7");
    }
 
    @Test(dependsOnMethods = "addBinary")
    @Title("Binary subtraction")
    @Description("Subtract binary number from decimal number")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Subtraction", "Binary"})
    public void subtractBinary() {
    	String expression = " - 0b111";
        EvalResult er = calculator.eval(expression);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "-7");
    }
}
