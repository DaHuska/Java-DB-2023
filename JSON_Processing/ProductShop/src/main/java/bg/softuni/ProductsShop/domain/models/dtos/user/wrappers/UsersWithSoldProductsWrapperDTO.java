package bg.softuni.ProductsShop.domain.models.dtos.user.wrappers;

import bg.softuni.ProductsShop.domain.models.dtos.user.UserWithSoldProducts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersWithSoldProductsWrapperDTO {

    private Integer count;

    private List<UserWithSoldProducts> users;

    public UsersWithSoldProductsWrapperDTO(List<UserWithSoldProducts> users) {
        this.users = users;
        this.count = users.size();
    }
}
