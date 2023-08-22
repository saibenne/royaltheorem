package com.royaltheorem.ordersservice.Service;

import com.royaltheorem.ordersservice.Order;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public interface OrderService {
    String placeOrder(Order order);
    Order getOrderDetails(int orderId);
    List<Order> getOrdersByDesignId(int designId);

    List<Order> getOrdersByUserId(int userId);
    String getStatus(int orderId);
    String updateStatus(int orderId,String status);
    String cancelOrder(int orderId);
    String cancelAllOrders(int userId);


}
