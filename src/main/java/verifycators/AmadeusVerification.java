package verifycators;

import io.restassured.response.Response;

public class AmadeusVerification extends Verification<AmadeusVerification>{
    public AmadeusVerification(Response response) {
        super(response);
    }
}
