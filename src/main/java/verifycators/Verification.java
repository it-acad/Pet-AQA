package verifycators;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.Getter;
import org.assertj.core.api.Assertions;

import java.util.Arrays;

import static io.qameta.allure.Allure.step;
import static org.hamcrest.Matchers.*;

//@VerifyStep
public class Verification<T> {
    @Getter
    protected final Response response;

    public Verification(Response response) {
        this.response = response;
    }

    public <S> S bindAs(Class<S> cls) {
        return response.as(cls);
    }

    //No direct assert to avoid log spam in report.
    //@Step("Expected response code equals to '{responseCode}'")
    public T expectedCode(int responseCode) {
        if (response.getStatusCode() != responseCode) {
            Assertions.fail("Response code not as expected! Expected " +
                    responseCode + " but found " + response.getStatusCode());
        }
        return (T) this;
    }

    @Step("Verify value of field {path} is equal to {expectedValue}")
    public T verifyBodyAsJson(String path, String expectedValue) {
        var actual = response.jsonPath().getString(path);
        Assertions.assertThat(actual)
                .isEqualTo(expectedValue);
        return (T) this;
    }

    @Step("Verify value of field {path} is equal to {expectedValue}")
    public T verifyBodyAsJsonInt(String path, Integer expectedValue) {
        var actual = response.jsonPath().getString(path);
        if (actual != null) {
            Assertions.assertThat(Integer.valueOf(actual))
                    .isEqualTo(expectedValue);
        } else {
            Assertions.assertThat(expectedValue).isNull();
        }
        return (T) this;
    }

    @Step("Verify value of field {path} is (not) equal to {expectedValue}")
    public T verifyBodyAsJsonIsNotEquals(String path, String expectedValue) {
        var actual = response.jsonPath().getString(path);
        Assertions
                .assertThat(actual)
                .isNotEqualTo(expectedValue);

        return (T) this;
    }

    @Step("Verify value of field {path} contains {expectedValue}")
    public T verifyBodyAsJsonContains(String path, String... expectedValue) {
        var actual = response.jsonPath().getString(path);
        Assertions.assertThat(actual).contains(expectedValue);
        return (T) this;
    }

    @Step("Verify value of field {path} not contains {expectedValue}")
    public T verifyBodyAsJsonNotContains(String path, String... expectedValue) {
        var actual = response.jsonPath().getString(path);
        Assertions.assertThat(actual).doesNotContain(expectedValue);
        return (T) this;
    }

    public T verifyResponseTime() {
        step("Response time:" + response.getTime());
        return (T) this;
    }

    @Step("Verify response contain not empty list")
    public T verifyContainsNotEmptyList() {
        if (response.jsonPath().get("content") != null) {
            var actualContent = response.jsonPath().getList("content");
            Assertions.assertThat(actualContent).asList().isNotEmpty();
        } else {
            var actual = response.as(Object[].class);
            Assertions.assertThat(Arrays.asList(actual)).asList().isNotEmpty();
        }
        return (T) this;
    }

    @Step("Verify array size")
    public T verifyHasSize(int size) {
        response.then().body("size()", is(size));
        return (T) this;
    }

    @Step("Verify response contain empty list")
    public T verifyContainsEmptyList() {
        if (response.jsonPath().getList("content") != null) {
            var actualContent = response.jsonPath().getList("content");
            Assertions.assertThat(actualContent).asList().isEmpty();
        } else {
            var actual = response.as(Object[].class);
            Assertions.assertThat(Arrays.asList(actual)).asList().isEmpty();
        }
        return (T) this;
    }

    @Step("Verify response contain not empty list")
    public T verifyResponseNotEmpty() {
        response.then().body("$.size()", greaterThan(0));
        return (T) this;
    }

    @Step("Verify response contain empty array")
    public T verifyResponseEmptyArray(String path) {
        response.then().body(path, empty());
        return (T) this;
    }
}