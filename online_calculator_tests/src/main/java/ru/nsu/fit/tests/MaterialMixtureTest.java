package ru.nsu.fit.tests;

import ru.nsu.fit.services.calculator.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

@Title("Test for computing weight of mixed materials")
public class MaterialMixtureTest extends BaseTest {
    @Test
    @Title("Mix materials")
    @Description("Mix several materials by different proportions")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Addition", "Unary"})
    public void mixMaterials() {
        Calculator calculator = getCalculator();
        // solve task
        String expression = "1.011 * 2 + 3.895 * 4 + 5.5001 * 6 + 0.0033 * 9";
        EvalResult er = calculator.eval(expression, false);
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "50.632299999999994");
    }

    @Test(dependsOnMethods = "mixMaterials")
    @Title("Calculate partial weight of mixture")
    @Description("Calculate several procents from mixture")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"Addition", "Unary"})
    public void calcPartialWeight() {
        EvalResult er = calculator.appendAndEval(" / 100 * 2");
        Assert.assertTrue(er.getStatus() != EvalResultStatus.MismatchedInput);
        Assert.assertEquals(er.getResult(), "1.012646");
    }
}
