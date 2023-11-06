/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexos.hulkstore.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nexos.hulkstore.domain.Product;
import com.nexos.hulkstore.domain.User;
import com.nexos.hulkstore.service.ProductService;
import com.nexos.hulkstore.service.UserService;

/**
 *
 * @author Carlos Montealegre
 */
@Controller
public class ControllerIntarface {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    private static final String LOGIN_FORM_STR = "login-form";
    private static final String LIST_USER_FORM_STR = "listUser-form";
    private static final String LIST_PRODUCT_FORM_STR = "listProduct-form";
    private static final String PRODUCT_LIST_STR = "productList";
    private static final String USER_LIST_STR = "userList";
    private static final String SUCCESS_MESSAGE_STR = "succesMessage";

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ControllerIntarface.class);

    /**
     * Login
     */
    @GetMapping("/login")
    public String loginForm() {
        return LOGIN_FORM_STR;
    }

    @PostMapping("/login")
    public String loginValidate(@ModelAttribute(LOGIN_FORM_STR) com.nexos.hulkStore.controller.dtos.User user, ModelMap model) {
        try {
            User response = userService.getAllUserById(user.getNameUser());
            
            if (user.getPassUser().equals(response.getPass())) {
                if (response.getRol().equals("1")) {
                    LOGGER.info("Send to formAdmin......");
                    return "redirect:/listForm";
                } else {
                    LOGGER.info("Send to formCustomer......");
                    return "redirect:/productlistClientForm";
                }
            }
            model.addAttribute("errorMessage", "Contraseña incorrecta");
            LOGGER.info("Password incorrect.....");
            return LOGIN_FORM_STR;
        } catch (Exception ex) {
            model.addAttribute("errorMessage", "El usuario no existe");
            LOGGER.info("El usuario no existe....");
            return LOGIN_FORM_STR;
        }
    }

    /**
     * Login
     */
    /**
     * create
     */
    @GetMapping("/adminForm")
    public String adminForm() {
        return "admin-form";
    }

    @PostMapping("/adminForm")
    public String userCreateForm(@ModelAttribute("adminForm") com.nexos.hulkStore.controller.dtos.User user, ModelMap model) {
        try {
            userService.getAllUserById(user.getUserId());
            model.addAttribute(USER_LIST_STR, userService.getAllUser());
            model.addAttribute(SUCCESS_MESSAGE_STR, "Este documento ya esta registrado.");
            LOGGER.info("El usuario ya existe!!!...");
            return LIST_USER_FORM_STR;
        } catch (Exception ex) {
            userService.createUser(this.mapUserDtotoUser(user));
            model.addAttribute(USER_LIST_STR, userService.getAllUser());
            model.addAttribute(SUCCESS_MESSAGE_STR, "Usuario creado con exito.");
            LOGGER.info("Usuario creado!!!...");
            return LIST_USER_FORM_STR;
        }
    }

    private User mapUserDtotoUser(com.nexos.hulkStore.controller.dtos.User user) {
        User userModel = new User();
        userModel.setPass(user.getPassUser());
        userModel.setRol(user.getRolUser());
        userModel.setEmail(user.getEmailUser());
        userModel.setName(user.getNameUser());
        return userModel;
    }

    @PostMapping("/registerForm")
    public String customerCreateForm(@ModelAttribute("registerForm") com.nexos.hulkStore.controller.dtos.User user, ModelMap model) {
        user.setRolUser("2");
        try {
            userService.getAllUserById(user.getUserId());
            model.addAttribute(SUCCESS_MESSAGE_STR, "Este documento ya esta registrado.");
            LOGGER.info("El usuario ya existe!!!...");
            return "redirect:/login";
        } catch (Exception ex) {
            userService.createUser(this.mapUserDtotoUser(user));
            model.addAttribute(SUCCESS_MESSAGE_STR, "Usuario creado con exito.");
            LOGGER.info("Usuario creado!!!...");
            return "redirect:/login";
        }
    }

    @GetMapping("/editUser/{id}")
    public String getEditForm(ModelMap model, @PathVariable(name = "id") String id) {
        User user = userService.getAllUserById(id);
        model.addAttribute("userForm", user);
        model.addAttribute("editMode", "true");
        model.addAttribute("disable", "true");
        return "edit-form";
    }

    @PostMapping("/editUser")
    public String postEditForm(@ModelAttribute("adminForm") com.nexos.hulkStore.controller.dtos.User user, ModelMap model) {
        try {
            userService.editUser(this.mapUserDtotoUser(user));
            model.addAttribute(SUCCESS_MESSAGE_STR, "Usuario editado con exito.");
            model.addAttribute(USER_LIST_STR, userService.getAllUser());
            LOGGER.info("Usuario editado!!!...");
            return LIST_USER_FORM_STR;
        } catch (Exception ex) {
            model.addAttribute(SUCCESS_MESSAGE_STR, "Usuario no editado.");
            model.addAttribute(USER_LIST_STR, userService.getAllUser());
            LOGGER.info("No se puede editar el usuario !!!...");
            return LIST_USER_FORM_STR;
        }
    }

    /**
     * edit
     */
    /**
     * list and delete
     */
    @GetMapping("/listForm")
    public String listForm(ModelMap model) {
        model.addAttribute(USER_LIST_STR, userService.getAllUser());
        return LIST_USER_FORM_STR;
    }

    @GetMapping("/registerForm")
    public String registerForm() {
        return "register-form";
    }
    
    
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(ModelMap model, @PathVariable(name = "id") String id) {
            userService.deleteUser(id);
            model.addAttribute(SUCCESS_MESSAGE_STR, "Se elimino el usuario");
            LOGGER.info("borrado exitoso...");
        return listForm(model);
    }

    /**
     * list and delete
     */
    /**
     * productproductlistClientForm
     */
    @GetMapping("/productlistForm")
    public String productListForm(ModelMap model) {
        model.addAttribute(PRODUCT_LIST_STR, productService.getAllProduct());
        return LIST_PRODUCT_FORM_STR;
    }

    @GetMapping("/productlistClientForm")
    public String productlistClientForm(ModelMap model) {
        model.addAttribute(PRODUCT_LIST_STR, productService.getAllProduct());
        return "listProductClient-form";
    }

    @GetMapping("/productForm")
    public String getProductForm() {
        return "product-form";
    }

    @PostMapping("/productForm")
    public String postProductForm(@ModelAttribute("adminForm") Product product, ModelMap model) {
        try {
            productService.getAllProductById(product.getId());
            model.addAttribute(PRODUCT_LIST_STR, productService.getAllProduct());
            model.addAttribute(SUCCESS_MESSAGE_STR, "Este producto ya fue registrado.");
            LOGGER.info("El producto ya existe!!!...");
            return LIST_PRODUCT_FORM_STR;
        } catch (Exception ex) {
            productService.createProduct(product);
            model.addAttribute(PRODUCT_LIST_STR, productService.getAllProduct());
            model.addAttribute(SUCCESS_MESSAGE_STR, "producto ingresado con exito.");
            LOGGER.info("productp ingresado!!!...");
            return LIST_PRODUCT_FORM_STR;
        }
    }

    @GetMapping("/editProduct/{id}")
    public String getEditProduct(ModelMap model, @PathVariable(name = "id") String id) throws Exception {
        Product product = productService.getAllProductById(id);
        model.addAttribute("productForm", product);
        model.addAttribute("editMode", "true");
        return "editProduct-form";
    }

    @PostMapping("/editProduct")
    public String postEditProductForm(@ModelAttribute("productForm") Product product, ModelMap model) {
        try {
            productService.editProduct(product);
            model.addAttribute(SUCCESS_MESSAGE_STR, "Producto editado con exito.");
            model.addAttribute(PRODUCT_LIST_STR, productService.getAllProduct());
            LOGGER.info("Producto editado!!!...");
            return LIST_PRODUCT_FORM_STR;
        } catch (Exception ex) {
            model.addAttribute(SUCCESS_MESSAGE_STR, "Producto no editado.");
            model.addAttribute(PRODUCT_LIST_STR, productService.getAllProduct());
            LOGGER.info("No se puede editar el Producto !!!...");
            return LIST_PRODUCT_FORM_STR;
        }
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(ModelMap model, @PathVariable(name = "id") String id) {
        productService.deleteProduct(id);
        model.addAttribute(SUCCESS_MESSAGE_STR, "Se elimino el producto");
        return productListForm(model);
    }

    /**
     * product
     */
    @GetMapping("/getShoppingCar")
    public String getShoppingCar(ModelMap model) {
        return "";
    }
    /**
     * product
     */
}
