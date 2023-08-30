package bg.softuni.ProductsShop.domain.models.dtos.user;

import bg.softuni.ProductsShop.domain.models.dtos.product.ProductDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDTO {
    private String firstName;
    private String lastName;
    private Integer age;
    private Set<ProductDTO> soldProducts;

    public UserWithSoldProducts toUserWithProductDTO() {
        return new UserWithSoldProducts(firstName, lastName, age, ProductDTO.toProductsSoldWithCountDTO(soldProducts));
    }
}
