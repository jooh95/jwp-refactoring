package kitchenpos.order.acceptance;

public class OrderId {
    private final long orderId;

    public OrderId(long orderId) {
        this.orderId = orderId;
    }

    public long value() {
        return orderId;
    }
}
