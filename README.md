# Introduction

## Requirements

* NodeJS >= 4.0.0
* [Testim Cli](https://www.npmjs.com/package/@testim/testim-cli)
  ```
  $ npm install -g @testim/testim-cli
  ```
  
## Installation

```
<repository>
  <id>bintray-info-maven</id>
  <name>bintray</name>
  <url>http://dl.bintray.com/info/maven</url>
</repository>

<dependency>
  <groupId>io.testim</groupId>
  <artifactId>testim-java-sdk</artifactId>
  <version>0.0.1</version>
</dependency>
```
  
## Usage

### Testim Options

```java
import io.testim.sdk.Testim;
import io.testim.sdk.TestimOptions;

String token = "<YOUR ACCESS TOKEN>";
String projectId = "<YOUR PROJECT ID>";
String gridHost = "<SELENIUM GRID HOST, e.g. 127.0.0.1>";
int gridPort = <SELENIUM GRID PORT, e.g. 4444>;

TestimOptions options = new TestimOptions(token, projectId, gridHost, gridPort);
Testim testim = new Testim(options);
```

### Run Label

```java
import io.testim.sdk.model.TestimResults;

TestimResults results = testim.runLabel("<YOUR LABEL>");
```

### Run Test

```java
import io.testim.sdk.model.TestimResults;

TestimResults results = testim.runTestId("<YOUR TEST ID>");
```

### Testim Results

```java
results.getSuccess(); //return true\false
Map<String, TestData> tests = results.getTests(); //return map of testId, test data
```
