# 키친포스

## 요구 사항

- [x] 서비스 로직 -> 도메인으로 이동
    - [x] 메뉴그룹 서비스 도메인 로직 이동
    - [x] 메뉴 서비스 도메인 로직 이동
    - [x] 주문 CRUD 서비스 도메인 로직 이동
    - [x] 주문 상태 서비스 도메인 로직 이동
    - [x] 상품 서비스 도메인 로직 이동
    - [x] 테이블그룹 서비스 도메인 로직 이동
    - [x] 테이블 서비스 도메인 로직 이동
-[x] JPA 사용
    - [x] OrderDao -> OrderRepository 변경
    - [x] OrderTableDao -> OrderTableRepository 변경
    - [x] TableGroupDao -> TableGroupRepository 변경
    - [x] MenuGroupDao -> MenuGroupRepository 변경
- [x] Fixture 사용
- [x] DTO 사용
- [x] 일급 컬렉션을 쓴다.
- [x] 배열 대신 컬렉션을 사용한다.
- [x] 팩토리 메서드 사용
- [x] 모든 원시 값과 문자열을 포장한다
- [x] 무분별한 setter 삭제
- [x] 3항 연산자를 쓰지 않는다.
- [x] else 예약어를 쓰지 않는다.
- [x] 함수(또는 메서드)의 길이가 10라인을 넘어가지 않도록 구현한다.
- [x] indent(인덴트, 들여쓰기) depth를 2를 넘지 않도록 구현한다. 1까지만 허용한다.
- [x] 모든 엔티티를 작게 유지한다.
- [x] 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
- [x] spring.jpa.hibernate.ddl-auto=validate 추가
- [x] 상수 사용

### 이름

- [x] 이름을 생성한다.
    - [x] 이름은 null 일 수 없다.
    - [x] 이름은 공백일 수 없다.

### 가격

- [x] 가격을 생성한다.
    - [x] 가격이 음수일 수 없다.
    - [x] 가격은 필수값이다.

### 갯수

- [x] 갯수를 생성한다.
    - [x] 갯수가 음수일 수 없다.

### 메뉴

#### 메뉴 그룹

- [x] [x] 메뉴 그룹을 생성한다.
    - [x] [x] 이름을 갖는다.
- [-] [x] 메뉴 그룹 목록을 조회한다.

#### 메뉴

- [x] [x] 메뉴를 생성한다.
    - [x] [x] 이름을 갖는다.
    - [x] [x] 가격을 갖는다.
    - [-] [x] 메뉴 상품 리스트를 갖는다.
    - [x] [x] 메뉴 그룹이 없을 경우 메뉴를 생성할 수 없다.
    - [-] [x] 메뉴의 가격이 메뉴 상품의 합보다 클 수 없다.
    - [x] [x] 가격이 없을 경우 메뉴를 생성할 수 없다.
    - [x] [x] 가격이 0원보다 작을 경우 메뉴를 생성할 수 없다.
    - [x] 이름이 없을 수 없다.
- [-] [x] 메뉴 목록을 조회한다.

#### 메뉴 상품

- [x] 메뉴 상품을 생성한다.
    - [x] 메뉴를 필수로 갖는다.
    - [x] 상품을 필수로 갖는다.
    - [x] 갯수를 필수로 갖는다.

#### 메뉴 상품 일급 콜렉션

- [x] 메뉴 상품 일급 콜렉션을 생성한다.
- [x] 메뉴 상품 일급 콜렉션의 가격의 합을 구한다.
- [x] 메뉴 상품 일급 콜렉션의 empty 여부를 반환한다.
- [x] 메뉴와 매핑한다.

### 주문

- [x] [x] 주문을 생성한다.
    - [x] [x] 주문 항목이 비어있을 수 없다.
    - [x] [x] 주문 테이블은 비어있을 수 없다.
    - [x] [x] 주문 항목의 수와 메뉴의 수는 같아야 한다.
    - [x] [x] 주문 상태는 요리중 상태를 가진다.
    - [x] [x] 주문 시간을 갖는다.
    - [x] [x] 주문 테이블을 가진다.
    - [x] [x] 주문 항목을 갖는다.
- [x] [x] 주문상태를 식사중으로 변경한다.
    - [x] [x] 주문완료일 경우 주문상태를 변경할 수 없다.
- [x] [x] 주문상태를 완료로 변경한다.
- [-] [x] 주문을 조회한다.

### 주문 항목 일급 콜렉션

- [x] 주문 항목 일급 콜렉션을 생성한다.
    - [x] 주문항목이 empty 일수 없다.
- [x] 주문 항목의 갯수를 구한다.
- [x] 주문 항목의 메뉴 아이디를 조회한다.
- [x] 주문 항목을 주문과 매핑한다.
- [x] 주문 항목의 empty 여부를 반환한다.

### 주문테이블

#### 테이블 그룹

- [x] [x] 테이블 그룹을 생성한다.
    - [x] [x] 주문 테이블의 갯수가 2보다 작을 수 없다.
    - [x] [x] 주문 테이블이 비어있을 수 없다.
    - [-] [x] 생성 시간을 필수로 갖는다.

#### 주문 테이블

- [x] [x] 주문 테이블을 생성한다.
    - [x] [x] 주문 테이블 그룹은 null 이다.
    - [x] [x] 고객수를 0명이다.
    - [x] [x] 착석 여부를 공석이다.
- [x] [x] 공석 상태로 변경한다.
    - [x] [x] 테이블 그룹이 없을 수 없다.
    - [-] [x] 요리중일 경우 변경할 수 없다.
    - [-] [x] 식사중일 경우 변경할 수 없다.
- [x] [x] 손님수를 변경한다.
    - [x] [x] 0명보다 작을 수 없다.
    - [x] [x] 주문테이블이 없을 경우 손님수를 변경할 수 없다.
    - [x] [x] 테이블이 공석 상태면 손님수를 변경할 수 없다.
- [x] [x] 테이블 그룹을 해제한다.
    - [-] [x] 요리중일 경우 해제할 수 없다.
    - [-] [x] 식사중일 경우 해제할 수 없다.
- [-] [x] 주문 테이블을 조회한다.

#### 손님 수

- [x] 손님 수를 생성한다.
    - [x] 손님 수가 0명보다 적을 수 없다.

#### 주문 테이블 일급 콜렉션

- [x] 주문 테이블 일급 콜렉션을 생성한다.
    - [x] 주문 테이블들이 empty 일 수 없다.
    - [x] 주문 테이블의 갯수가 2보다 작을 수 없다.
    - [x] 주문 테이블의 상태가 empty 가 아니면 안된다.
- [x] 테이블 그룹을 해제한다.
- [x] 테이블 그룹을 매핑한다.
- [x] 주문 테이블 아이디들을 반환한다.

### 상품

- [x] [x] 상품을 생성한다.
    - [x] [x] 가격을 필수로 갖는다.
    - [x] [-] 이름을 필수로 갖는다.
- [-] [x] 상품 목록을 조회한다.

### 주문 항목

- [x] 주문 항목을 생성한다.
    - [-] 주문을 갖는다. 필수 x
    - [x] 메뉴를 갖는다.
    - [x] 갯수를 갖는다.
    - [x] 메뉴가 없을 수 없다.
    - [-] 갯수가 없을 수 없다.

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
