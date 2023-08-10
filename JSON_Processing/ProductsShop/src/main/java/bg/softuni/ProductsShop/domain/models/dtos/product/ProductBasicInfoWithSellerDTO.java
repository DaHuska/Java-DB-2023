package bg.softuni.ProductsShop.domain.models.dtos.product;

import bg.softuni.ProductsShop.domain.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class ProductBasicInfoWithSellerDTO {
    private String name;
    private BigDecimal price;
    private String seller;

    public static ProductBasicInfoWithSellerDTO getFromProduct(Product product) {
        String fullName = product.getSeller().getFirstName() + " " + product.getSeller().getLastName();

        return new ProductBasicInfoWithSellerDTO(product.getName(), product.getPrice(), fullName);
    }
}
