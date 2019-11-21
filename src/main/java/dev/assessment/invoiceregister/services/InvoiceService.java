package dev.assessment.invoiceregister.services;

import dev.assessment.invoiceregister.model.Invoice;
import dev.assessment.invoiceregister.repositories.InvoiceRepository;
import dev.assessment.invoiceregister.repositories.LineItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class InvoiceService {
    private InvoiceRepository invoiceRepository;
    private LineItemRepository lineItemRepository;

    @Autowired
    public InvoiceService(final InvoiceRepository invoiceRepository, final LineItemRepository lineItemRepository) {
        this.invoiceRepository = invoiceRepository;
        this.lineItemRepository = lineItemRepository;
    }


    public Invoice addInvoice(Invoice invoice){
        log.debug("A POST received on /invoices");
        return invoiceRepository.save(invoice);
    }


    public List<Invoice> viewAllInvoices(){
        log.debug("A GET request has been received");
        List<Invoice> invoiceList = new ArrayList<>();
        invoiceRepository.findAll().forEach(invoice -> invoiceList.add(invoice));
        return invoiceList;
    }


    public Invoice viewInvoice(@PathVariable Long invoiceId){
        log.debug("Got  GET request for invoice {}", invoiceId);
        return invoiceRepository.findById(invoiceId).get();
    }
}
