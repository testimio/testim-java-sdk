# Overview
The Testim Java SDK is a wrapper around the Testim CLI. It allows to integrate testim in a Java heavy test environment - TestNG, Junit, JSystem, etc. So Testim can be incorportaed before/after/around a flow that was coded specifically for your application enviroment. 

## Requirements

* [NodeJS >= 4.0.0](https://nodejs.org/en/download/)
* [Testim Cli](https://www.npmjs.com/package/@testim/testim-cli)
  ```
  $ npm install -g @testim/testim-cli
  ```
  
## Installation Maven

```
<repository>
  <id>bintray-info-maven</id>
  <name>bintray</name>
  <url>http://dl.bintray.com/info/maven</url>
</repository>

<dependency>
  <groupId>io.testim</groupId>
  <artifactId>testim-java-sdk</artifactId>
  <version>0.2.0</version>
</dependency>
```
  
## Usage

### Testim Options

```java
import io.testim.sdk.Testim;
import io.testim.sdk.TestimOptions;
import io.testim.sdk.model.TestimResults;

String token = "<YOUR ACCESS TOKEN>";
String projectId = "<YOUR PROJECT ID>";
String gridHost = "<SELENIUM GRID HOST, e.g. 127.0.0.1>";
int gridPort = <SELENIUM GRID PORT, e.g. 4444>;

TestimOptions options = new TestimOptions(token, projectId, gridHost, gridPort);
Testim testim = new Testim(options);

TestimResults results = testim.runLabel("sanity");
```

#### Throw Exception On Test Failed
```java

TestimOptions options = new TestimOptions(token, projectId, gridHost, gridPort);
options.setThrowExceptionOnFail(true);
Testim testim = new Testim(options);

try {
  TestimResults results = testim.runLabel("sanity");
} catch (TestimTestResultException e) {
  Assert.fail(e.toString());
}

```

### Run Test Suite By Label

```java
TestimResults results = testim.runLabel("<YOUR LABEL>");
```

### Run Single Test

```java
TestimResults results = testim.runTestId("<YOUR TEST ID>");
```

### Testim Results

| Member        | Type        | Description                           |
| ------------- |-----------| -------------------------------------|
| startTime     | Long        | Run start time (ms)                |
| endTime       | Long        | Run end time (ms)                  |
| duration      | Long        | Run duration (ms)                  |
| success       | Boolean     | Run success                        | 
| tests         | Map         | Map tests results \<testId, test data\> |
