package bg.softuni.ProductsShop.domain.entities;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "products")
//id, name (at least 3 characters), price, buyerId (optional) and sellerId as IDs of users
public class Product extends BaseEntity {

    @Column
    private String name;

    @Column
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.EAGER)
//    @Fetch(FetchMode.JOIN)
    private User buyer;

    @ManyToOne(fetch = FetchType.EAGER)
    private User seller;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Category> categories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
