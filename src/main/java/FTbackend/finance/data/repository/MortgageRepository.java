package FTbackend.finance.data.repository;

import FTbackend.finance.data.domain.Mortgage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MortgageRepository extends JpaRepository<Mortgage, Long> {
}
