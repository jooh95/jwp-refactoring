package kitchenpos.ordertable.domain;

import kitchenpos.tablegroup.domain.TableGroup;
import kitchenpos.exception.BadRequestException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static kitchenpos.utils.Message.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("주문 테이블 일급 콜렉션 태스트")
class OrderTablesTest {


    @DisplayName("단체 지정 시 주문 테이블이 없으면 예외가 발생한다.")
    @Test
    void registerTableGroupException() {
        OrderTables orderTables = OrderTables.from(Collections.emptyList());

        Assertions.assertThatThrownBy(() -> orderTables.registerTableGroup(TableGroup.createEmpty()))
                .isInstanceOf(BadRequestException.class)
                .hasMessageStartingWith(INVALID_ORDER_TABLE_SIZE);
    }

    @DisplayName("단체 지정 시 주문 테이블이 2개 미만이면 예외가 발생한다.")
    @Test
    void registerTableGroupException2() {
        OrderTables orderTables = OrderTables.from(
                Collections.singletonList(OrderTable.of(null, null,10, true))
        );

        Assertions.assertThatThrownBy(() -> orderTables.registerTableGroup(TableGroup.createEmpty()))
                .isInstanceOf(BadRequestException.class)
                .hasMessageStartingWith(INVALID_ORDER_TABLE_SIZE);
    }

    @DisplayName("단체 지정 시 주문 테이블 하나라도 empty 상태가 아니면 예외가 발생한다.")
    @Test
    void registerTableGroupException3() {
        OrderTables orderTables = OrderTables.from(
                Arrays.asList(
                        OrderTable.of(null, null,10, false),
                        OrderTable.of(null, null,10, true))
        );

        Assertions.assertThatThrownBy(() -> orderTables.registerTableGroup(TableGroup.createEmpty()))
                .isInstanceOf(BadRequestException.class)
                .hasMessageStartingWith(INVALID_EMPTY_ORDER_TABLE);
    }

    @DisplayName("단체 지정 시 주문 테이블 하나라도 이미 단체지정이 되어있으면 예외가 발생한다.")
    @Test
    void registerTableGroupException4() {
        OrderTables orderTables = OrderTables.from(
                Arrays.asList(
                        OrderTable.of(1L, 1L, 10, true),
                        OrderTable.of(1L, 10, true))
        );

        Assertions.assertThatThrownBy(() -> orderTables.registerTableGroup(TableGroup.createEmpty()))
                .isInstanceOf(BadRequestException.class)
                .hasMessageStartingWith(CONTAIN_ALREADY_GROUPED_ORDER_TABLE);
    }

    @DisplayName("주문 테이블들을 단체 지정한다.")
    @Test
    void registerTableGroup() {
        OrderTables orderTables = OrderTables.from(
                Arrays.asList(
                        OrderTable.of(null, null,10, true),
                        OrderTable.of(null, null,5, true)
                )
        );

        orderTables.registerTableGroup(TableGroup.createEmpty());

        assertAll(
                () -> assertThat(orderTables.getOrderTables().get(0).isEmpty()).isFalse(),
                () -> assertThat(orderTables.getOrderTables().get(1).isEmpty()).isFalse()
        );
    }
}
