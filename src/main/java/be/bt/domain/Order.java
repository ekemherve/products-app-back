package be.bt.domain;

import be.bt.domain.security.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "Orders")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    private BigDecimal totaOrderAmount;

    @JsonIgnore
    @ManyToOne
    private User user;

    @ManyToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    public Order(){}

    public Order(LocalDateTime date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BigDecimal getTotaOrderAmount() {
        return totaOrderAmount;
    }

    public void setTotaOrderAmount(BigDecimal totaOrderAmount) {
        this.totaOrderAmount = totaOrderAmount;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
