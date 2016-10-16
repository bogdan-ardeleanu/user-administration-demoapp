package x.y.z.demo.app.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Anastasia cea micuta on 10/16/2016.
 */
@Entity
@Table(name = "ACCOUNT")
public class AccountEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "IDENTIFIER", length = 10, unique = true)
    @Basic(optional = false)
    private String name;

    @Column(name = "BALANCE")
    @Basic(optional = false)
    private Integer balance;

    @ManyToOne
    @JoinColumn(name = "ID_CUSTOMER")
    private CustomerEntity customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
}
