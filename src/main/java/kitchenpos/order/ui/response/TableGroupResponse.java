package kitchenpos.order.ui.response;

import java.time.LocalDateTime;
import java.util.List;

import kitchenpos.order.domain.TableGroup;

public class TableGroupResponse {

	private final long id;
	private final LocalDateTime createdDate;
	private final List<OrderTableResponse> orderTables;

	private TableGroupResponse(long id, LocalDateTime createdDate, List<OrderTableResponse> orderTables) {
		this.id = id;
		this.createdDate = createdDate;
		this.orderTables = orderTables;
	}

	public static TableGroupResponse of(long id, LocalDateTime createdDate, List<OrderTableResponse> orderTables) {
		return new TableGroupResponse(id, createdDate, orderTables);
	}

	public static TableGroupResponse from(TableGroup savedTableGroup) {
		return new TableGroupResponse(savedTableGroup.getId(), savedTableGroup.getCreatedDate(),
				OrderTableResponse.listOf(savedTableGroup.getOrderTables()));
	}

	public long getId() {
		return id;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public List<OrderTableResponse> getOrderTables() {
		return orderTables;
	}
}
