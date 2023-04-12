package common;

import helper.ApplicationHelper;
import io.qameta.allure.Link;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.CONCURRENT)
@Link(name = "allure", type = "mylink")
public class BaseApiTests {
    @BeforeAll
    public static void init() {
        new ApplicationHelper().initToken();
    }
}
