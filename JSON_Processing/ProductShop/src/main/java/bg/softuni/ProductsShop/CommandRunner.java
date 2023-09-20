package bg.softuni.ProductsShop;

import bg.softuni.ProductsShop.services.product.ProductService;
import bg.softuni.ProductsShop.services.seed.SeedService;
import bg.softuni.ProductsShop.services.user.UserService;
import bg.softuni.ProductsShop.services.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CommandRunner implements CommandLineRunner {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final SeedService seedService;

    @Autowired
    public CommandRunner(ProductService productService, CategoryService categoryService, UserService userService, SeedService seedService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.seedService = seedService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedService.seedAll();
        //this.productService.getProductsInRangeWithNoBuyer(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));
        //this.userService.getSuccUsers();
        //this.categoryService.getCategorySummary();
        this.userService.getUsersAndSoldProductsWrapper();
    }
}
