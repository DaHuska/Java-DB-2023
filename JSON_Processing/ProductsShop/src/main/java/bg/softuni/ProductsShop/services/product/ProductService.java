package bg.softuni.ProductsShop.services.product;

import bg.softuni.ProductsShop.domain.models.dtos.product.ProductBasicInfoWithSellerDTO;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    List<ProductBasicInfoWithSellerDTO> getProductsInRangeWithNoBuyer(BigDecimal lowBoundary, BigDecimal highBoundary) throws IOException;
}
