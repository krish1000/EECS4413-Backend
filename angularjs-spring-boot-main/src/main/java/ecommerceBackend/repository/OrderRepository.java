package ecommerceBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ecommerceBackend.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}