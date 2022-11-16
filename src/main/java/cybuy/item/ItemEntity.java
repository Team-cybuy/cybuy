package cybuy.item;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long item_Number;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price")
    private double price;

//    @Column(name = "user")
//    private User user;

    public ItemEntity() {}

    public ItemEntity(String title, String description, double price) {

        this.title = title;
        this.description = description;
        this.price = price;
    }

    public long getItemNumber() {
        return item_Number;
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

    @Override
    public String toString() {
        return "Item [item_Number=" + this.item_Number + ", title=" + this.title + ", description=" + this.description + ", price="
                + this.price + "]";
    }
}
