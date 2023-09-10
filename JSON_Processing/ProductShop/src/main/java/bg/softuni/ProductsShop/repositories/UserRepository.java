package bg.softuni.ProductsShop.repositories;


import bg.softuni.ProductsShop.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM `products_shop`.users order by RAND() LIMIT 1", nativeQuery = true)
    Optional<User> getRandomEntity();

    List<User> findAllBySoldProductsBuyerIsNotNullOrderBySoldProductsBuyerLastName();
}
