package be.bt.rest;


import be.bt.domain.Order;
import be.bt.domain.security.User;
import be.bt.repository.IOrdersRepository;
import be.bt.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrdersRestController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private IOrdersRepository orderRepo;

    @GetMapping()
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Order> getOrdersForCurrentUser(Principal principal){

        String name = principal.getName();
        User user = userRepo.findByUsername(name);

        System.out.println(">>>>>>>>>>>> Utilisateur récupéré : " + user.getUsername());

        return user.getOrders();
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping
    public  void addOrder(@RequestBody Order order, Principal principal) {

        String name = principal.getName();
        User user = userRepo.findByUsername(name);

        System.out.println(">>>>>>>>>>>> Utilisateur récupéré : " + user.getUsername());

        order.setUser(user);

        List<Order> ordersCurrent = user.getOrders();
        ordersCurrent.add(order);
        user.setOrders(ordersCurrent);

        userRepo.save(user);
        orderRepo.save(order);

    }
}
