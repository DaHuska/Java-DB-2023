package bg.softuni.ProductsShop.services.category;

import bg.softuni.ProductsShop.domain.models.dtos.category.CategorySummaryDTO;

import java.io.IOException;
import java.util.List;

public interface CategoryService {
    List<CategorySummaryDTO> getCategorySummary() throws IOException;
}
