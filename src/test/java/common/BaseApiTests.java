package common;

import helper.ApplicationHelper;
import org.junit.jupiter.api.BeforeAll;

public class BaseApiTests extends BaseTest {
    @BeforeAll
    public static void init() {
        new ApplicationHelper().initToken();
    }
}
