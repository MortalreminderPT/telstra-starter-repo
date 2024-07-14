package au.com.telstra.simcardactivator.repository;

import au.com.telstra.simcardactivator.model.SimCard;
import org.springframework.data.repository.CrudRepository;

public interface SimCardRepository extends CrudRepository<SimCard, Long> {
    SimCard findById(long id);
    SimCard save(SimCard simCard);
}