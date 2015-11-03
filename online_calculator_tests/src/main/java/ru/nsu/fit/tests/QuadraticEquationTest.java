package ru.nsu.fit.tests;
 
import ru.nsu.fit.services.calculator.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;
 
/*
Legent:
В школе задали найти корни квадратного уравнения.
*/


@Title("Find roots of quadratic equation")
public class QuadraticEquationTest extends BaseTest {
    //x^2 - 3 * x + 2 = 0
    private final String aExpression = "1";
    private final String bExpression = "-3";
    private final String cExpression = "2";
    private String discriminantExpression;
    private String root0Expression;
    private String root1Expression;

    @Test
    @Title("Find discriminant")
    @Description("Find discriminant of quadratic equation")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Multiplication", "Subtraction"})
    public void findDiscriminant() {
        Calculator calculator = getCalculator();
        String expr = bExpression + " * " + bExpression + " - 4 * " + aExpression + " * " + cExpression;
        EvalResult er = calculator.eval(expr);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "1");
        discriminantExpression = Double.toString(Math.sqrt(Double.parseDouble(er.getResult())));
    }

    @Test(dependsOnMethods = "findDiscriminant")
    @Title("Find roots")
    @Description("Find roots of quadratic equation")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Addition", "Subtraction", "Multiplication", "Division"})
    public void findRoots() {
        Calculator calculator = getCalculator();
        String sqrtDisriminantExpression = Double.toString(Math.sqrt(Double.parseDouble(discriminantExpression)));

        String r0Expression = "(-" + bExpression + " + " + sqrtDisriminantExpression + ") / 2 / " + aExpression;
        EvalResult er = calculator.eval(r0Expression);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "-2");

        String r1Expression = "(-" + bExpression + " - " + sqrtDisriminantExpression + ") / 2 / " + aExpression;
        er = calculator.eval(r1Expression);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "1");
    }

    @Test(dependsOnMethods = "findRoots")
    @Title("Check roots by Vieta's formulas")
    @Description("Use a, b, c values to check Vieta's formulas for roots")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Addition", "Multiplication", "Unary"})
    public void checkVietasFormulas() {
        Calculator calculator = getCalculator();

        String rootSumExpression = root0Expression + " + " + root1Expression;
        EvalResult er = calculator.eval(rootSumExpression);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "-" + bExpression);

        String rootMulExpression = root0Expression + " * " + root1Expression;
        er = calculator.eval(rootMulExpression);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), cExpression);
    }
}
