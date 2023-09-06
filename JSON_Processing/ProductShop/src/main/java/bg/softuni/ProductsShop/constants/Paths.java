package bg.softuni.ProductsShop.constants;

import java.nio.file.Path;

public enum Paths {
    ;
    public static final Path CATEGORIES_JSON_PATH =
            Path.of("src", "main", "resources", "dbContent", "json", "categories.json");

    public static final Path USERS_JSON_PATH =
            Path.of("src", "main", "resources", "dbContent", "json", "users.json");

    public static final Path PRODUCTS_JSON_PATH =
            Path.of("src", "main", "resources", "dbContent", "json", "products.json");

    public static final Path FIRST_JSON_PATH =
            Path.of("src", "main", "resources", "output", "json", "1.json");

    public static final Path SECOND_JSON_PATH =
            Path.of("src", "main", "resources", "output", "json", "2.json");

    public static final Path THIRD_JSON_PATH =
            Path.of("src", "main", "resources", "output", "json", "3.json");

    public static final Path FORTH_JSON_PATH =
            Path.of("src", "main", "resources", "output", "json", "4.json");
}
