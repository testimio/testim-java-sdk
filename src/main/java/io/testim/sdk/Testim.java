package io.testim.sdk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import io.testim.sdk.model.TestimResults;

public class Testim {

	private TestimOptions options;
	private String cliLocation = null;
	private String nodeLocation = null;
	private TestimResults testimResults = new TestimResults();
	
	@SuppressWarnings("unused")
	private Testim() {}

	public Testim(TestimOptions options) {
		super();
		this.options = options;
		testimResults.setBaseUrl(options.getBaseUrl());
		testimResults.setPort(options.getGridPort());
		testimResults.setHost(options.getGridHost());
	}

	public Testim(TestimOptions options, String cliLocation) {
		this(options);
		this.cliLocation = cliLocation;
	}

	public Testim(TestimOptions options, String cliLocation, String nodeLocation) {
		this(options, cliLocation);
		this.nodeLocation = nodeLocation;
	}

	public TestimResults runLabel(String... labels) throws TestimException, TestimTestResultException {
		ArrayList<String> cmdOptions = buildOptions(options);
		for (String label : labels) {
			cmdOptions.add("--label");
			cmdOptions.add(label);
		}
		return run(cmdOptions);
	}

	public TestimResults runTestId(String... testIds) throws TestimException, TestimTestResultException {
		ArrayList<String> cmdOptions = buildOptions(options);
		for (String testId : testIds) {
			cmdOptions.add("--testId");
			cmdOptions.add(testId);
		}
		return run(cmdOptions);
	}

	private ArrayList<String> buildOptions(TestimOptions options) {
		ArrayList<String> cmdOptions = new ArrayList<String>();
		if (nodeLocation != null) {
			cmdOptions.add(nodeLocation);
		}

		if (cliLocation != null) {
			cmdOptions.add(cliLocation);
		} else {
			cmdOptions.add("testim");
		}

		cmdOptions.add("--reporters");
		cmdOptions.add("json");
		cmdOptions.add("--project");
		cmdOptions.add(options.getProjectId());
		cmdOptions.add("--token");
		cmdOptions.add(options.getToken());
		cmdOptions.add("--host");
		cmdOptions.add(options.getGridHost());
		cmdOptions.add("--base-url");
		cmdOptions.add(options.getBaseUrl());
		cmdOptions.add("--port");
		cmdOptions.add(Integer.toString(options.getGridPort()));
		return cmdOptions;
	}

	private TestimResults run(ArrayList<String> cmdOptions) throws TestimException, TestimTestResultException {
		ProcessBuilder builder = new ProcessBuilder();
		builder.command(cmdOptions);
		BufferedReader stdInput = null;
		try {
			builder.redirectErrorStream(true);
			Process proc = builder.start();
			stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String s = null;
			while ((s = stdInput.readLine()) != null) {
				if (JSONUtils.isStringJSONValid(s)) {
					TestimResults.buildTestResults(testimResults, s, this.options.isThrowExceptionOnFail());
				}
			}

			int exitValue = proc.waitFor();
			testimResults.setExitCode(exitValue);
			return testimResults;
		} catch(TestimTestResultException e){
			throw e;
		} catch (Exception e) {
			throw new TestimException(e.getMessage());
		} finally {
			if (stdInput != null) {
				try {
					stdInput.close();
				} catch (IOException e) {
					throw new TestimException(e.getMessage());
				}
			}
		}
	}

}
