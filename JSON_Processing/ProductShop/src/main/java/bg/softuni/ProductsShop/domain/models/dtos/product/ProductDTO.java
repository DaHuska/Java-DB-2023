package bg.softuni.ProductsShop.domain.models.dtos.product;

import bg.softuni.ProductsShop.domain.entities.User;
import bg.softuni.ProductsShop.domain.models.dtos.category.CategoryDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ProductDTO {
    private String name;
    private BigDecimal price;
    private User buyer;
    private User seller;
    private Set<CategoryDTO> categories;

    public static ProductsSoldWithCountDTO toProductsSoldWithCountDTO(Set<ProductDTO> soldProducts) {
        List<ProductBasicInfoDTO> productBasicInfoModelStream = soldProducts.stream()
                .map(ProductDTO::toProductBasicInfoModel)
                .toList();

        return ProductsSoldWithCountDTO.productsSoldWithCountDto(productBasicInfoModelStream);
    }

    private static ProductBasicInfoDTO toProductBasicInfoModel(ProductDTO productDto) {
        return new ProductBasicInfoDTO(productDto.getName(), productDto.getPrice());
    }
}
