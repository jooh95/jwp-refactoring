package kitchenpos.order;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import kitchenpos.domain.Order;
import kitchenpos.domain.OrderLineItem;
import kitchenpos.domain.OrderStatus;
import org.springframework.http.MediaType;

public class OrderFixture {

    public static ExtractableResponse<Response> 주문(Long orderTableId,
        List<OrderLineItem> orderLineItems) {
        Order order = new Order();
        order.setOrderTableId(orderTableId);
        order.setOrderLineItems(orderLineItems);

        return RestAssured
            .given().log().all()
            .body(order)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().post("/api/orders")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> 주문_목록_조회() {

        return RestAssured
            .given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().get("/api/orders")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> 주문_상태_수정(Long orderId,
        OrderStatus orderStatus) {
        Order order = new Order();
        order.setOrderStatus(orderStatus.name());

        return RestAssured
            .given().log().all()
            .body(order)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().put("/api/orders/" + orderId + "/order-status")
            .then().log().all()
            .extract();
    }

}
