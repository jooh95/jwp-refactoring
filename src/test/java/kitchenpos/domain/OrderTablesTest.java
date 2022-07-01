package kitchenpos.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.stream.Collectors;
import kitchenpos.dto.OrderTableResponse;
import org.junit.jupiter.api.Test;

class OrderTablesTest {
    @Test
    void 테이블_그룹과_주문_테이블들과의_연관관계를_설정할_수_있어야_한다() {
        // given
        final TableGroup tableGroup = new TableGroup();
        final OrderTable orderTable1 = new OrderTable(1L, null, new NumberOfGuests(0), true);
        final OrderTable orderTable2 = new OrderTable(2L, null, new NumberOfGuests(0), true);

        // when
        final OrderTables orderTables = new OrderTables();
        orderTables.makeRelations(tableGroup, Arrays.asList(orderTable1, orderTable2));

        // then
        assertThat(
                orderTables.getResponses().stream().map(OrderTableResponse::getId).collect(Collectors.toList()))
                .containsExactly(orderTable1.getId(), orderTable2.getId());
    }

    @Test
    void 테이블이_하나라도_비어있지_않으면_에러가_발생해야_한다() {
        // given
        final TableGroup tableGroup = new TableGroup();
        final OrderTable empty = new OrderTable(1L, null, new NumberOfGuests(0), true);
        final OrderTable notEmpty = new OrderTable(2L, null, new NumberOfGuests(1), false);
        final OrderTables orderTables = new OrderTables();

        // when and then
        assertThatThrownBy(() -> orderTables.makeRelations(tableGroup, Arrays.asList(empty, notEmpty)))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 테이블_개수가_2개_미만이면_에러가_발생해야_한다() {
        // given
        final TableGroup tableGroup = new TableGroup();
        final OrderTable orderTable = new OrderTable(1L, null, new NumberOfGuests(0), true);
        final OrderTables orderTables = new OrderTables();

        // when and then
        assertThatThrownBy(() -> orderTables.makeRelations(tableGroup, Arrays.asList(orderTable)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
