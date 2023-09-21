package bg.softuni.ProductsShop.services.seed;

import bg.softuni.ProductsShop.domain.entities.Category;
import bg.softuni.ProductsShop.domain.entities.Product;
import bg.softuni.ProductsShop.domain.entities.User;
import bg.softuni.ProductsShop.domain.models.CategoryImportModel;
import bg.softuni.ProductsShop.domain.models.ProductImportModel;
import bg.softuni.ProductsShop.domain.models.UserImportModel;
import bg.softuni.ProductsShop.repositories.CategoryRepository;
import bg.softuni.ProductsShop.repositories.ProductRepository;
import bg.softuni.ProductsShop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.IntStream;

import static bg.softuni.ProductsShop.constants.Paths.*;
import static bg.softuni.ProductsShop.constants.Utils.GSON;
import static bg.softuni.ProductsShop.constants.Utils.MODEL_MAPPER;

@Service
public class SeedServiceImpl implements SeedService {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public SeedServiceImpl(UserRepository userRepository,
                           CategoryRepository categoryRepository,
                           ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void seedUsers() throws IOException {
        if (this.userRepository.count() > 0) return;

        final List<User> usersToSave = getAllUsersFromJson();

        this.userRepository.saveAll(usersToSave);
    }

    @Override
    public void seedCategories() throws IOException {
        if (this.categoryRepository.count() > 0) return;

        final List<Category> categoriesToSave = getAllCategoriesFromJson();

        this.categoryRepository.saveAll(categoriesToSave);
    }

    @Override
    public void seedProducts() throws IOException {
        if (this.productRepository.count() > 0) return;

        final List<Product> productsBeforeMap = getAllProductsFromJson();

        final List<Product> productsToSave = productsBeforeMap.stream()
                .map(this::setRandomCategories)
                .map(this::setRandomBuyer)
                .map(this::setRandomSeller)
                .toList();

        this.productRepository.saveAll(productsToSave);
    }

    private Product setRandomSeller(Product product) {
        User seller = this.userRepository
                .getRandomEntity()
                .orElseThrow(NoSuchElementException::new);

        while (product.getBuyer() != null && product.getBuyer().getId().equals(seller.getId())) {
            seller = this.userRepository
                    .getRandomEntity()
                    .orElseThrow(NoSuchElementException::new);
        }

        product.setSeller(seller);

        return product;
    }

    private Product setRandomBuyer(Product product) {
        if (product.getPrice().compareTo(BigDecimal.valueOf(750)) > 0) {
            final User buyer = this.userRepository
                    .getRandomEntity()
                    .orElseThrow(NoSuchElementException::new);

            product.setBuyer(buyer);
        }

        return product;
    }

    private Product setRandomCategories(Product product) {
        final Random random = new Random();

        long countOfCategories = random.nextLong(this.categoryRepository.count() - 2);

        Set<Category> categories = new HashSet<>();

        IntStream.range(0, (int) countOfCategories)
                .forEach(value -> {
                   categories.add(this.categoryRepository
                        .getRandomEntity()
                           .orElseThrow(NoSuchElementException::new));
                });

        product.setCategories(categories);

        return product;
    }

    private List<Category> getAllCategoriesFromJson() throws IOException {
        final FileReader fileReader = new FileReader(CATEGORIES_JSON_PATH.toFile());

        final List<Category> categories = Arrays.stream(GSON.fromJson(fileReader, CategoryImportModel[].class))
                .map(categoryImportModel -> MODEL_MAPPER.map(categoryImportModel, Category.class))
                .toList();

        fileReader.close();

        return categories;
    }

    private List<User> getAllUsersFromJson() throws IOException {
        final FileReader fileReader = new FileReader(USERS_JSON_PATH.toFile());

        final List<User> users = Arrays.stream(GSON.fromJson(fileReader, UserImportModel[].class))
                .map(userImportModel -> MODEL_MAPPER.map(userImportModel, User.class))
                .toList();

        fileReader.close();

        return users;
    }

    private List<Product> getAllProductsFromJson() throws IOException {
        final FileReader fileReader = new FileReader(PRODUCTS_JSON_PATH.toFile());

        final List<Product> products = Arrays.stream(GSON.fromJson(fileReader, ProductImportModel[].class))
                .map(productImportModel -> MODEL_MAPPER.map(productImportModel, Product.class))
                .toList();

        fileReader.close();

        return products;
    }
}
