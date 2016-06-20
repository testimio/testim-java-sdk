package io.testim.sdk.model;

import java.util.Map;

public class SuiteFinishedModel {
	private Map<String, TestData> testResults;

	public Map<String, TestData> getTestResults() {
		return testResults;
	}

	public void setTestResults(Map<String, TestData> testResults) {
		this.testResults = testResults;
	}
}