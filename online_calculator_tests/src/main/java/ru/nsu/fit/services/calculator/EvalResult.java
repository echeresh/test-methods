package ru.nsu.fit.services.calculator;

public class EvalResult {
	private String result;
	private EvalResultStatus status;

	public EvalResult(String result, EvalResultStatus status) {
		this.result = result;
		this.status = status;
	}

	public String getResult() {
		return result;
	}

	public EvalResultStatus getStatus() {
		return status;
	}
}