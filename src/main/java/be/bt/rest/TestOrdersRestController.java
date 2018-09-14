package be.bt.rest;


import be.bt.domain.Order;
import be.bt.domain.Product;
import be.bt.domain.security.User;
import be.bt.repository.IOrdersRepository;
import be.bt.repository.IProductsRepository;
import be.bt.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/testOrder")
@CrossOrigin(origins = "*")
public class TestOrdersRestController {

    @Autowired
    private IOrdersRepository orderRepo;

    @Autowired
    private IProductsRepository productRepo;

    @Autowired
    private UserRepository userRepo;

    // Createur pour un utilisateur courant
    @GetMapping("/create")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void create(Principal principal) {

        //Récupérer l'utilisateur courant

        //UserDetails userDetails = (UserDetails) principal;

        String name = principal.getName();

        //User currentUser = (User)userDetails;

        User currentUser = userRepo.findByUsername(name);


        System.out.println(">>>>>>>>>> Utilisateur Connecté : " + currentUser);

        // Creer la commande et l'ajouter au user

        Order order = new Order();
        order.setDate(LocalDateTime.now());
        order.setTotaOrderAmount(new BigDecimal(0));;
        order.setUser(currentUser);

        currentUser.getOrders().add(order);

        // Creer les produits et les ajouter à la commander

        Product lenovo = new Product();
        lenovo.setName("Lenovo");
        lenovo.setUnitPrice(new BigDecimal(999));
        //Il faut s'assurer que lenove.getOrders() n'est pas null
        lenovo.getOrders().add(order);

        Product playstation = new Product();
        playstation.setName("Playstation");
        playstation.setUnitPrice(new BigDecimal(499));
        playstation.getOrders().add(order);


        //Il faut s'assurer que order.getOrders() n'est pas null
        order.getProducts().add(lenovo);
        order.getProducts().add(playstation);

        //Persister l'utilisateur/commandes/produits

        userRepo.save(currentUser);
        orderRepo.save(order);   // CascadeType.All dans order persistera les products
    }

   /* private User getCurrentUser() {




    }*/
}
