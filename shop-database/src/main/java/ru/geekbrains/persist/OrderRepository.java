package ru.geekbrains.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.persist.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
