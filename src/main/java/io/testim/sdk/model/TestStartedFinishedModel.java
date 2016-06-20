package io.testim.sdk.model;

public class TestStartedFinishedModel {

	private TestData test;
	private Integer workerId;

	public TestData getTest() {
		return test;
	}

	public void setTest(TestData test) {
		this.test = test;
	}

	public Integer getWorkerId() {
		return workerId;
	}

	public void setWorkerId(Integer workerId) {
		this.workerId = workerId;
	}
	
}
