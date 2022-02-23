package cg.repository;

import cg.model.Product;

import java.util.ArrayList;

public interface IProductRepository {
    ArrayList<Product> findAll();

    Product saveProduct(Product product);

    Product deleteProduct(int id);

    Product findProductByName(String name);

    Product findProductById(int id);

}
