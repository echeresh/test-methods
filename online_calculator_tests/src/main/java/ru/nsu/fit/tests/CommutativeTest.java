package ru.nsu.fit.tests;
 
import ru.nsu.fit.services.calculator.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

@Title("Test commutative property of addition")
public class CommutativeTest extends BaseTest {
    @Test
    @Title("Direct")
    @Description("Test direct order of sum")
    @Severity(SeverityLevel.BLOCKER)
    @Features("Addition")
    public void testDirectSum() {
        Calculator calculator = getCalculator();
        String expression = "123 + 456";
        EvalResult er = calculator.eval(expression, false);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "579");
    }

    @Test(dependsOnMethods = "testDirectSum")
    @Title("Reverse")
    @Description("Test reverse order of sum")
    @Severity(SeverityLevel.BLOCKER)
    @Features("Addition")
    public void testReverseSum() {
        EvalResult er = calculator.eval(expression);
        String expression = "456 + 123";
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "579");
    }
}
