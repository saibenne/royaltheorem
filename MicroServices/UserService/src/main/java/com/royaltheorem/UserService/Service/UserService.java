package com.royaltheorem.UserService.Service;

import com.royaltheorem.UserService.Order;
import com.royaltheorem.UserService.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface UserService {
    User addUser(User user);
    Optional<User> getUser(int id);
    List<User> getAllUsers();
    List<Order> getAllOrders(int id);
    String placeOrder(int userId,int designId);
    String updateUser(int id,User user);
    String deleteAccount(int id);


}
