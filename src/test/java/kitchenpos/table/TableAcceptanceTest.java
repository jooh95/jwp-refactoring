package kitchenpos.table;

import static kitchenpos.table.TableFixture.주문_테이블_목록_조회;
import static kitchenpos.table.TableFixture.주문_테이블_빈_테이블_상태_변경;
import static kitchenpos.table.TableFixture.주문_테이블_추가;
import static kitchenpos.table.TableFixture.주문_테이블의_방문한_손님_수_변경;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import kitchenpos.AcceptanceTest;
import kitchenpos.domain.OrderTable;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class TableAcceptanceTest extends AcceptanceTest {

    /*
    Feature: 테이블 관리를 합니다.
        Scenario: 주문 테이블을 관리 한다.
            When 주문 테이블을 등록
            Then 주문 테이블 등록됨
            When 주문 테이블 목록을 조회
            Then 주문 테이블 목록 조회됨
            When 주문 테이블 방문한 손님 수 변경
            Then 주문 테이블 방문한 손님 수 변경 됨
            When 주문 테이블 빈 테이블로 변경
            Then 주문 테이블 빈 테이블로 변경 됨
     */
    @Test
    void 주문_테이블_관리() {
        //when
        ExtractableResponse<Response> createResponse = 주문_테이블_추가(null, 0, false);
        //then
        assertThat(createResponse.statusCode()).isEqualTo(HttpStatus.CREATED.value());

        //when
        List<OrderTable> orderTables = 주문_테이블_목록_조회().jsonPath().getList(".", OrderTable.class);
        //then
        assertThat(orderTables)
            .hasSize(1)
            .extracting(OrderTable::getNumberOfGuests, OrderTable::isEmpty)
            .containsExactly(tuple(
                0,
                false
            ));

        //when
        OrderTable numberOfGuestChanged = 주문_테이블의_방문한_손님_수_변경(orderTables.get(0).getId(), 10)
            .as(OrderTable.class);
        //then
        assertThat(numberOfGuestChanged.getNumberOfGuests()).isEqualTo(10);

        //when
        OrderTable empty = 주문_테이블_빈_테이블_상태_변경(orderTables.get(0).getId(), true)
            .as(OrderTable.class);
        //then
        assertThat(empty.isEmpty()).isTrue();
    }
}
