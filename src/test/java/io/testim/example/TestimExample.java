package io.testim.example;

import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import io.testim.sdk.Testim;
import io.testim.sdk.TestimException;
import io.testim.sdk.TestimOptions;
import io.testim.sdk.model.TestData;
import io.testim.sdk.model.TestimResults;

public class TestimExample {

	private static Testim TESTIM;

	@BeforeClass
	public static void beforeClass() throws TestimException {
		String token = "<YOUR ACCESS TOKEN>";
		String projectId = "<YOUR PROJECT ID>";
		String gridHost = "<SELENIUM GRID HOST, e.g. 127.0.0.1>";
		int gridPort = 4444;

		TestimOptions options = new TestimOptions(token, projectId, gridHost, gridPort);
		TESTIM = new Testim(options, "/usr/local/bin/testim", "/usr/local/bin/node");
	}

	@Test
	public void testimExampleLabels() throws TestimException {

		TestimResults results = TESTIM.runLabel("debug");
		System.out.println(results);
		Map<String, TestData> tests = results.getTests();
		System.out.println(tests);
		Assert.assertTrue(results.getSuccess());
	}

}
