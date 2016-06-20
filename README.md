# Introduction

## Requirements

* NodeJS >= 4.0.0
* Testim Cli
  ```
  $ npm install -g @testim/testim-cli
  ```
  
## Installation

```
<dependency>
  <groupId>io.testim</groupId>
  <artifactId>testim-java-sdk</artifactId>
  <version>0.0.1</version>
</dependency>
```
  
## Usage

### Testim Options

```
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

```
import io.testim.sdk.model.TestimResults;

TestimResults results = testim.runLabel("<YOUR LABEL>");
```

### Run Test

```
import io.testim.sdk.model.TestimResults;

TestimResults results = testim.runTestId("<YOUR TEST ID>");
```

### Testim Results

```
results.getSuccess(); //return true\false
Map<String, TestData> tests = results.getTests(); //return map of testId, test data
```
