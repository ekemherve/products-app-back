package be.bt.rest;

import be.bt.domain.Product;
import be.bt.repository.IProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/products")
public class ProductRestController {

    @Autowired
    private IProductsRepository repo;

    @GetMapping
    public List<Product> findAllProducts(){
        return repo.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){

        Product  result  =  repo.save(product);
        return result == null ? new ResponseEntity<>(HttpStatus.CONFLICT)
                              : new ResponseEntity<>(result, HttpStatus.CREATED);
    }

}
