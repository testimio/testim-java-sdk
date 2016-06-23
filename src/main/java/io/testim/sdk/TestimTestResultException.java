package io.testim.sdk;

public class TestimTestResultException extends RuntimeException {

	private static final long serialVersionUID = -1904734067316455085L;
	private String failureReason;
	private String resultId;
	private String testId;

	public TestimTestResultException(String testId, String resultId, String failureReason) {
		this.testId = testId;
		this.resultId = resultId;
		this.failureReason = failureReason;
	}

	public String getFailureReason() {
		return failureReason;
	}

	public String getResultId() {
		return resultId;
	}

	public String getTestId() {
		return testId;
	}
	
	@Override
	public String toString() {
		return "TestimTestResultException [failureReason=" + failureReason + ", resultId=" + resultId + ", testId="
				+ testId + "]";
	}

}
