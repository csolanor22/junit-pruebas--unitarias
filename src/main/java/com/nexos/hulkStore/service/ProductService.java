/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexos.hulkstore.service;

import com.nexos.hulkstore.domain.Product;

/**
 *
 * @author Carlos Montealegre
 */
public interface ProductService {

    public Iterable<Product> getAllProduct();

    public Product getAllProductById(String id) throws Exception;

    public Product createProduct(Product user);

    public Product editProduct(Product user) throws Exception;

    public void deleteProduct(String id);

}
