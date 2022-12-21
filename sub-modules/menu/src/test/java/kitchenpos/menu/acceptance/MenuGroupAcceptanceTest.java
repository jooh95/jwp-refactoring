package kitchenpos.menu.acceptance;


import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kitchenpos.MenuAcceptanceConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static kitchenpos.menu.fixture.MenuGroupTestFixture.*;

@DisplayName("메뉴그룹 관련 인수 테스트")
public class MenuGroupAcceptanceTest extends MenuAcceptanceConfig {

    /**
     * When : 메뉴 그룹 생성을 요청한다.
     * Then : 메뉴 그룹이 생성된다.
     */
    @DisplayName("메뉴 그룹 생성 인수 테스트")
    @Test
    void createMenuGroup() {
        // when
        ExtractableResponse<Response> response = 메뉴그룹_생성_요청("세마리 치킨");

        // then
        메뉴그룹_생성됨(response);
    }

    /**
     * Given : 메뉴그룹이 생성되어 있다.
     * When : 메뉴그룹 조회를 요청하면,
     * Then : 메뉴그룹 목록을 응답한다.
     */
    @DisplayName("메뉴그룹 조회 인수 테스트")
    @Test
    void findMenuGroups() {
        // given
        메뉴그룹_생성_요청("한마리 치킨");
        메뉴그룹_생성_요청("두마리 치킨");
        메뉴그룹_생성_요청("세마리 치킨");

        // when
        ExtractableResponse<Response> response = 메뉴그룹_조회_요청();

        // then
        메뉴그룹_조회됨(response);
    }
}
