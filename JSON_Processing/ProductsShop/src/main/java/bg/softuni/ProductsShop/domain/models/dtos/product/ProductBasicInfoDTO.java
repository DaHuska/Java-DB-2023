package bg.softuni.ProductsShop.domain.models.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ProductBasicInfoDTO {

    private String name;
    private BigDecimal price;
}
