package com.royaltheorem.ordersservice;

import com.netflix.discovery.converters.Auto;
import com.royaltheorem.ordersservice.Service.OrderService;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController

public class Controller {
    @Autowired
    OrderService orderService;
    @PostMapping("/placeOrder")
    public String placeOrder(@RequestBody Order order){return orderService.placeOrder(order);}

    @GetMapping("/getOrderDetails/{id}")
    public Order getOrderDetails(@PathVariable("id") int id){return orderService.getOrderDetails(id);};

    @GetMapping("/getOrdersByUserId/{userId}")
    public List<Order> getOrderByUserId(@PathVariable("userId") int userId){return orderService.getOrdersByUserId(userId);}

    @PostMapping("/updateStatus/{id}/{status}")
    public String updateStatus(@PathVariable("id") int orderId, @PathVariable("status") String status)
    {
        return orderService.updateStatus(orderId,status);
    }
}
