package bg.softuni.ProductsShop.domain.models.dtos.user;

import bg.softuni.ProductsShop.domain.models.dtos.product.ProductsSoldWithCountDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserWithSoldProducts {
    private String firstName;
    private String lastName;
    private Integer age;

    private ProductsSoldWithCountDTO soldProducts;
}
