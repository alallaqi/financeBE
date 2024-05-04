package FTbackend.finance.data.repository;

import FTbackend.finance.data.domain.EmergencyFund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergencyFundRepository extends JpaRepository<EmergencyFund, Long> {
}
