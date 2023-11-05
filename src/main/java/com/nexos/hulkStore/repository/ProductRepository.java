/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexos.hulkstore.repository;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nexos.hulkstore.domain.Product;

/**
 *
 * @author Carlos Montealegre
 */
@Repository
@EntityScan
public interface ProductRepository extends CrudRepository<Product, String> {

}
