package dev.assessment.invoiceregister.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue
    private Long id;
    private Long quantity;
    private String description;
    private Long unitPrice;
    @OneToMany(mappedBy = "invoice")
    private List<LineItem> lineItems;
    @Value( "${app.vat}")
    private double vatRate;

    public BigDecimal getSubTotal(){
        final BigDecimal total = BigDecimal.ZERO;
        total.setScale(2, BigDecimal.ROUND_HALF_UP);
        lineItems.forEach(lineItem -> total.add(lineItem.getLineItemTotal()));
        return total;
    }

    public BigDecimal getVat(){
        BigDecimal vat = new BigDecimal(vatRate);
        return getSubTotal().multiply(vat);
    }

    public BigDecimal getTotal(){
        return getSubTotal().add(getVat());
    }
}
