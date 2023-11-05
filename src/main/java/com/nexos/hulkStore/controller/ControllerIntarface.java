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

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ControllerIntarface.class);

    /**
     * Login
     */
    @GetMapping("/login")
    public String loginForm() {
        return "login-form";
    }

    @PostMapping("/login")
    public String loginValidate(@ModelAttribute("loginForm") User user, ModelMap model) {
        try {
            User response = userService.getAllUserById(user.getName());
            
            if (user.getPass().equals(response.getPass())) {
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
            return "login-form";
        } catch (Exception ex) {
            model.addAttribute("errorMessage", "El usuario no existe");
            LOGGER.info("El usuario no existe....");
            return "login-form";
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
    public String userCreateForm(@ModelAttribute("adminForm") User user, ModelMap model) {
        try {
            userService.getAllUserById(user.getId());
            model.addAttribute("userList", userService.getAllUser());
            model.addAttribute("succesMessage", "Este documento ya esta registrado.");
            LOGGER.info("El usuario ya existe!!!...");
            return "listUser-form";
        } catch (Exception ex) {
            userService.createUser(user);
            model.addAttribute("userList", userService.getAllUser());
            model.addAttribute("succesMessage", "Usuario creado con exito.");
            LOGGER.info("Usuario creado!!!...");
            return "listUser-form";
        }
    }

    @PostMapping("/registerForm")
    public String customerCreateForm(@ModelAttribute("registerForm") User user, ModelMap model) {
        user.setRol("2");
        try {
            userService.getAllUserById(user.getId());
            model.addAttribute("succesMessage", "Este documento ya esta registrado.");
            LOGGER.info("El usuario ya existe!!!...");
            return "redirect:/login";
        } catch (Exception ex) {
            userService.createUser(user);
            model.addAttribute("succesMessage", "Usuario creado con exito.");
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
    public String postEditForm(@ModelAttribute("adminForm") User user, ModelMap model) {
        try {
            userService.editUser(user);
            model.addAttribute("succesMessage", "Usuario editado con exito.");
            model.addAttribute("userList", userService.getAllUser());
            LOGGER.info("Usuario editado!!!...");
            return "listUser-form";
        } catch (Exception ex) {
            model.addAttribute("succesMessage", "Usuario no editado.");
            model.addAttribute("userList", userService.getAllUser());
            LOGGER.info("No se puede editar el usuario !!!...");
            return "listUser-form";
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
        model.addAttribute("userList", userService.getAllUser());
        return "listUser-form";
    }

    @GetMapping("/registerForm")
    public String registerForm() {
        return "register-form";
    }
    
    
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(ModelMap model, @PathVariable(name = "id") String id) {
            userService.deleteUser(id);
            model.addAttribute("succesMessage", "Se elimino el usuario");
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
        model.addAttribute("productList", productService.getAllProduct());
        return "listProduct-form";
    }

    @GetMapping("/productlistClientForm")
    public String productlistClientForm(ModelMap model) {
        model.addAttribute("productList", productService.getAllProduct());
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
            model.addAttribute("productList", productService.getAllProduct());
            model.addAttribute("succesMessage", "Este producto ya fue registrado.");
            LOGGER.info("El producto ya existe!!!...");
            return "listProduct-form";
        } catch (Exception ex) {
            productService.createProduct(product);
            model.addAttribute("productList", productService.getAllProduct());
            model.addAttribute("succesMessage", "producto ingresado con exito.");
            LOGGER.info("productp ingresado!!!...");
            return "listProduct-form";
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
            model.addAttribute("succesMessage", "Producto editado con exito.");
            model.addAttribute("productList", productService.getAllProduct());
            LOGGER.info("Producto editado!!!...");
            return "listProduct-form";
        } catch (Exception ex) {
            model.addAttribute("succesMessage", "Producto no editado.");
            model.addAttribute("productList", productService.getAllProduct());
            LOGGER.info("No se puede editar el Producto !!!...");
            return "listProduct-form";
        }
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(ModelMap model, @PathVariable(name = "id") String id) {
        productService.deleteProduct(id);
        model.addAttribute("succesMessage", "Se elimino el producto");
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
