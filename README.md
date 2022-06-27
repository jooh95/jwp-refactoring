# 키친포스

## 요구 사항
### 상품 (Product)
- 상품을 등록할 수 있다.
- 상품의 가격이 올바르지 않으면 등록할 수 없다.
  - 상품의 가격은 0원 이상이어야 한다.
- 상품의 목록을 조회할 수 있다.
### 메뉴 그룹 (MenuGroup)
- 메뉴 그룹을 생성할 수 있다.
- 메뉴 그룹의 목록을 조회할 수 있다.
### 메뉴 (Menu)
- 메뉴를 등록할 수 있다.
- 메뉴 가격이 올바르지 않으면 등록할 수 없다.
  - 메뉴의 가격은 0 이상이어야 한다.
  - 메뉴의 가격은 구성 상품의 가격과 수량을 곱한 금액의 합보다 같거나 작아야 한다.
- 메뉴의 메뉴 그룹이 올바르지 않으면 등록할 수 없다.
  - 메뉴는 메뉴 그룹에 속해 있어야 한다.
- 메뉴를 구성하는 상품이 올바르지 않으면 등록할 수 없다.
  - 메뉴를 구성하는 상품은 생성되어 있는 상품이어야 한다.
- 메뉴의 목록을 조회할 수 있다.
### 주문 테이블 (OrderTable)
- 주문 테이블을 생성할 수 있다.
- 주문 테이블의 목록을 조회할 수 있다.
- 주문 테이블을 빈 테이블로 설정할 수 있다.
- 주문 테이블이나 주문의 상태가 올바르지 않으면 빈 테이블로 설정할 수 없다.
  - 단체 지정되지 않은 주문 테이블이어야 한다
  - 주문 상태가 "조리"나 "식사"가 아니어야 한다.
- 손님 수를 변경할 수 있다.
- 변경하려는 손님 수가 올바르지 않으면 변경할 수 없다.
  - 손님 수는 0 이상이어야 한다.
- 주문 테이블의 상태가 올바르지 않으면 변경할 수 없다.
  - 빈 테이블(주문을 등록할 수 없는 상태)이 아니어야 한다.
### 단체 지정 (Table Group)
- 주문 테이블을 단체 지정할 수 있다.
- 주문 테이블이 올바르지 않으면 단체 지정할 수 없다.
  - 주문 테이블의 수는 2개 이상이어야 한다.
  - 주문 테이블들은 생성되어 있어야 한다.
  - 주문 테이블들은 빈 테이블이어야 한다.
  - 기존에 단체 지정되어 있지 않아야 한다.
- 단체 지정을 해제할 수 있다.
- 주문 테이블들의 주문 상태가 올바르지 않으면 해제할 수 없다.
  - 주문 테이블들의 주문 상태가 "조리"나 "식사"가 아니어야 한다.
### 주문
- 주문을 생성할 수 있다.
- 주문 항목들이 올바르지 않으면 생성할 수 없다.
  - 주문 항목이 하나 이상 존재해야 한다.
  - 주문 항목의 메뉴들은 생성되어 있어야 한다.
- 주문을 등록할 주문 테이블이 올바르지 않으면 생성할 수 없다.
  - 주문 테이블이 생성되어 있어야 한다.
  - 주문 테이블이 빈 테이블(주문을 등록할 수 없는 상태)이 아니어야 한다.
- 주문 목록을 조회할 수 있다.
- 주문 상태를 변경할 수 있다.
- 주문 상태가 올바르지 않으면 변경할 수 없다.
  - 주문은 생성되어 있어야 한다.
  - 계산이 완료되기 전이어야 한다.

## 2단계 요구 사항
단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드를 분리해 단위 테스트 가능한 코드에 대해 단위 테스트를 구현한다.
- Lombok 없이 미션을 진행해 본다.
- indent(인덴트, 들여쓰기) depth는 1까지만 허용한다.
- 3항 연산자를 쓰지 않는다
- else 예약어를 쓰지 않는다.
- 모든 기능을 TDD로 구현해 단위 테스트가 존재해야 한다. 단, UI(System.out, System.in) 로직은 제외
- 함수(또는 메서드)의 길이가 10라인을 넘어가지 않도록 구현한다.
- 모든 원시 값과 문자열을 포장한다.
- 일급 컬렉션을 쓴다.
- 모든 엔티티를 작게 유지한다.
- 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.

## 용어 사전

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 상품 | product | 메뉴를 관리하는 기준이 되는 데이터 |
| 메뉴 그룹 | menu group | 메뉴 묶음, 분류 |
| 메뉴 | menu | 메뉴 그룹에 속하는 실제 주문 가능 단위 |
| 메뉴 상품 | menu product | 메뉴에 속하는 수량이 있는 상품 |
| 금액 | amount | 가격 * 수량 |
| 주문 테이블 | order table | 매장에서 주문이 발생하는 영역 |
| 빈 테이블 | empty table | 주문을 등록할 수 없는 주문 테이블 |
| 주문 | order | 매장에서 발생하는 주문 |
| 주문 상태 | order status | 주문은 조리 ➜ 식사 ➜ 계산 완료 순서로 진행된다. |
| 방문한 손님 수 | number of guests | 필수 사항은 아니며 주문은 0명으로 등록할 수 있다. |
| 단체 지정 | table group | 통합 계산을 위해 개별 주문 테이블을 그룹화하는 기능 |
| 주문 항목 | order line item | 주문에 속하는 수량이 있는 메뉴 |
| 매장 식사 | eat in | 포장하지 않고 매장에서 식사하는 것 |
