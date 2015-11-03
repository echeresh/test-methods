package ru.nsu.fit.tests;
 
import ru.nsu.fit.services.calculator.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

/*
Legent:
Повысили зп на работе, нужно посчитать сколько за год государство скомуниздит себе.
*/

@Title("Calculate yearly salary tax")
public class SalaryTaxTest extends BaseTest {
    final String monthSalaryExpression = "10000";

    @Test
    @Title("Associative")
    @Description("Origin expression")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Addition", "Subtraction"})
    public void calculateYearlyIncome() {
        Calculator calculator = getCalculator();
        String expression = monthSalaryExpression + " * 12";
        EvalResult er = calculator.eval(expression, false);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "120000");
    }
 
    @Test(dependsOnMethods = "calculateYearlyIncome")
    @Title("Calculate net income")
    @Description("Calculate net yearly salary using VAT = 13%")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Addition", "Subtraction"})
    public void calculateNetYearlySalary() {
        Calculator calculator = getCalculator();
        String expression = " * 0.87";
        EvalResult er = calculator.appendAndEval(expression);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "104400");
    }
}
