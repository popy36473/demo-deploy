package springbootrestapi.springapi_session06.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import springbootrestapi.springapi_session06.exception.ProductNotFound;
import springbootrestapi.springapi_session06.model.dto.request.ProductDTO;
import springbootrestapi.springapi_session06.model.entity.Product;
import springbootrestapi.springapi_session06.repository.ProductRepository;
import springbootrestapi.springapi_session06.service.ProductService;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;


    @Override
    public Page<Product> getAllProducts(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return productRepository.findAll(pageable);
    }

    @Override
    public Product insertProduct(ProductDTO productDTO) {
        Product product = Product.builder()
                .proName(productDTO.getProName())
                .producer(productDTO.getProducer())
                .yearMaking(productDTO.getYearMaking())
                .expireDate(productDTO.getExpireDate())
                .price(productDTO.getPrice())
                .build();
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long proId) {
        return productRepository.findById(proId).orElseThrow(()->new ProductNotFound("Không tồn tại sản phẩm "+proId));
    }

    @Override
    public Product updateProduct(Long proId, ProductDTO productDTO) {
        productRepository.findById(proId).orElseThrow(()->new ProductNotFound("Không tồn tại sản phẩm "+proId));

        Product product = Product.builder()
                .proId(proId)
                .proName(productDTO.getProName())
                .producer(productDTO.getProducer())
                .yearMaking(productDTO.getYearMaking())
                .expireDate(productDTO.getExpireDate())
                .price(productDTO.getPrice())
                .build();

        return productRepository.save(product);
    }

    @Override
    public Product updatePartialProduct(Long proId, ProductDTO productDTO) {
        Product product = productRepository.findById(proId).orElseThrow(() -> new ProductNotFound("Không tồn tại sản phẩm " + proId));
        if(productDTO.getProName()!=null && !productDTO.getProName().isEmpty()){
            product.setProName(productDTO.getProName());
        }
        if(productDTO.getProducer()!=null && !productDTO.getProducer().isEmpty()){
            product.setProducer(productDTO.getProducer());
        }
        if(productDTO.getYearMaking()!=null && productDTO.getYearMaking()>0){
            product.setYearMaking(productDTO.getYearMaking());
        }
        if(productDTO.getExpireDate()!=null){
            product.setExpireDate(productDTO.getExpireDate());
        }
        if(productDTO.getPrice()!=null && productDTO.getPrice()>0){
            product.setPrice(productDTO.getPrice());
        }
        return productRepository.save(product);
    }
}
