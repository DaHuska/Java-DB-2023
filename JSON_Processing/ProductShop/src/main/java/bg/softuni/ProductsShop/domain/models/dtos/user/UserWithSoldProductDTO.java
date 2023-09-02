package bg.softuni.ProductsShop.domain.models.dtos.user;

import bg.softuni.ProductsShop.domain.models.dtos.product.ProductSoldDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserWithSoldProductDTO {
    private String firstName;
    private String lastName;
    private Set<ProductSoldDTO> boughtProducts;


}
