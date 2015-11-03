package ru.nsu.fit.tests;
 
import ru.nsu.fit.services.calculator.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

/*
Legent:
Нужно посчитать определеитель матрицы 3х3.
*/

@Title("Matrix's determinant")
public class MatrixDeterminantTest extends BaseTest {
    @Test
    @Title("Determinant of matrix 3 x 3")
    @Description("Calculate sum part of determinant expression")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Multiplication", "Addition", "Unary"})
    public void calculateFirstPartOfDeterminant() {
        Calculator calculator = getCalculator();
        String expression = "4 * 29 * (-5) + 3 * 5 * 41 + 0 * 6 * 12";
        EvalResult er = calculator.eval(expression, false);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "35");
    }
 
    @Test(dependsOnMethods = "calculateFirstPartOfDeterminant")
    @Title("Subtract rest part of determinant expression")
    @Description("Calculate sum part of determinant expression")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Addition", "Subtraction", "Multiplication", "Unary"})
    public void calculateSecondPartOfDeterminant() {
        Calculator calculator = getCalculator();
        String expression = " - (0 * 29 * 41 + 4 * 5 * 12 + 3 * 6 * (-5))";
        EvalResult er = calculator.eval(expression);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "-150");
    }
}
