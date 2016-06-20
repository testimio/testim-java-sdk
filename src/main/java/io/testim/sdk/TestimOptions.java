package io.testim.sdk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class TestimOptions {

	private String token = "";
	private String projectId = "";
	private String gridHost = "";
	private int gridPort = 0;
	private String baseUrl = "";
	private String sauceLabsUser = "";
	private String sauceLabsKey = "";

	public TestimOptions() {
		super();
	}

	public TestimOptions(File configFile) throws TestimException {
		super();
		try {
			String jsonString = FileUtils.readFileToString(configFile, "UTF-8");
			if (JSONUtils.isStringJSONValid(jsonString)) {
				TestimOptions opt = JSONUtils.parseStringJson(jsonString, TestimOptions.class);
				this.token = opt.getToken();
				this.projectId = opt.getProjectId();
				this.gridHost = opt.getGridHost();
				this.gridPort = opt.getGridPort();
				this.baseUrl = opt.getBaseUrl();
				this.sauceLabsUser = opt.getSauceLabsUser();
				this.sauceLabsKey = opt.getSauceLabsKey();
			} else {
				throw new TestimException("Configuration file is not valid");
			}
		} catch (FileNotFoundException e) {
			throw new TestimException(e.getMessage());
		} catch (IOException e) {
			throw new TestimException(e.getMessage());
		}
	}

	public TestimOptions(String token, String projectId, String gridHost, int gridPort) {
		super();
		this.token = token;
		this.projectId = projectId;
		this.gridHost = gridHost;
		this.gridPort = gridPort;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getGridHost() {
		return gridHost;
	}

	public void setGridHost(String gridHost) {
		this.gridHost = gridHost;
	}

	public int getGridPort() {
		return gridPort;
	}

	public void setGridPort(int gridPort) {
		this.gridPort = gridPort;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getSauceLabsUser() {
		return sauceLabsUser;
	}

	public void setSauceLabsUser(String sauceLabsUser) {
		this.sauceLabsUser = sauceLabsUser;
	}

	public String getSauceLabsKey() {
		return sauceLabsKey;
	}

	public void setSauceLabsKey(String sauceLabsKey) {
		this.sauceLabsKey = sauceLabsKey;
	}

}
