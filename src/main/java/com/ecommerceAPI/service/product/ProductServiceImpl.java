package com.ecommerceAPI.service.product;

import com.ecommerceAPI.core.exception.NotFoundException;
import com.ecommerceAPI.core.utils.Msg;
import com.ecommerceAPI.entity.Product;
import com.ecommerceAPI.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return this.productRepository.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public List<Product> getAll() {
        return this.productRepository.findAllByOrderByIdAsc();
    }

    @Override
    @Transactional
    public Product update(Product product) {
        this.getById(product.getId());
        return this.productRepository.save(product);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Product product = this.getById(id);
        this.productRepository.delete(product);
    }
}