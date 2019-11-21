package dev.assessment.invoiceregister.repositories;

import dev.assessment.invoiceregister.model.LineItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineItemRepository extends CrudRepository<LineItem, Long> {
}
