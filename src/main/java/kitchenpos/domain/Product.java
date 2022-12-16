package kitchenpos.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Embedded
    private Price price;

    public Product() {
    }

    public Product(final String name, final BigDecimal price) {
        this.name = name;
        this.price = Price.from(price);
    }

    public static Product of(String name, BigDecimal price) {
        return new Product(name, price);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Price getPrice() {
        return price;
    }
}
