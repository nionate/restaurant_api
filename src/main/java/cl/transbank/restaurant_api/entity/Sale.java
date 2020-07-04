package cl.transbank.restaurant_api.entity;

import javax.persistence.Entity;

@Entity
public class Sale {

    private long id, invoice, totalPrice;
    private String date, ciSeller, ciBuyer;

    public Sale(long id, long invoice, String date, String ciSeller, String ciBuyer, long totalPrice) {
        this.id = id;
        this.invoice = invoice;
        this.date = date;
        this.ciSeller = ciSeller;
        this.ciBuyer = ciBuyer;
        this.totalPrice = totalPrice;
    }
}
