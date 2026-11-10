package springbootrestapi.springapi_session06.service;

import org.springframework.data.domain.Page;
import springbootrestapi.springapi_session06.model.dto.request.ProductDTO;
import springbootrestapi.springapi_session06.model.entity.Product;

public interface ProductService {
    Page<Product> getAllProducts(Integer page, Integer pageSize);

    Product insertProduct(ProductDTO productDTO);

    Product getProductById(Long proId);

    Product updateProduct(Long proId, ProductDTO productDTO);

    Product updatePartialProduct(Long proId, ProductDTO productDTO);
}
