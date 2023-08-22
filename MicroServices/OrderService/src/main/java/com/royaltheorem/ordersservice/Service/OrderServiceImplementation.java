package com.royaltheorem.ordersservice.Service;

import com.netflix.discovery.converters.Auto;
import com.royaltheorem.ordersservice.Order;
import com.royaltheorem.ordersservice.Repository.OrdersRepository;
import org.apache.catalina.util.ServerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class OrderServiceImplementation implements OrderService{

    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public String placeOrder(Order order) {
        try {
            order.setDate(LocalDateTime.now());
            order.setStatus("success");
            ordersRepository.save(order);
            return "success";
        }
        catch (Exception e)
        {
            return "failure";
        }
    }

    public Object getDesignDetails(int designId)
    {
        List<ServiceInstance> instances=discoveryClient.getInstances("DESIGN-SERVICE");
        String uri=instances.get(0).getUri().toString();
        Object design=restTemplate.getForObject(uri+"/getDesignById/"+designId,Object.class);
        return design;
    }
    @Override
    public Order getOrderDetails(int orderId) {

        Order order= ordersRepository.findById(orderId).stream().toList().get(0);
        Object design=getDesignDetails(order.getDesignId());
        order.setDesign(design);
        return order;
    }

    @Override
    public List<Order> getOrdersByDesignId(int designId) {
        return ordersRepository.findByDesignId(designId);
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {

        System.out.println("clicked..");
        List<Order> orders=new ArrayList<>();
        for(Order order:ordersRepository.findByUserId(userId))
        {
            order.setDesign(getDesignDetails(order.getDesignId()));
            orders.add(order);
        }
        return orders;
    }

    @Override
    public String getStatus(int orderId) {
        return ordersRepository.findById(orderId).stream().toList().get(0).getStatus();
    }

    @Override
    public String updateStatus(int orderId,String status) {
        Order orderToUpdate=ordersRepository.findById(orderId).stream().toList().get(0);
        orderToUpdate.setStatus(status);
        ordersRepository.save(orderToUpdate);
        return "status order with id - "+orderId+" updated successfully...";
    }

    @Override
    public String cancelOrder(int orderId) {
        ordersRepository.deleteById(orderId);
        return "deleted successfully...";
    }

    @Override
    public String cancelAllOrders(int userId) {
        ordersRepository.deleteAllByUserId(userId);
        return null;
    }
}
