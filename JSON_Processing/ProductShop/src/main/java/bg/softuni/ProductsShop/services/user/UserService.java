package bg.softuni.ProductsShop.services.user;

import bg.softuni.ProductsShop.domain.models.dtos.user.UserWithSoldProductDTO;
import bg.softuni.ProductsShop.domain.models.dtos.user.wrappers.UsersWithSoldProductsWrapperDTO;

import java.io.IOException;
import java.util.List;

public interface UserService {
    List<UserWithSoldProductDTO> getSuccUsers() throws IOException;

    UsersWithSoldProductsWrapperDTO getUsersAndSoldProductsWrapper() throws IOException;
}
