package bg.softuni.ProductsShop.domain.models.dtos.category;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter

public class CategorySummaryDTO {
    private String category;
    private Long productCount;
    private Double averagePrice;
    private BigDecimal totalRevenue;
}
