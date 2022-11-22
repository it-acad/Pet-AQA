Elevate Automated Regression

Dependencies
The following dependencies required in order to be able to build,
deploy and run commands locally:
 - Open JDK 15
In case not wrapped gradle use gradle 6.8
In case UI test run, docker can be used.   

Commands to run
Test cases run by gradle test task. Env variable used to specify ENV and target test group.

Possible ENV:
- DEV

Possible SERVICE_NAME:
 - core-person-service
 - core-banking-service
 - core-card-service
 - policy-admin-service
 - e2e
 - ui
 - gateway-service
 - invoice

CI=true - by default, this param is set while run on CI. On CI environment UI tests run in docker.
So to run desired test cases use command:

gradle test -Penv=DEV -PSERVICE_NAME=core-person-service

To run single test case use:

 gradle test --tests core.gateway.SignUpTests.verifySignUpIsOk






