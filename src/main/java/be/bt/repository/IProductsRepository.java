package be.bt.repository;

import be.bt.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductsRepository extends JpaRepository<Product, Long> {
}
