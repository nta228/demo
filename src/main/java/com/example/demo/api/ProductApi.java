package com.example.demo.api;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/products")
public class ProductApi {
    /*private static List<Product> products = new ArrayList<>();

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> findAll(){
        return products;
    }
    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public Product findById(@PathVariable int id){
        int foundIndex = -1;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id){
                foundIndex = i;
            }
        }
        if (foundIndex == -1)
        {
            return null;
        }
        return products.get(foundIndex);
    }
    @RequestMapping(method = RequestMethod.POST)
    public Product save(@RequestBody Product product){
        products.add(product);
        return product;
    }
    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public Product update(@PathVariable int id, @RequestBody Product updatedProduct){
        int foundIndex = -1;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id){
                foundIndex = i;
            }
        }
        if (foundIndex == -1){
            return null;
        }
        products.get(foundIndex).setName(updatedProduct.getName());
        products.get(foundIndex).setDescription(updatedProduct.getDescription());
        return products.get(foundIndex);
    }
    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public Product delete(@PathVariable int id){
        int foundIndex = -1;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id){
                foundIndex = i;
            }
        }
        if (foundIndex == -1){
            return null;
        }
        products.remove(foundIndex);
        return null;
    }*/
    @Autowired
    ProductService productService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return ResponseEntity.ok(productService.save(product));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(product.get());
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<Product> update(@PathVariable int id, @RequestBody Product product) {
        Optional<Product> productId = productService.findById(id);
        if (!productId.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Product exitsProduct = productId.get();
        exitsProduct.setName(product.getName());
        exitsProduct.setThumbnail(product.getThumbnail());
        exitsProduct.setDescription(product.getDescription());
        exitsProduct.setSlug(product.getSlug());
        exitsProduct.setStatus(product.getStatus());
        return ResponseEntity.ok(productService.save(exitsProduct));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
