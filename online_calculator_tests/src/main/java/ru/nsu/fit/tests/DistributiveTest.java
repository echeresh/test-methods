package ru.nsu.fit.tests;
 
import ru.nsu.fit.services.calculator.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

@Title("Test distributive property of addition and multiplication")
public class DistributiveTest extends BaseTest {
    @Test
    @Title("Distributive")
    @Description("Origin expression")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Addition", "Multiplication"})
    public void testBaseExpression() {
        Calculator calculator = getCalculator();
        String expression = "(123 + 456) * 987";
        EvalResult er = calculator.eval(expression, false);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "571473");
    }
 
    @Test(dependsOnMethods = "testBaseExpression")
    @Title("Proof")
    @Description("Check of expression with disclosed brackets")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Addition", "Multiplication", "Brackets"})
    public void testDisclosedExpression() {
        EvalResult er = calculator.eval(expression);
        String expression = "(123 * 987) + (456 * 987)";
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "571473");
    }
}