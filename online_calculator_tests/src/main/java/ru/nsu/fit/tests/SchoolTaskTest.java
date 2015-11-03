package ru.nsu.fit.tests;

import ru.nsu.fit.services.calculator.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

/*
Legent:
В школе задали сложить трехзначные вещественные числа.
*/

@Title("Test for school task")
public class SchoolTaskTest extends BaseTest {
    @Test
    @Title("School task")
    @Description("Do school task: additions of 3-digit numbers")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Addition", "Unary"})
    public void doTask() {
        Calculator calculator = getCalculator();
        // solve task
        String expression = "123 + 212 + -345.3 + 449 + -552 + 666 + 716 + 824 + -9.14";
        EvalResult er = calculator.eval(expression, false);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "2083.56");
    }

    @Test(dependsOnMethods = "doTask")
    @Title("Correct sum")
    @Description("Correct sum by forgotten summand")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Addition", "Unary"})
    public void addOneMoreNumber() {
        EvalResult er = calculator.appendAndEval(" + -2357.1");
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "-273.53999999999996");
    }
}
