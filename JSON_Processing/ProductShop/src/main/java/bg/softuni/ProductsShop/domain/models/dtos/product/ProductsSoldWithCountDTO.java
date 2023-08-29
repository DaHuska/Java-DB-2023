package bg.softuni.ProductsShop.domain.models.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductsSoldWithCountDTO {
    private Integer count;
    private List<ProductBasicInfoDTO> products;

    public static ProductsSoldWithCountDTO productsSoldWithCountDto(List<ProductBasicInfoDTO> products) {
        return new ProductsSoldWithCountDTO(products.size(), products);
    }
}
