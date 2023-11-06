/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexos.hulkStore.service;

import com.nexos.hulkStore.domain.Product;
import com.nexos.hulkStore.repository.ProductRepository;
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
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Carlos Montealegre
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @InjectMocks
    public ProductServiceImpl productServiceImpl;

    @Mock
    public ProductService productService;

    @Mock
    public ProductRepository productRepository;

    public ProductServiceImplTest() {
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
     * Test of getAllProduct method, of class ProductServiceImpl.
     */
    @Test
    public void testGetAllProduct() {
        assertNotNull(productServiceImpl.getAllProduct());
    }

    /**
     * Test of getAllProductById method, of class ProductServiceImpl.
     */
    @Test
    public void testGetAllProductById() throws Exception {
        Product product = setProduct();
        Product productResponse = productServiceImpl.getAllProductById(product.getId());
        assertNotNull(productResponse);
    }

    /**
     * Test of createProduct method, of class ProductServiceImpl.
     */
    @Test
    public void testCreateProduct() {
        Product product = setProduct();
        Product productResponse = productServiceImpl.createProduct(product);
        assertNull(productResponse);
    }

    /**
     * Test of editProduct method, of class ProductServiceImpl.
     */
    @Test
    public void testEditProduct() throws Exception {
        Product product = setProduct();
        Product productResponse = productServiceImpl.editProduct(product);
        assertNotNull(product);
    }

    /**
     * Test of deleteProduct method, of class ProductServiceImpl.
     */
    @Test
    public void testDeleteProduct() {
        Product product = setProduct();
        productServiceImpl.deleteProduct(product.getId());
    }

    private Product setProduct() {
        Product product = new Product();
        product.setId("123");
        product.setDescription("Mueñoco Thor");
        product.setBrand("Avenger");
        product.setPrice("10,000");
        product.setQuantity("3");
        return product;

    }

}
