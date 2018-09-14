package be.bt.repository;

import be.bt.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrdersRepository extends JpaRepository<Order, Long> {
}
