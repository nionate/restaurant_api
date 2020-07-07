package cl.transbank.restaurant_api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
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

    public Sale(long invoice, Date saleDate, String ciSeller, String ciBuyer, long totalPrice) {
        this.invoice = invoice;
        this.saleDate = saleDate;
        this.ciSeller = ciSeller;
        this.ciBuyer = ciBuyer;
        this.totalPrice = totalPrice;
    }
}
