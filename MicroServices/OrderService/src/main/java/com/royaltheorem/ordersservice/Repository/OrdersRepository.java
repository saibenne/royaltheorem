package com.royaltheorem.ordersservice.Repository;

import com.royaltheorem.ordersservice.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface OrdersRepository extends JpaRepository<Order,Integer> {
    List<Order> findByUserId(int userId);
    List<Order> findByDesignId(int designId);

    void deleteAllByUserId(int orderId);

}
