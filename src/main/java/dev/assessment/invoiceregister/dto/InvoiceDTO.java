package dev.assessment.invoiceregister.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class InvoiceDTO {
    private Long id;
    private String client;
    private long vatRate;
    private String invoiceDate;
    private List<LineItemDTO> lineItemDTOS = new ArrayList<>();
    private BigDecimal subTotal;
    private BigDecimal total;
    private BigDecimal vat;
}
