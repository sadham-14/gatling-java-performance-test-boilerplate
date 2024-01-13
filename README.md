# Gatling Java Maven Boilerplate for Performance Testing
This project serves as a boilerplate for performance testing using Gatling and Java. The performance test scripts are created using Gatling, with scripts written in Java and build using Maven. For more information and documentation, can go [here](https://gatling.io/docs/gatling/).

## Project Setup
Install the following to ensure smooth execution of the performance test script:

- [Java JDK 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/?section=mac)

After importing the perf test script to IntelliJ IDEA, run Maven build first. 

In settings, ensure that the java compiler used is JDK 11 and the Maven JVM selected is `Project SDK`. Once selected, the Maven JVM and java compiler both should be of same version.

## Project Structure
Test scripts are located in `/scenario` folder. The scripts are grouped by the BP. 

Example of structure:
```
└── scenario
│   ├── breakpoint
│   │   └── BP1BreakpointTest.java
│   │   └── BP2BreakpointTest.java
│   │   └── BP3BreakpointTest.java
│   ├── BP1Test.java
│   ├── BP2Test.java
│   ├── BP3Test.java
```

For utils files that are used in the tests, they are located in the `/core` folder.

Structure is of following:
```
├── core
│   ├── constant
│   │   └── ApiConstant.java
│   │   └── BusinessProcess.java
│   │   └── PerformanceTestType.java
│   │   └── TestConfiguration.java
│   ├── controller
│   │   ├── ApproveRequestController.java
│   │   ├── AuthController.java
│   │   ├── GetAllServiceRequestsController.java
│   │   └── SubmitRequestController.java
│   └── util
│       ├── ExecutionUtil.java
│       ├── HttpProtocol.java
│       ├── PropertyFileReader.java
│       └── RequestBodyGenerator.java
```

## Test Script Execution

### Method 1: Test Script Execution via IntelliJ IDEA

1. Go to `config.properties` in the `src/test/resources` folder and update the `test_env` to `sit` or `uat`.
2. Go to `PerformanceTestExecutor.java` in the `src/test/java/crisp` folder.
3. Update line numbers `24` and `25` according to the business process and the performance test type you need to execute.
4. Go to `Engine.java` in the `src/test/java` folder.
5. Click on the run icon beside the Engine class header.
6. Wait till test execution is completed.
7. Once test is completed, the report will be stored at `<project_path_directory>/target/gatling`.

### Method 2: Test Script Execution via Terminal

1. Execute the below command in the terminal

    `mvn gatling:test -Dgatling.simulationClass=<test-class-name-with-package> -Dbp=<business-process> -DtestType=<LOAD-STRESS-ENDURANCE-BREAKPOINT> -Dgatling.core.runDescription=<test-description>`
    
    Example:
    
    `mvn gatling:test -Dgatling.simulationClass=crisp.PerformanceTestExecutor -Dbp=BP1 -DtestType=LOAD -Dgatling.core.runDescription="BP1 Load Test"`

2. Wait till test execution is completed
3. Once test is completed, the report will be stored at `<project_path_directory>/build/report/gatling`

## Test Data File Usage and Directory
If csv data files are required to use for the requests, pls place it in `resources/data` directory. Ensure that the data files are named clearly to prevent confusion. 

** Do note that if you have added data files that is only meant for testing locally, please do not commit them into the repository.

## Generate Gatling HTML Reports from simulation.log

### 1. Download Gatling Highcharts Standalone Bundle 
Visit the [Gatling Download page](https://gatling.io/thank-you/) and download the Gatling Highcharts Standalone bundle. Ensure that the version matches your Gatling version.

### 2. Extract the Bundle
Extract the downloaded archive to a location on your machine.

### 3. Copy the simulation.log File
Copy your `simulation.log` file to the `user-files/simulations/crisp` directory inside the extracted Gatling bundle.

### 4. Run the Report Generation
Open a terminal, navigate to the Gatling bundle directory, and run the following command:

```bash
sh bin/gatling.sh -ro /Users/osanda/Downloads/gatling-charts-highcharts-bundle-3.10.3/user-files/simulations/crisp
```

### 5. View Report
Open the `index.html` file inside the `user-files/simulations/crisp` directory.

## Important Things to Note
- Before every execution of test, remember to check and ensure that the url is pointing to the right environment
- For test report generated at the end of the test, they report folder name will be look like: `performancetestexecutor-<datetime>`