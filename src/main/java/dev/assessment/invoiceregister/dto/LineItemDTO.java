package dev.assessment.invoiceregister.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class LineItemDTO {
    private Long id;
    private Long quantity;
    private String description;
    private Long unitPrice;
    private BigDecimal lineItemTotal;
}
