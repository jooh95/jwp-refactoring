package kitchenpos.menu.acceptance;


import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kitchenpos.AcceptanceTest;
import kitchenpos.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("상품 관련 인수 테스트")
public class ProductAcceptanceTest extends AcceptanceTest {

    /**
     * When : 상품 생성을 요청하면
     * Then : 상품이 생성된다.
     */
    @DisplayName("상품을 생성한다")
    @Test
    void createProduct() {
        // when
        ExtractableResponse<Response> response = 상품_생성_요청("순살치킨", new BigDecimal(9000));

        // then
        상품_생성됨(response);
    }

    /**
     * When : 가격이 0 이하인 상품을 생성하면
     * Then : 상품 생성이 실패한다
     */
    @DisplayName("상품을 생성 실패")
    @Test
    void createProductFailed() {
        // when
        ExtractableResponse<Response> response = 상품_생성_요청("순살치킨", new BigDecimal(-1));

        // then
        상품_생성_실패됨(response);
    }

    /**
     * Given : 상품이 생성되어 있다
     * When : 상품 목록 조회를 요청하면
     * Then : 상품 목록을 응답한다.
     */
    @DisplayName("상품 목록을 조회한다")
    @Test
    void findProducts() {
        // given
        상품_생성_요청("신제품치킨", new BigDecimal(20000));

        // when
        ExtractableResponse<Response> response = 상품_목록_조회();

        // then
        상품_목록_조회됨(response);
    }

    public static ExtractableResponse<Response> 상품_생성_요청(String name, BigDecimal price) {
        Product productRequest = new Product(name, price);
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(productRequest)
                .when().post("/api/products")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 상품_목록_조회() {
        return RestAssured.given().log().all()
                .when().get("/api/products")
                .then().log().all()
                .extract();
    }

    public static void 상품_목록_조회됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    public static void 상품_생성됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.header("Location")).isNotBlank();
    }

    public static void 상품_생성_실패됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
