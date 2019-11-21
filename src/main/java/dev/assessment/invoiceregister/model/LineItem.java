package dev.assessment.invoiceregister.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class LineItem {
    @Id
    @GeneratedValue
    private Long id;
    private Long quantity;
    private String description;
    private Long unitPrice;
    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="invoice_id")
    private Invoice invoice;

    public BigDecimal getLineItemTotal(){
        BigDecimal lineTotal = BigDecimal.valueOf(unitPrice * quantity);
        return lineTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
