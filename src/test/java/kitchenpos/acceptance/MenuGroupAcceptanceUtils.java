package kitchenpos.acceptance;

import static kitchenpos.acceptance.RestAssuredUtils.*;
import static org.assertj.core.api.Assertions.*;

import org.springframework.http.HttpStatus;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kitchenpos.domain.MenuGroup;

public class MenuGroupAcceptanceUtils {

	private static final String MENU_GROUP_API = "/api/menu-groups";

	public static ExtractableResponse<Response> 메뉴_그룹_등록_요청(String name) {
		return post(MENU_GROUP_API, createRequest(name)).extract();
	}

	public static ExtractableResponse<Response> 메뉴_그룹_목록_조회_요청() {
		return get(MENU_GROUP_API).extract();
	}


	public static void 메뉴_그룹_등록_됨(ExtractableResponse<Response> response, String expectedName) {
		assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
		assertThat(response.as(MenuGroup.class)).isNotNull();
	}

	public static void 메뉴_그룹_목록_조회_됨(ExtractableResponse<Response> response) {
		assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.jsonPath().getList(".", MenuGroup.class)).isNotNull();
	}


	private static MenuGroup createRequest(String name) {
		return new MenuGroup(1L, name);
	}

}
