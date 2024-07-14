package au.com.telstra.simcardactivator.repository;

import au.com.telstra.simcardactivator.record.SimCardRecord;
import org.springframework.data.repository.CrudRepository;

public interface SimCardRepository extends CrudRepository<SimCardRecord, Long> {
}