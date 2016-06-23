package io.testim.sdk.model;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import io.testim.sdk.JSONUtils;
import io.testim.sdk.TestimException;
import io.testim.sdk.TestimTestResultException;

public class TestimResults {

	private String projectId = "";
	private String executionId = "";
	private Long startTime = (long) 0;
	private Long endTime = (long) 0;
	private Boolean success = true;
	private Long duration = (long) 0;
	private String host = "";
	private int port = 0;
	private String baseUrl = "";
	private Map<String, TestData> tests = new HashMap<>();
	private Integer exitCode = 0;

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
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

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public Map<String, TestData> getTests() {
		return tests;
	}

	public void setTests(List<TestData> tests) {
		Map<String, TestData> map = new HashMap<String, TestData>();
		for (TestData testData : tests) {
			map.put(testData.getTestId(), testData);
		}
	}

	public void addTest(TestData test) {
		if (test.getSuccess() != null && !test.getSuccess()) {
			setSuccess(false);
		}
		Map<String, TestData> tests = this.getTests();
		tests.put(test.getTestId(), test);
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Integer getExitCode() {
		return exitCode;
	}

	public void setExitCode(Integer exitCode) {
		this.exitCode = exitCode;
	}

	@Override
	public String toString() {
		return "TestimResults [projectId=" + projectId + ", executionId=" + executionId + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", success=" + success + ", duration=" + duration + ", host=" + host
				+ ", port=" + port + ", baseUrl=" + baseUrl + ", tests=" + tests + ", exitCode=" + exitCode + "]";
	}

	public static void buildTestResults(final TestimResults results, String jsonString, boolean throwExceptionOnFail) throws TestimException, TestimTestResultException {
		Type type = null;
		BaseModel<?> o = JSONUtils.parseStringJson(jsonString, BaseModel.class);
		switch (o.getName()) {
		case "suiteStarted":
			type = new TypeToken<BaseModel<SuiteStartedModel>>() {
			}.getType();
			BaseModel<SuiteStartedModel> o2 = JSONUtils.parseStringJson(jsonString, type);
			results.setExecutionId(o2.getData().getExecutionId());
			results.setProjectId(o2.getData().getProjectId());
			results.setStartTime(System.currentTimeMillis());
			break;
		case "testStarted":
		case "testFinished":
			type = new TypeToken<BaseModel<TestStartedFinishedModel>>() {
			}.getType();
			BaseModel<TestStartedFinishedModel> o4 = JSONUtils.parseStringJson(jsonString, type);
			TestData data = o4.getData().getTest();
			results.addTest(data);
			if(data.getSuccess() != null && !data.getSuccess() && throwExceptionOnFail){
				throw new TestimTestResultException(data.getTestId(), data.getResultId(), data.getFailureReason());
			}
			break;
		case "suiteFinished":
			results.setEndTime(System.currentTimeMillis());
			results.setDuration(results.getEndTime() - results.getStartTime());
			break;
		}
	}
}
