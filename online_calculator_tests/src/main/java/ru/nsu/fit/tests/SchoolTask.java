package ru.nsu.fit.tests;

import ru.nsu.fit.services.calculator.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

@Title("Test for school task")
public class SchoolTaskTest extends BaseTest {
    @Test
    @Title("Solve school task")
    @Description("Solve school task: additions of 3-digit numbers")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Addition", "Unary"})
    public void solveTask() {
        Calculator calculator = getCalculator();
        // solve task
        String expression = "123 + 212 + -345.3 + 449 + -552 + 666 + 716 + 824 + -9.14";
        EvalResult er = calculator.eval(expression, false);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "1");
    }

    @Test(dependsOnMethods = "solveTask")
    @Title("Correct sum")
    @Description("Correct sum by forgotten summand")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Addition", "Unary"})
    public void addOneMoreNumber() {
        EvalResult er = calculator.appendAndEval(" + -2357.1");
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "1");
    }
}
