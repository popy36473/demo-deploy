package springbootrestapi.springapi_session06.init_data;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import springbootrestapi.springapi_session06.model.entity.Product;
import springbootrestapi.springapi_session06.repository.ProductRepository;

import java.time.LocalDate;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class InitData implements CommandLineRunner {
    private final ProductRepository productRepository;


    @Override
    public void run(String... args) throws Exception {
        if (productRepository.findAll().isEmpty()){
            List<Product> products = List.of(
                    new Product(null,"Laptop Gaming","ASUS",2024, LocalDate.of(2029, 12, 31),25990000.0),
                    new Product(null,"Laptop Gaming","ASUS",2024, LocalDate.of(2029, 12, 31),25990000.0),
                    new Product(null,"Laptop Gaming","ASUS",2024, LocalDate.of(2029, 12, 31),25990000.0),
                    new Product(null,"Laptop Gaming","ASUS",2024, LocalDate.of(2029, 12, 31),25990000.0),
                    new Product(null,"Laptop Gaming","ASUS",2024, LocalDate.of(2029, 12, 31),25990000.0),
                    new Product(null,"Laptop Gaming","ASUS",2024, LocalDate.of(2029, 12, 31),25990000.0)
            );
        }
    }
}
