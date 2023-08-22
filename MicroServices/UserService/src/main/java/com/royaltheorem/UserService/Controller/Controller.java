package com.royaltheorem.UserService.Controller;

import com.royaltheorem.UserService.Service.UserService;
import com.royaltheorem.UserService.User;
import jakarta.ws.rs.Path;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class Controller {
    @Autowired
    UserService userService;
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){return userService.getAllUsers();}
    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){return userService.addUser(user);}
    @GetMapping("/getUserById/{id}")
    public Optional<User> getUserById(@PathVariable("id") int id){return userService.getUser(id);}
    @GetMapping("/getAllOrdersById/{id}")
    public Object getAllOrdersById(@PathVariable("id") int id){return userService.getAllOrders(id);}
    @PutMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") int id,@RequestBody User user){return userService.updateUser(id,user);}

    @PostMapping("/placeOrder/{userId}/{designId}")
    public String placeOrder(@PathVariable("userId") int userId,@PathVariable("designId") int designId){return userService.placeOrder(userId,designId);}



}
