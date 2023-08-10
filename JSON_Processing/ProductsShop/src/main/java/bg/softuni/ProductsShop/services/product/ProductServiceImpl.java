package bg.softuni.ProductsShop.services.product;

import bg.softuni.ProductsShop.domain.models.dtos.product.ProductBasicInfoWithSellerDTO;
import bg.softuni.ProductsShop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static bg.softuni.ProductsShop.constants.Utils.writeIntoJsonFile;
import static bg.softuni.ProductsShop.constants.Paths.FIRST_JSON_PATH;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductBasicInfoWithSellerDTO> getProductsInRangeWithNoBuyer(BigDecimal lowBoundary, BigDecimal highBoundary) throws IOException {
        List<ProductBasicInfoWithSellerDTO> products =
                this.productRepository
                        .findAllByPriceBetweenAndBuyerIsNullOrderByPrice(lowBoundary, highBoundary)
                        .stream()
                        .map(ProductBasicInfoWithSellerDTO::getFromProduct)
                        .toList();

        writeIntoJsonFile(products, FIRST_JSON_PATH);

        return products;
    }
}
