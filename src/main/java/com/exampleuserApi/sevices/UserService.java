package com.exampleuserApi.sevices;


import com.exampleuserApi.entities.User;
import com.exampleuserApi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User addUser(User user){
        if(userRepository.existsUserByEmail(user.getEmail())){
            user.setEmail("Email already exists");
            return user;
        }
        else if(userRepository.existsUserByUsername(user.getUsername())){
            user.setEmail("Username already exists");
            return user;
        }
        else{
            return userRepository.save(user);
        }
    }

    public User updateUser(Long id, User user){
        if(userRepository.existsById(id)){
            user.setId(id);
            return userRepository.save( user );
        }throw new NullPointerException("trying to update a non-existing data at id " + id);
    }

    public Boolean deleteUser(Long id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }throw new NullPointerException("trying to delete a non-existing data at id " + id);
    }

}
