package io.testim.sdk.model;

public class TestData {
	private String testId;
	private String status;
	private String name;
	private String resultId;
	private Long startTime;
	private Long duration;
	private Boolean success;
	private String failureReason;

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResultId() {
		return resultId;
	}

	public void setResultId(String resultId) {
		this.resultId = resultId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getFailureReason() {
		return failureReason;
	}

	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}

	@Override
	public String toString() {
		return "TestData [testId=" + testId + ", status=" + status + ", name=" + name + ", resultId=" + resultId
				+ ", startTime=" + startTime + ", duration=" + duration + ", success=" + success + ", failureReason="
				+ failureReason + "]";
	}
}
