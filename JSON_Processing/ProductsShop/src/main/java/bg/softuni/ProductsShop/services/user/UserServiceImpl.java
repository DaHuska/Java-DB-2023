package bg.softuni.ProductsShop.services.user;

import bg.softuni.ProductsShop.domain.models.dtos.user.UserDTO;
import bg.softuni.ProductsShop.domain.models.dtos.user.UserWithSoldProductDTO;
import bg.softuni.ProductsShop.domain.models.dtos.user.UserWithSoldProducts;
import bg.softuni.ProductsShop.domain.models.dtos.user.wrappers.UsersWithSoldProductsWrapperDTO;
import bg.softuni.ProductsShop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static bg.softuni.ProductsShop.constants.Paths.SECOND_JSON_PATH;
import static bg.softuni.ProductsShop.constants.Paths.FORTH_JSON_PATH;
import static bg.softuni.ProductsShop.constants.Utils.MODEL_MAPPER;
import static bg.softuni.ProductsShop.constants.Utils.writeIntoJsonFile;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserWithSoldProductDTO> getSuccUsers() throws IOException {
        List<UserWithSoldProductDTO> users = this.userRepository
                .findAllBySoldProductsBuyerIsNotNullOrderBySoldProductsBuyerLastName()
                .stream()
                .map(u -> MODEL_MAPPER.map(u, UserWithSoldProductDTO.class))
                .toList();

        writeIntoJsonFile(users, SECOND_JSON_PATH);

        return Collections.unmodifiableList(users);
    }

    @Override
    public UsersWithSoldProductsWrapperDTO getUsersAndSoldProductsWrapper() throws IOException {
        List<UserWithSoldProducts> userWithSoldProducts = this.userRepository
                .findAllBySoldProductsBuyerIsNotNullOrderBySoldProductsBuyerLastName()
                .stream()
                .map(u -> MODEL_MAPPER.map(u, UserDTO.class))
                .map(UserDTO::toUserWithProductDTO)
                .toList();

        final UsersWithSoldProductsWrapperDTO wrapper =
                new UsersWithSoldProductsWrapperDTO(userWithSoldProducts);

        writeIntoJsonFile(wrapper, FORTH_JSON_PATH);

        return wrapper;
    }
}
