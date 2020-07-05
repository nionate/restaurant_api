package cl.transbank.restaurant_api.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Sale {

    @Id
    private long id;
    private long invoice, totalPrice;
    private String date, ciSeller, ciBuyer;

    public Sale(long id, long invoice, String date, String ciSeller, String ciBuyer, long totalPrice) {
        this.id = id;
        this.invoice = invoice;
        this.date = date;
        this.ciSeller = ciSeller;
        this.ciBuyer = ciBuyer;
        this.totalPrice = totalPrice;
    }

    public Sale(long invoice, String date, String ciSeller, String ciBuyer, long totalPrice) {
        this.invoice = invoice;
        this.date = date;
        this.ciSeller = ciSeller;
        this.ciBuyer = ciBuyer;
        this.totalPrice = totalPrice;
    }
}
