package ru.nsu.fit.tests;

import ru.nsu.fit.services.calculator.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

/*
Legent:
Бабушка решила посчитать не обманули ли ее в магазине.
*/

@Title("LU flops test")
public class FoodPriceTest extends BaseTest {

    private static final double epsilon = 1e-10;

    @Test
    @Title("Sum up prices")
    @Description("Check whether or not sum of prices is correct")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Addition", "Unary"})
    public void sumUpPrices() {
        Calculator calculator = getCalculator();
        // sum up prices
        String expression = "11.50 + 20.45 + 3.30 + 4.50 + 5.25 + 64.90 + 7.40 + 8.13 + 9.56";
        EvalResult er = calculator.eval(expression, false);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "134.99");
    }

    @Test(dependsOnMethods = "sumUpPrices")
    @Title("Correct money spent")
    @Description("Subtract debt")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Subtraction"})
    public void subtractDebt() {
        EvalResult er = calculator.appendAndEval(" - 35.53");
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertTrue(Math.abs(Double.parseDouble(er.getResult()) - 99.46) < epsilon);
    }
}
