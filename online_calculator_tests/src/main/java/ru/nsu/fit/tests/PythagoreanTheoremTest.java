package ru.nsu.fit.tests;

import ru.nsu.fit.services.calculator.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

@Title("Test for mathematical school task on Pythagorean theorem")
public class PythagoreanTheoremTest extends BaseTest {
    private final String aExpression = "0.8";
    private final String bExpression = "0.6";
    private String c2Expression;
    private double epsilon = 1e-10;

    @Test
    @Title("Find squared hypotenuse")
    @Description("Find squared hypotenuse using Pythagorean theorem")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Multiplication", "Addition"})
    public void findHypotenuse() {
        Calculator calculator = getCalculator();
        String expression = "0.8 * 0.8 + 0.6 * 0.6";
        EvalResult er = calculator.eval(expression);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        c2Expression = er.getResult();
        Assert.assertEquals(er.getResult(), "1");
    }

    @Test(dependsOnMethods = "findHypotenuse")
    @Title("Find area by Heron's formula")
    @Description("Compare area of direct triangle computed by simple way and by Heron's formula")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Addition", "Substraction", "Multiplication", "Brackets"})
    public void checkCorrectnessThroughHeronsFormula() {
        Calculator calculator = getCalculator();
        String cExpression = Double.toString(Math.sqrt(Double.parseDouble(c2Expression)));
        String areaExpression = "0.5 * " + aExpression + " * " + bExpression;
        String semiperimeterExpression = "0.5 * (" + aExpression + " + " + bExpression + " + " + cExpression + ")";
        String heronArea2Expression = semiperimeterExpression + " * " + "(" + semiperimeterExpression + " - " + aExpression + ") * (" +
            semiperimeterExpression + " - " + bExpression + ") * (" + semiperimeterExpression + " - " + cExpression + ")";
        EvalResult er = calculator.eval("(" + areaExpression + " * " + areaExpression + ") - " + heronArea2Expression);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertTrue(Math.abs(Double.parseDouble(er.getResult())) < epsilon);
    }
}
