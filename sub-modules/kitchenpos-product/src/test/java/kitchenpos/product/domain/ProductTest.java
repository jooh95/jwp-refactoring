package kitchenpos.product.domain;

import kitchenpos.ProductApplication;
import kitchenpos.product.repository.ProductRepository;
import kitchenpos.utils.DatabaseCleanup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(classes = ProductApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@DisplayName("상품 관련 도메인 테스트")
public class ProductTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private DatabaseCleanup databaseCleanup;

    @BeforeEach
    void setUp() {
        databaseCleanup.execute();
    }

    @DisplayName("상품 생성 테스트")
    @Test
    void create() {
        // given
        final Product product = new Product("후라이드", BigDecimal.valueOf(10_000));

        // when
        final Product savedProduct = productRepository.save(product);

        // then
        assertThat(savedProduct).isNotNull();
    }

    @DisplayName("상품 생성 테스트")
    @Test
    void createProductExceptionNegativePrice() {
        // when / then
        assertThatThrownBy(() -> new Product("후라이드", BigDecimal.valueOf(-2_000)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
