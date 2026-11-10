package springbootrestapi.springapi_session06.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootrestapi.springapi_session06.model.dto.request.ProductDTO;
import springbootrestapi.springapi_session06.model.dto.response.ApiDataResponse;
import springbootrestapi.springapi_session06.model.entity.Product;
import springbootrestapi.springapi_session06.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @Value("${page_size_product}")
    private Integer pageSize;

    @GetMapping
    public ResponseEntity<ApiDataResponse<Page<Product>>> getProducts(@RequestParam(name = "page", defaultValue = "1") Integer page) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Lấy danh sách sản phẩm trang " + page + " thành công",
                productService.getAllProducts(page - 1, pageSize),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiDataResponse<Product>> insertProduct(@Valid @RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Thêm mới sản phẩm " + productDTO.getProName() + " thành công",
                productService.insertProduct(productDTO),
                null,
                HttpStatus.CREATED
        ), HttpStatus.CREATED);
    }

    @GetMapping("/{proId}")
    public ResponseEntity<ApiDataResponse<Product>> getProductById(@PathVariable Long proId) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Lấy thông tin sản phẩm " + proId + " thành công",
                productService.getProductById(proId),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @PutMapping("/{proId}")
    public ResponseEntity<ApiDataResponse<Product>> updateProduct(@PathVariable Long proId, @Valid @RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Cập nhật sản phẩm " + proId + " thành công",
                productService.updateProduct(proId, productDTO),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @PatchMapping("{proId}")
    public ResponseEntity<ApiDataResponse<Product>> updatePartialProduct(@PathVariable Long proId, @RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Cập nhật sản phẩm " + proId + " thành công",
                productService.updatePartialProduct(proId, productDTO),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }
}
