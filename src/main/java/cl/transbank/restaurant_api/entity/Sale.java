package cl.transbank.restaurant_api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

@Entity
public class Sale implements Serializable {

    @Id
    @Type(type="uuid-char")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    private long invoice;
    @Column(name="total_price")
    private long totalPrice;
    @Column(name="sale_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date saleDate;
    @Column(name="ci_seller")
    private String ciSeller;
    @Column(name="ci_buyer")
    private String ciBuyer;

    public Sale() {

    }

    public Sale(long invoice, Date saleDate, String ciSeller, String ciBuyer, long totalPrice) {
        this.invoice = invoice;
        this.saleDate = saleDate;
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

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
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
