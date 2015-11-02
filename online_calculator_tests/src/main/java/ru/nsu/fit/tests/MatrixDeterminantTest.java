package ru.nsu.fit.tests;
 
import ru.nsu.fit.services.calculator.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

@Title("Matrix's determinant")
public class MatrixDeterminantTest extends BaseTest {
    @Test
    @Title("Addition")
    @Description("Determinant of matrix")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Multiplication", "Addition"})
    public void solveTask() {
        Calculator calculator = getCalculator();
        // solve task
        String expression = "4*29*(-5) + 3*5*41 + 0*6*12";
        EvalResult er = calculator.eval(expression, false);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "35");
    }
 
    @Test(dependsOnMethods = "solveTask")
    @Title("Subtraction")
    @Description("Proof of distributive property")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Addition", "Multiplication"})
    public void addOneMoreNumber() {
        Calculator calculator = getCalculator();
        String expression = "-(0 * 29*41 + 4*5*12 + 3*6*(-5))";
        EvalResult er = calculator.eval(expression);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "-150");
    }
}
