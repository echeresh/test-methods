package ru.nsu.fit.tests;

import ru.nsu.fit.services.calculator.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

@Title("LU flops test")
public class LuFlopsTest extends BaseTest {
    private int matrixRows = 100000;
    private int matrixCols = 100;

    @Test
    @Title("Calculate flops for LU")
    @Description("Calculate floating point operations for tall-and-skinny LU")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Addition", "Subtraction", "Multiplication"})
    public void calcLuFlops() {
        Calculator calculator = getCalculator();
        // expression for LU flops: m*n^2 - 1 / 3 * n^3 - 1 / 2 * n^2
        String expression = matrixRows + " * " + matrixCols + " * " + matrixCols +
            " - 1 / 3 * " + matrixCols + " * " + matrixCols + " * " + matrixCols +
            " - 1 / 2 * " + matrixCols + " * " + matrixCols;
        EvalResult er = calculator.eval(expression, false);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "1");
    }

    @Test(dependsOnMethods = "calcLuFlops")
    @Title("Calculate GFlops for LU")
    @Description("Divide LU flops by 10^9")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Division"})
    public void calcLuGFlops() {
        EvalResult er = calculator.appendAndEval(" / 1e9", false);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "1");
    }

    @Test(dependsOnMethods = "calcLuGFlops")
    @Title("LU efficicency")
    @Description("Calculate efficicency of LU on 2-socket Xeon E5-2699")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Division"})
    public void getLuEfficiencyOnXeonE52699() {
        String timeExpression = "0.007";
        String coresNumberExpression = "36";
        String avx2DoubleFlopsPerCycleExpression = "16";
        String freqExpression = "2.3";
        EvalResult er = calculator.appendAndEval(" / " + timeExpression +
            " / " + coresNumberExpression +
            " * " + freqExpression +
            " * " + avx2DoubleFlopsPerCycleExpression);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "1");
    }
}
