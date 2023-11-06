/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexos.hulk_store.service;

import com.nexos.hulk_store.domain.User;

/**
 *
 * @author Carlos Montealegre
 */
public interface UserService {

    public Iterable<User> getAllUser();

    public User getAllUserById(String id);

    public User createUser(User user);

    public User editUser(User user);

    public void deleteUser(String id);

    public User testMethod(User userOne, User userTwo) throws Exception;
}
