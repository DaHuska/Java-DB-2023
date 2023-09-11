package bg.softuni.ProductsShop.services.category;

import bg.softuni.ProductsShop.domain.models.dtos.category.CategorySummaryDTO;
import bg.softuni.ProductsShop.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static bg.softuni.ProductsShop.constants.Paths.THIRD_JSON_PATH;
import static bg.softuni.ProductsShop.constants.Utils.writeIntoJsonFile;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategorySummaryDTO> getCategorySummary() throws IOException {
        final List<CategorySummaryDTO> categories = this.categoryRepository.getCategorySummary();

        writeIntoJsonFile(categories, THIRD_JSON_PATH);

        return Collections.unmodifiableList(categories);
    }
}

