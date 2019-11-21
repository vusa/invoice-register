package dev.assessment.invoiceregister.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue
    private Long id;
    private String client;
    private Date invoiceDate;
    private long vatRate;
    @OneToMany(mappedBy = "invoice")
    private List<LineItem> lineItems = new ArrayList<>();

    public BigDecimal getSubTotal(){
        BigDecimal total = BigDecimal.ZERO;
        total = total.setScale(2, BigDecimal.ROUND_HALF_UP);
        for(LineItem lineItem:lineItems){
            total = total.add(lineItem.getLineItemTotal());
        }
        return total;
    }

    public BigDecimal getVat(){
        BigDecimal vat = BigDecimal.valueOf(vatRate);
        vat = vat.setScale(2, BigDecimal.ROUND_HALF_UP);
        return getSubTotal().multiply(vat);
    }

    public BigDecimal getTotal(){
        return getSubTotal().add(getVat());
    }
}
