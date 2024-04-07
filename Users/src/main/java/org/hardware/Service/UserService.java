package org.hardware.Service;

import org.hardware.Entity.User;
import org.hardware.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User createUser(User user) {
        User user1 = userRepository.save(user);
        log.info("Product updated to the database. \n " +
                "User: " + user1.getUserId() + "\n" +
                "User Full Name: " + user1.getUserName() + "\n" +
                "User phone: " + user1.getPhone() + "\n" +
                "User email: " + user1.getEmail());
        return user1;
    }

    public Optional<User> findUserById(UUID id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    public void deleteUserById(UUID userid) {

        userRepository.deleteById(userid);
        log.info("Deleted product : " + userid);
    }


    public List<User> findAllProduct() {
        List<User> all = userRepository.findAll();
        return all;
    }
}
