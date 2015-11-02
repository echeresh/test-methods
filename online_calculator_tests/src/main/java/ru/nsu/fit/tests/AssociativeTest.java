package ru.nsu.fit.tests;
 
import ru.nsu.fit.services.calculator.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

@Title("Associative property")
public class AssociativeTest extends BaseTest {
    @Test
    @Title("Associative")
    @Description("Origin expression")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Addition", "Subtraction"})
    public void solveTask() {
        Calculator calculator = getCalculator();
        // solve task
        String expression = "(123 + 456) - 987";
        EvalResult er = calculator.eval(expression, false);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "-408");
    }
 
    @Test(dependsOnMethods = "solveTask")
    @Title("Proof")
    @Description("Proof of associative property")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Addition", "Subtraction"})
    public void addOneMoreNumber() {
        EvalResult er = calculator.eval(expression);
        String expression = "123 + (456 - 987)";
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "-408");
    }
}