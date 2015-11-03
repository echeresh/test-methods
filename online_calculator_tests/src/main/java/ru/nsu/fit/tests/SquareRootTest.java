package ru.nsu.fit.tests;

import ru.nsu.fit.services.calculator.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

/*
Legent:
В школе задали найти квадатный корень из 3х вавилоновским способом.
*/

@Title("Square root test")
public class SquareRootTest extends BaseTest {
    private double s = 3;
    private double x = 1;
    private final int iterationNumber = 10;
    private final double epsilon = 1e-6;

    @Test
    @Title("Calculate square root")
    @Description("Iterate using babylonian method to find square root of inputNumber")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Addition", "Multiplication", "Division"})
    public void calcSquareRootUsingBabylonianMethod() {
        Calculator calculator = getCalculator();
        // expression for next approximation:
        // x(i + 1) = 0.5 * (x(i) + s / x(i))
        String inputNumber = Double.toString(s);
        String currentApproximation = Double.toString(x);
        for (int i = 0; i < iterationNumber; i++) {
            String expr = "0.5 * (" + currentApproximation + " + " +
                inputNumber + " / " + currentApproximation + ")";
            EvalResult er = calculator.eval(expr);
            Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
            currentApproximation = er.getResult();
            double sqroot = Double.parseDouble(inputNumber);
            Assert.assertFalse(Double.isNaN(sqroot));
            Assert.assertFalse(Double.isInfinite(sqroot));
        }
        x = Double.parseDouble(currentApproximation);
    }

    @Test(dependsOnMethods = "calcSquareRootUsingBabylonianMethod")
    @Title("Validate square root value")
    @Description("Validate value computed by babylonian method")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Multiplication"})
    public void validateSquareRoot() {
        String currentApproximation = Double.toString(x);
        Calculator calculator = getCalculator();
        String expr = currentApproximation + " * " + currentApproximation;
        EvalResult er = calculator.eval(expr);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        double sqroot2 = Double.parseDouble(er.getResult());
        Assert.assertTrue(Math.abs(sqroot2 - s) < epsilon);
        Assert.assertTrue(Math.abs(x - Math.sqrt(s)) < epsilon);
    }
}
