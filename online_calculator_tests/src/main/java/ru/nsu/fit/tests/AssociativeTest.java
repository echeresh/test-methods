package ru.nsu.fit.tests;
 
import ru.nsu.fit.services.calculator.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

/*
Legent:
Студент не верит преподавателям о ассоциативности сложения.
*/

@Title("Associative property")
public class AssociativeTest extends BaseTest {
    @Test
    @Title("Associative")
    @Description("Origin expression")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Addition", "Subtraction"})
    public void testFirstCase() {
        Calculator calculator = getCalculator();
        String expression = "(123 + 456) - 987";
        EvalResult er = calculator.eval(expression, false);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "-408");
    }
 
    @Test(dependsOnMethods = "testFirstCase")
    @Title("Proof")
    @Description("Proof of associative property")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Addition", "Subtraction"})
    public void testSecondCase() {
        Calculator calculator = getCalculator();
        String expression = "123 + (456 - 987)";
        EvalResult er = calculator.eval(expression);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "-408");
    }
}
