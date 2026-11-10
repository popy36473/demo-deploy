package springbootrestapi.springapi_session06.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbootrestapi.springapi_session06.model.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
