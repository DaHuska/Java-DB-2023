package bg.softuni.ProductsShop.domain.models.dtos.product;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductSoldDTO {
    private String name;
    private BigDecimal price;
    private String buyerFirstName;
    private String buyerLastName;
}
