package kitchenpos.table.domain;

import org.springframework.util.CollectionUtils;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Embeddable
public class OrderTables {

    public static final String ORDER_TABLE_NOT_EMPTY_EXCEPTION_MESSAGE = "주문 테이블이 비어있을 수 없다.";
    public static final String ORDER_TABLE_MINIMUM_SIZE_EXCEPTION_MESSAGE = "주문 테이블의 갯수가 2보다 작을 수 없다.";
    public static final int MINIMUM_SIZE = 2;

    @OneToMany(mappedBy = "tableGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderTable> orderTables = new ArrayList<>();

    public OrderTables() {

    }

    public OrderTables(List<OrderTable> orderTables) {
        validate(orderTables);
        this.orderTables = orderTables;
    }

    public List<OrderTable> getOrderTables() {
        return this.orderTables;
    }

    public void unGroup() {
        for (OrderTable orderTable : orderTables) {
            orderTable.setTableGroup(null);
        }
    }

    public List<Long> getOrderTableIds() {
        return orderTables.stream()
                .map(OrderTable::getId)
                .collect(Collectors.toList());
    }

    private static void validate(List<OrderTable> orderTables) {
        validateEmptyOrderTables(orderTables);
        validateOrderTablesSize(orderTables);
        for (final OrderTable orderTable : orderTables) {
            validateNotEmptyOrderTable(orderTable);
            validateNotNullOrderTable(orderTable);
        }
    }

    private static void validateEmptyOrderTables(List<OrderTable> orderTables) {
        if (CollectionUtils.isEmpty(orderTables)) {
            throw new IllegalArgumentException(ORDER_TABLE_NOT_EMPTY_EXCEPTION_MESSAGE);
        }
    }

    private static void validateOrderTablesSize(List<OrderTable> orderTables) {
        if (orderTables.size() < MINIMUM_SIZE) {
            throw new IllegalArgumentException(ORDER_TABLE_MINIMUM_SIZE_EXCEPTION_MESSAGE);
        }
    }

    private static void validateNotEmptyOrderTable(OrderTable orderTable) {
        if (!orderTable.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateNotNullOrderTable(OrderTable orderTable) {
        if (Objects.nonNull(orderTable.getTableGroup())) {
            throw new IllegalArgumentException();
        }
    }
}
