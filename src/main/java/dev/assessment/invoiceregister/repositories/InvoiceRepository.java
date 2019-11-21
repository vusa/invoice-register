package dev.assessment.invoiceregister.repositories;

import dev.assessment.invoiceregister.model.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
    Optional<Invoice> findById(Long id);
}
