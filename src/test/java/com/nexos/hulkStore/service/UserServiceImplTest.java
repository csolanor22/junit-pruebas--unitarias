/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexos.hulkstore.service;

import java.util.Optional;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

import com.nexos.hulkstore.domain.User;
import com.nexos.hulkstore.repository.UserRepository;
import com.nexos.hulkstore.service.UserService;
import com.nexos.hulkstore.service.UserServiceImpl;

/**
 *
 * @author Carlos Montealegre
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    public UserServiceImpl userServiceImpl;

    @Mock
    public UserService userService;

    @Mock
    public UserRepository userRepository;

    public UserServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllUser method, of class UserServiceImpl.
     */
    @Test
    public void testGetAllUser() {
        assertNotNull(userServiceImpl.getAllUser());
    }

    /**
     * Test of createUser method, of class UserServiceImpl.
     */
    @Test
    public void testCreateUser() {
        User user = new User();
        user.setId("1234");
        user.setName("test");
        user.setEmail("test@gmail.com");
        user.setPass("1234");
        user.setRol("1");
        assertNull(userServiceImpl.createUser(user));
    }

    /**
     * Test of getAllUserById method, of class UserServiceImpl.
     */
    @Test
    public void testGetAllUserById() {
        User user = new User();
        user.setId("004");
        User userResponse = userServiceImpl.getAllUserById(user.getId());
        assertNotNull(userResponse);
    }

    /**
     * Test of deleteUser method, of class UserServiceImpl.
     */
    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setId("004");
        userServiceImpl.deleteUser(user.getId());
    }

    /**
     * Test of editUser method, of class UserServiceImpl.
     */
    @Test
    public void testEditUser() {
        User user = new User();
        user.setId("1234");
        user.setName("test");
        user.setEmail("test@gmail.com");
        user.setPass("1234");
        user.setRol("1");
        userServiceImpl.getAllUserById(user.getId());
        User response = userServiceImpl.editUser(user);
        assertNull(response);
    }

    /**
     * Test of validateEditUser method, of class UserServiceImpl.
     */
    @Test
    public void testValidateEditUser() {
        System.out.println("validateEditUser");
        User initial = createUser();
        User end = createUser();
        UserServiceImpl instance = new UserServiceImpl();
        instance.validateEditUser(initial, end);
    }

    /**
     * Test of testMethod method, of class UserServiceImpl.
     */
    @Test
    public void testTestMethod() throws Exception {
        User user = createUser();
        User user2 = createUser();
        user2.setId("4567");
        User responseUser = userServiceImpl.testMethod(user, user2);
        assertNotNull(responseUser);                
    }
    
    @Test
    public void testTestMethodStageTwoSave() throws Exception {
        User user = createUser();
        User user2 = createUser();
        user2.setId("4567");
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.save(user2)).thenReturn(user2);
        when(userServiceImpl.createUser(user)).thenReturn(user2);
        User responseUser = userServiceImpl.testMethod(user, user2);
        assertNotNull(responseUser);
    }
    
    @Test
    public void testTestMethodStageThreeException() throws Exception {
        User user = createUser();
        User user2 = createUser();
        user2.setId("4567");
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userServiceImpl.createUser(user2)).thenThrow(new NullPointerException());
        when(userRepository.save(user)).thenReturn(user);
        when(userServiceImpl.editUser(user)).thenReturn(user);
        User responseUser = userServiceImpl.testMethod(user, user2);
        assertNotNull(responseUser);
    }
    
    
    public User createUser(){
        User user = new User();
        user.setId("1234");
        user.setName("test");
        user.setEmail("test@gmail.com");
        user.setPass("1234");
        user.setRol("1");
        return user;
    }
}
