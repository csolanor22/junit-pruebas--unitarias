/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexos.hulkStore.controller;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;

import com.nexos.hulkstore.controller.ControllerIntarface;
import com.nexos.hulkstore.domain.Product;
import com.nexos.hulkstore.domain.User;
import com.nexos.hulkstore.service.ProductService;
import com.nexos.hulkstore.service.UserService;

/**
 *
 * @author Carlos Montealegre
 */
@RunWith(MockitoJUnitRunner.class)
public class ControllerIntarfaceTest {

    @InjectMocks
    public ControllerIntarface controllerIntarface;

    @Mock
    private UserService userService;

    @Mock
    private ProductService productService;

    public ControllerIntarfaceTest() {
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
     * Test of loginForm method, of class ControllerIntarface.
     */
    @Test
    public void testLoginForm() {
        String form = controllerIntarface.loginForm();
        assertNotNull(form);
    }

    /**
     * Test of loginValidate method, of class ControllerIntarface.
     */
    @Test
    public void testLoginValidate() {
        User user = createUser();
        ModelMap map = createMap();
        String form = controllerIntarface.loginValidate(user, map);
        assertNotNull(form);
    }

    /**
     * Test of loginValidate method, of class ControllerIntarface.
     */
    @Test
    public void testLoginValidateStageOne() {
        User user = createUser();
        ModelMap map = createMap();
        
        when(userService.getAllUserById(user.getId())).thenReturn(user);
        String form = controllerIntarface.loginValidate(user, map);
        Assert.assertEquals("redirect:/listForm",form);
    }

    @Test
    public void testLoginValidateStageTwo() {
        User user = createUser();
        user.setId("1");
        ModelMap map = createMap();
        String form = controllerIntarface.loginValidate(user, map);
        Assert.assertEquals("login-form",form);
    }
    @Test
    public void testLoginValidateStageThree() {
        User user = createUser();
        user.setRol("2");
        ModelMap map = createMap();
        when(userService.getAllUserById(user.getId())).thenReturn(user);
        String form = controllerIntarface.loginValidate(user, map);
        Assert.assertEquals("redirect:/productlistClientForm",form);
    }
    @Test
    public void testLoginValidateStageFour() {
        User user = createUser();
        User user2 = createUser();
        ModelMap map = createMap();
        when(userService.getAllUserById(user.getId())).thenReturn(user);
        user2.setPass("7894");
        String form = controllerIntarface.loginValidate(user2, map);
        Assert.assertEquals("login-form",form);
    }
    /**
     * Test of adminForm method, of class ControllerIntarface.
     */
    @Test
    public void testAdminForm() {
        String form = controllerIntarface.adminForm();
        assertNotNull(form);
    }

    /**
     * Test of userCreateForm method, of class ControllerIntarface.
     */
    @Test
    public void testUserCreateForm() {
        User user = createUser();
        ModelMap map = createMap();
        String form = controllerIntarface.userCreateForm(user, map);
        assertNotNull(form);
    }
        @Test
    public void testUserCreateFormStageTwo() {
        User user = createUser();
        ModelMap map = createMap();
        when(userService.getAllUserById(user.getId())).thenThrow();
        String form = controllerIntarface.userCreateForm(user, map);
        Assert.assertEquals("listUser-form",form);
    }

    /**
     * Test of customerCreateForm method, of class ControllerIntarface.
     */
    @Test
    public void testCustomerCreateForm() {
        User user = createUser();
        ModelMap map = createMap();
        String form = controllerIntarface.customerCreateForm(user, map);
        assertNotNull(form);
    }
    
    @Test
    public void testCustomerCreateFormStageTwo() {
        User user = createUser();
        ModelMap map = createMap();
        when(userService.getAllUserById(user.getId())).thenThrow();
        String form = controllerIntarface.customerCreateForm(user, map);
        assertNotNull(form);
    }

    /**
     * Test of getEditForm method, of class ControllerIntarface.
     */
    @Test
    public void testGetEditForm() {
        User user = createUser();
        ModelMap model = createMap();
        when(userService.getAllUserById(user.getId())).thenReturn(user);
        String form = controllerIntarface.getEditForm(model, user.getId());
        assertNotNull(form);
    }

    /**
     * Test of postEditForm method, of class ControllerIntarface.
     */
    @Test
    public void testPostEditForm() {
        User user = createUser();
        ModelMap map = createMap();
        String form = controllerIntarface.postEditForm(user, map);
        assertNotNull(form);
    }
    @Test
    public void testPostEditFormStageTwo() {
        User user = createUser();
        ModelMap map = createMap();
        when(userService.editUser(user)).thenThrow();
        String form = controllerIntarface.postEditForm(user, map);
        Assert.assertEquals("listUser-form",form);
    }

    /**
     * Test of listForm method, of class ControllerIntarface.
     */
    @Test
    public void testListForm() {
        ModelMap map = createMap();
        String form = controllerIntarface.listForm(map);
        assertNotNull(form);
    }

    /**
     * Test of registerForm method, of class ControllerIntarface.
     */
    @Test
    public void testRegisterForm() {
        String form = controllerIntarface.registerForm();
        assertNotNull(form);
    }

    /**
     * Test of deleteUser method, of class ControllerIntarface.
     */
    @Test
    public void testDeleteUser() {
        User user = createUser();
        ModelMap model = createMap();
        String form = controllerIntarface.deleteUser(model, user.getId());
        assertNotNull(form);
    }
    

    /**
     * Test of productListForm method, of class ControllerIntarface.
     */
    @Test
    public void testProductListForm() {
        ModelMap map = createMap();
        String form = controllerIntarface.productListForm(map);
        assertNotNull(form);
    }

    /**
     * Test of productlistClientForm method, of class ControllerIntarface.
     */
    @Test
    public void testProductlistClientForm() {
        ModelMap map = createMap();
        String form = controllerIntarface.productlistClientForm(map);
        assertNotNull(form);
    }

    /**
     * Test of getProductForm method, of class ControllerIntarface.
     */
    @Test
    public void testGetProductForm() {
        String form = controllerIntarface.getProductForm();
        assertNotNull(form);
    }

    /**
     * Test of postProductForm method, of class ControllerIntarface.
     */
    @Test
    public void testPostProductForm() {
        Product product = createProduct();
        ModelMap map = createMap();
        String form = controllerIntarface.postProductForm(product, map);
        assertNotNull(form);
    }
    
    @Test
    public void testPostProductFormStageTwo() throws Exception {
        Exception exception = new Exception();
        Product product = createProduct();
        ModelMap map = createMap();
        when(productService.getAllProductById(product.getId())).thenThrow(exception);
        String form = controllerIntarface.postProductForm(product, map);
        assertNotNull(form);
    }

    /**
     * Test of getEditProduct method, of class ControllerIntarface.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetEditProduct() throws Exception {
        Product producto = createProduct();
        ModelMap model = createMap();
        String form = controllerIntarface.getEditProduct(model, producto.getId());
        assertNotNull(form);
    }
    
    @Test(expected = Exception.class)
    public void testGetEditProductStageTwo() throws Exception {
        Product producto = createProduct();
        ModelMap model = createMap();
        when(productService.getAllProductById(producto.getId())).thenThrow();
        controllerIntarface.getEditProduct(model, producto.getId());
    }

    /**
     * Test of postEditForm method, of class ControllerIntarface.
     */
    @Test
    public void testPostEditProductForm() {
        Product product = createProduct();
        ModelMap map = createMap();
        String form = controllerIntarface.postEditProductForm(product, map);
        assertNotNull(form);
    }
    
    @Test
    public void testPostEditProductFormStageTwo() throws Exception {
        Exception exception = new Exception();
        Product product = createProduct();
        ModelMap map = createMap();
        when(productService.editProduct(product)).thenThrow(exception);
        String form = controllerIntarface.postEditProductForm(product, map);
        assertNotNull(form);
    }

    /**
     * Test of deleteProduct method, of class ControllerIntarface.
     */
    @Test
    public void testDeleteProduct() {
        Product producto = createProduct();
        ModelMap model = createMap();
        String form = controllerIntarface.deleteProduct(model, producto.getId());
        assertNotNull(form);
    }
    
    @Test
    public void testDeleteProductStageTwo() {
        Product producto = createProduct();
        ModelMap model = createMap();
        String form = controllerIntarface.deleteProduct(model, producto.getId());
        assertNotNull(form);
    }

    private User createUser() {
        User user = new User();
        user.setId("008");
        user.setName("008");
        user.setEmail("test@.gmail.com");
        user.setPass("1234");
        user.setRol("1");
        return user;
    }

    private Product createProduct() {
        Product product = new Product();
        product.setId("134");
        product.setDescription("test");
        product.setBrand("test");
        product.setPrice("10.000");
        product.setQuantity("33");
        return product;
    }

    private ModelMap createMap() {
        ModelMap model = new ModelMap();
        return model;
    }

    /**
     * Test of getShoppingCar method, of class ControllerIntarface.
     */
    @Test
    public void testGetShoppingCar() {
        ModelMap model = createMap();
        String form = controllerIntarface.getShoppingCar(model);
        assertNotNull(form);
    }

}
