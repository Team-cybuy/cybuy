package cybuy.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import cybuy.item.ItemEntity;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long user_id;

    @Column(name = "user_number")
    private UUID user_number;

    @Column(name = "mail")
    private String mail;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "address")
    private String address;

    @Column(name = "postal_code")
    private int postal_code;

    @Column(name = "items")
    @OneToMany(mappedBy = "user_entity", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<ItemEntity> items;

    public UserEntity() {}

    public UserEntity(String mail, String first_name, String last_name, String address, int postal_code) {
        this.mail = mail;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.postal_code = postal_code;
        this.user_number = UUID.randomUUID();
    }

    public long getUser_id() {
        return user_id;
    }

    public UUID getUser_number() {
        return user_number;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(int postal_code) {
        this.postal_code = postal_code;
    }

    public Set<ItemEntity> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "User [user_id=" + this.user_id + ", mail=" + this.mail + ", first_name=" + this.first_name + ", last_name="
                + this.last_name + ", address=" + this.address + ", postal_code=" + this.postal_code + ", items=" + this.items + "]";
    }
}
