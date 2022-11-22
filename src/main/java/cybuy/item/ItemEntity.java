package cybuy.item;

import com.fasterxml.jackson.annotation.JsonBackReference;
import cybuy.user.UserEntity;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long item_number;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private UserEntity user_entity;

    public ItemEntity() {}

    public ItemEntity(String title, String description, double price) {

        this.title = title;
        this.description = description;
        this.price = price;
    }

    public long getItemNumber() {
        return item_number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public UserEntity getUser_entity() {
        return user_entity;
    }

    public void setUser_entity(UserEntity user) {
        this.user_entity = user;
    }

    @Override
    public String toString() {
        return "Item [item_number=" + this.item_number + ", title=" + this.title + ", description=" + this.description + ", price="
                + this.price + ", user=" + this.user_entity + "]";
    }
}
