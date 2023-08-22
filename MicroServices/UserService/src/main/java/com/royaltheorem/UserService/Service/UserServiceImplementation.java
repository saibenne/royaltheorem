package com.royaltheorem.UserService.Service;

import com.netflix.discovery.converters.Auto;
import com.royaltheorem.UserService.Order;
import com.royaltheorem.UserService.Repository.UserRepository;
import com.royaltheorem.UserService.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserServiceImplementation implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CacheManager cacheManager;
    @Override
    public User addUser(User user) {
        try {
            userRepository.save(user);
            return user;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public Optional<User> getUser(int id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {

        List<User> users=userRepository.findAll();
        for(User user:users)
        {
            user.setOrders(getAllOrders(user.getId()));
        }
        return users;
    }

    @Override
    @Cacheable(value = "AllOrders",key = "#id")
    public List<Order> getAllOrders(int id) {
        List<ServiceInstance> instances= discoveryClient.getInstances("ORDERS-SERVICE");
        String url=instances.get(0).getUri().toString();
        List<Order> orders=restTemplate.getForObject(url+"/getOrdersByUserId/"+id,List.class);
        return orders;
    }

    @Override
    @CacheEvict(value = "AllOrders",key="#userId")
    public String placeOrder(int userId, int designId) {
        User userToUpdate=userRepository.findById(userId).stream().toList().get(0);
        List<ServiceInstance> orderInstance =discoveryClient.getInstances("ORDERS-SERVICE");
        String uri=orderInstance.get(0).getUri().toString();
        Order order=new Order();
        order.setUserId(userId);
        order.setDesignId(designId);
        String status=restTemplate.postForObject(uri+"/placeOrder",order,String.class);
        return status;
    }

    @Override
    public String updateUser(int id,User user) {
        try {
            Optional<User> userBeforeUpdate = userRepository.findById(id);
            User userToUpdate = userBeforeUpdate.stream().toList().get(0);
            userToUpdate.setName(user.getName());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setContact(user.getContact());
            userRepository.save(userToUpdate);
            return user.getName() + " updated succesfully";
        }
        catch (Exception e)
        {
            return e+"";
        }
    }

    @Override

    public String deleteAccount(int id) {
        userRepository.deleteById(id);
        return "Account deleted successfully...";
    }
}
