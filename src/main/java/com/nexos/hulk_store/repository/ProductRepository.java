/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexos.hulkStore.repository;

import com.nexos.hulkStore.domain.Product;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlos Montealegre
 */
@Repository
@EntityScan
public interface ProductRepository extends CrudRepository<Product, String> {

}