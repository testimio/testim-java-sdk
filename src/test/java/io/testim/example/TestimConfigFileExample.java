package io.testim.example;

import java.io.File;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import io.testim.sdk.Testim;
import io.testim.sdk.TestimException;
import io.testim.sdk.TestimOptions;
import io.testim.sdk.model.TestData;
import io.testim.sdk.model.TestimResults;

public class TestimConfigFileExample {
	private static Testim TESTIM;

	@BeforeClass
	public static void beforeClass() throws TestimException {
		File configFile = new File(TestimExample.class.getClassLoader().getResource("testim.json").getFile());
		TestimOptions options = new TestimOptions(configFile);
		TESTIM = new Testim(options, "/usr/local/bin/testim", "/usr/local/bin/node");
	}

	@Test
	@Ignore
	public void testimExampleLabels() throws TestimException {

		TestimResults results = TESTIM.runLabel("debug");
		System.out.println(results);
		Map<String, TestData> tests = results.getTests();
		System.out.println(tests);
		Assert.assertTrue(results.getSuccess());
	}

}
