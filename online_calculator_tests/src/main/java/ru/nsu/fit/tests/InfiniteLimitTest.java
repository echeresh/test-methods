package ru.nsu.fit.tests;
 
import ru.nsu.fit.services.calculator.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

@Title("Compute limit of x^2 / (x + 1) when x -> +inf")
public class InfiniteLimitTest extends BaseTest {
    @Test
    @Title("Direct expression for limit")
    @Description("Compute limit with direct substituion")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Addition", "Multiplication", "Division"})
    public void computeLimitDirectly() {
        Calculator calculator = getCalculator();
        String expression = "Infinity * Infinity / (Infinity + 1)";
        EvalResult er = calculator.eval(expression, false);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "NaN");
    }
 
    @Test
    @Title("Set NaN to zero")
    @Description("Compute limit with direct substituion")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Addition", "Multiplication", "Division"})
    public void tryToSetResultToZero() {
        Calculator calculator = getCalculator();
        String expression =  " * 0";
        EvalResult er = calculator.appendAndEval(expression);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "NaN");
    }

    @Test(dependsOnMethods = "tryToSetResultToZero")
    @Title("Subtraction")
    @Description("Proof of distributive property")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Addition", "Multiplication", "Division"})
    public void computeLimitAftersimplification() {
        EvalResult er = calculator.eval(expression);
        String expression = "Infinity / (1 + 1 / Infinity)";
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "Infinity");
    }
}
