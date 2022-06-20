package com.example.demo.repository;

import com.example.demo.entity.Product;
import com.example.demo.util.StringUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringBootApplication.class})
class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    public void testSaveProduct() {
        Product product = Product.builder()
                .name("Tuan Anh")
                .description("nice")
                .slug(StringUtil.toSlug("tuan-anh"))
                .thumbnail("tanh.png")
                .price(1234)
                .status(1)
                .build();
        productRepository.save(product);
    }

    @Test
    public void testGetList() {
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            System.out.println(product.toString());
        }
    }

    @Test
    public void testGetById() {
        Optional<Product> optionalProduct = productRepository.findById(1);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            System.out.println(product.getName().equals("Tuan Anh"));
        }
    }

    @Test
    public void testDelete() {
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            System.out.println(product.toString());
        }
    }
}