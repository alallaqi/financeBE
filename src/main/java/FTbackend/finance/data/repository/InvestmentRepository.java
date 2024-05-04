package FTbackend.finance.data.repository;

import FTbackend.finance.data.domain.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
}
