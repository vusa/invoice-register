package dev.assessment.invoiceregister.rest;

import dev.assessment.invoiceregister.dto.InvoiceDTO;
import dev.assessment.invoiceregister.services.InvoiceService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RequestMapping("/invoices")
@RestController
@Api(tags = "Invoices")
@Slf4j
public class InvoiceController {
    private InvoiceService invoiceService;

    @Autowired
    public InvoiceController(final InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping(produces = "application/json")
    public InvoiceDTO addInvoice(InvoiceDTO invoice) throws ParseException {
        log.debug("A POST received on /invoices");
        return invoiceService.addInvoice(invoice);
    }

    @GetMapping(produces = "application/json")
    public List<InvoiceDTO> viewAllInvoices(){
        log.debug("A GET request has been received");
        return invoiceService.viewAllInvoices();
    }

    @GetMapping( path = "/{invoiceId}")
    public InvoiceDTO viewInvoice(@PathVariable Long invoiceId){
        log.debug("Got  GET request for invoice {}", invoiceId);
        return invoiceService.viewInvoice(invoiceId);
    }
}
