package cl.transbank.restaurant_api.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Sale {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",
            strategy = "uuid")
    private UUID id;
    private long invoice, totalPrice;
    private String date, ciSeller, ciBuyer;

    public Sale() {

    }

    public Sale(long invoice, String date, String ciSeller, String ciBuyer, long totalPrice) {
        this.invoice = invoice;
        this.date = date;
        this.ciSeller = ciSeller;
        this.ciBuyer = ciBuyer;
        this.totalPrice = totalPrice;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public long getInvoice() {
        return invoice;
    }

    public void setInvoice(long invoice) {
        this.invoice = invoice;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCiSeller() {
        return ciSeller;
    }

    public void setCiSeller(String ciSeller) {
        this.ciSeller = ciSeller;
    }

    public String getCiBuyer() {
        return ciBuyer;
    }

    public void setCiBuyer(String ciBuyer) {
        this.ciBuyer = ciBuyer;
    }
}
