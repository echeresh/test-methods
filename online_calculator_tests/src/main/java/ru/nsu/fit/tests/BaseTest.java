package ru.nsu.fit.tests;

import ru.nsu.fit.services.calculator.*;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

public abstract class BaseTest {
	Calculator calculator;

	@BeforeClass
	public void initCalculator() {
		calculator = CalculatorService.newCalculator();
	}

	public Calculator getCalculator() {
		return calculator;
	}

	@AfterClass
	public void destroyCalculator() {
		calculator.close();
	}
}