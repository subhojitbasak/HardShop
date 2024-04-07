package org.hardware.Controller;

import org.hardware.Entity.User;
import org.hardware.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping(value = "/create")
    public ResponseEntity<String> createProduct(@RequestBody User user) {
        User user1 = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Registered!!");
    }

    @GetMapping(value = "/find/{id}")
    public Optional<User> findUserById(@PathVariable("id") UUID id) {
        Optional<User> userById = userService.findUserById(id);
        return userById;
    }

    @GetMapping(value = "/find")
    public List<User> findAll() {
        List<User> allUser = userService.findAllProduct();
        return allUser;
    }
    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable("id") UUID id) {
        userService.deleteUserById(id);
        return "User Deleted!!";
    }
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<String> updateProductById(@PathVariable("id") UUID id, @RequestBody User updatedUser) {
        Optional<User> findUserById = userService.findUserById(id);
        if (findUserById.isPresent()) {
            User existingUser = findUserById.get();
            existingUser.setUserName(updatedUser.getUserName());
            existingUser.setAddress(updatedUser.getAddress());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPhone(updatedUser.getPhone());
            userService.createUser(existingUser);
            return ResponseEntity.status(HttpStatus.OK).body("Product updated!!");
        }
        log.info("User not Found ");
        log.info("Entered id: " + id + "\n" +
                "Not found");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product not Found in the database!!");
    }

}
