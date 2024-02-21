package com.example.restaurant.Service;


import com.example.restaurant.Model.User;
import com.example.restaurant.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository u){
        userRepository = u;
    }


    public void setuser(User u){userRepository.save(u);}
    public Optional<User> getuserbyid(Integer id){return userRepository.findById(id);}
    public void deleteuserbyid(Integer i){userRepository.deleteUserById(i);}
    public Optional<User> getuserbyname(String username) {
        return userRepository.findByName(username);
    }
    public Optional<User> getuserbyrole(String role) {
        return userRepository.findByName(role);
    }
    public List<User> getalluser(){return userRepository.findAll();}
}