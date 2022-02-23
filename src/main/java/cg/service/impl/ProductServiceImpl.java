package cg.service.impl;

import cg.model.Product;
import cg.repository.IProductRepository;
import cg.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public ArrayList<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.saveProduct(product);
    }

    @Override
    public Product deleteProduct(int id) {
        return productRepository.deleteProduct(id);
    }

    @Override
    public Product getProduct(int id) {
        return productRepository.findProductById(id);
    }
}
