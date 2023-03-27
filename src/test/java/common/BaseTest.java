package common;

import io.qameta.allure.Link;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Link(name = "allure", type = "mylink")
@Execution(ExecutionMode.CONCURRENT)
public class BaseTest {

}
