package core.amadeus;

import common.BaseApiTests;
import controllers.amadeus.AmadeusController;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[Base Amadeus test]")
public class AmadeusTests extends BaseApiTests {
    @Test
    @DisplayName("Verify flight destination")
    public void verifyAddAmadeus() {
        new AmadeusController()
                .withToken()
                .getFlightDestination("PAR", 200)
                .expectedCode(HttpStatus.SC_OK);
    }
}
