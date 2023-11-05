/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexos.hulkstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexos.hulkstore.domain.User;
import com.nexos.hulkstore.repository.UserRepository;

/**
 *
 * @author Carlos Montealegre
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Iterable<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getAllUserById(String id) {
        User user = new User();
        return userRepository.findById(id).orElse(user);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public User editUser(User user) {
        User userFind = getAllUserById(user.getId());
        validateEditUser(user, userFind);
        return userRepository.save(userFind);
    }

    protected void validateEditUser(User initial, User end) {
        end.setName(initial.getName());
        end.setEmail(initial.getEmail());
        end.setRol(initial.getRol());
        end.setPass(initial.getPass());
    }

    @Override
    public User testMethod(User userOne, User userTwo) throws Exception {
        User userResponse = getAllUserById(userOne.getId());
        System.out.println(userOne.getPass());
       System.out.println(userResponse.getPass());
        if (userOne.getPass().equals(userResponse.getPass())) {
            try {
                User userResponseSave = createUser(userTwo);
                return userResponseSave;
            } catch (Exception ex) {
                User userResponseSave = editUser(userOne);
                return userResponseSave;
            }
        }
        deleteUser(userResponse.getId());
        return userTwo;
    }
}
