package bg.softuni.ProductsShop.repositories;

import bg.softuni.ProductsShop.domain.entities.Category;
import bg.softuni.ProductsShop.domain.models.dtos.category.CategorySummaryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "select * from `products_shop`.categories order by RAND() LIMIT 1", nativeQuery = true)
    Optional<Category> getRandomEntity();

    // TODO: SQL syntax error???
    @Query(value =
            "SELECT new bg.softuni.ProductsShop.domain.models.dto.category.CategorySummaryDTO(c.name, COUNT(p.id), AVG(p.price), SUM(p.price)) " +
            "FROM Product p JOIN p.categories c GROUP BY c.id", nativeQuery = true)
    List<CategorySummaryDTO> getCategorySummary();
}
