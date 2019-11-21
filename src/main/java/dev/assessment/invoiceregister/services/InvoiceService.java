package dev.assessment.invoiceregister.services;

import dev.assessment.invoiceregister.dto.InvoiceDTO;
import dev.assessment.invoiceregister.dto.LineItemDTO;
import dev.assessment.invoiceregister.model.Invoice;
import dev.assessment.invoiceregister.model.LineItem;
import dev.assessment.invoiceregister.repositories.InvoiceRepository;
import dev.assessment.invoiceregister.repositories.LineItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class InvoiceService {
    private InvoiceRepository invoiceRepository;
    private LineItemRepository lineItemRepository;
    SimpleDateFormat dateFormat = new SimpleDateFormat("YYY-mm-dd");

    @Autowired
    public InvoiceService(final InvoiceRepository invoiceRepository, final LineItemRepository lineItemRepository) {
        this.invoiceRepository = invoiceRepository;
        this.lineItemRepository = lineItemRepository;
    }

    public InvoiceDTO addInvoice(InvoiceDTO dto) throws ParseException {
        log.debug("Saving new invoice");
        Invoice invoice = new Invoice();
        invoice.setVatRate(dto.getVatRate());
        invoice.setClient(dto.getClient());
        invoice.setInvoiceDate(dateFormat.parse(dto.getInvoiceDate()));
        invoice = invoiceRepository.save(invoice);
        for (LineItemDTO liDto:dto.getLineItemDTOS()){
            LineItemDTO li = addLineItem(liDto, invoice);
            liDto.setId(li.getId());
        }
        dto.setId(invoice.getId());
        return dto;
    }

    private LineItemDTO addLineItem(LineItemDTO dto, Invoice invoice){
        LineItem lineItem = new LineItem();
        lineItem.setInvoice(invoice);
        lineItem.setDescription(dto.getDescription());
        lineItem.setQuantity(dto.getQuantity());
        lineItem.setUnitPrice(dto.getUnitPrice());
        LineItem li = lineItemRepository.save(lineItem);
        dto.setId(li.getId());
        return dto;
    }

    public List<InvoiceDTO> viewAllInvoices(){
        log.debug("A GET request has been received");
        List<InvoiceDTO> invoiceList = new ArrayList<>();
        invoiceRepository.findAll().forEach(invoice -> invoiceList.add(mapDtoFromInvoice(invoice)));
        return invoiceList;
    }

    public InvoiceDTO viewInvoice(@PathVariable Long invoiceId){
        log.debug("Got  GET request for invoice {}", invoiceId);
        Optional<Invoice> byId = invoiceRepository.findById(invoiceId);
        if(byId.isPresent()){
            return mapDtoFromInvoice(byId.get());
        }else {
            return null;
        }
    }

    private InvoiceDTO mapDtoFromInvoice(Invoice invoice){
        InvoiceDTO dto = new InvoiceDTO();
        dto.setId(invoice.getId());
        dto.setClient(invoice.getClient());
        dto.setInvoiceDate(dateFormat.format(invoice.getInvoiceDate()));
        dto.setVatRate(invoice.getVatRate());
        dto.setSubTotal(invoice.getSubTotal());
        dto.setVat(invoice.getVat());
        dto.setTotal(invoice.getTotal());
        for(LineItem li:invoice.getLineItems()){
            dto.getLineItemDTOS().add(mapDtoFromLineItem(li));
        }
        return dto;
    }

    private LineItemDTO mapDtoFromLineItem(LineItem li){
        LineItemDTO dto = new LineItemDTO();
        dto.setId(li.getId());
        dto.setDescription(li.getDescription());
        dto.setQuantity(li.getQuantity());
        dto.setUnitPrice(li.getUnitPrice());
        dto.setLineItemTotal(li.getLineItemTotal());
        return dto;
    }
}
