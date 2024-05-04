package FTbackend.finance.data.repository;

import FTbackend.finance.data.domain.RetirementPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetirementPlanRepository extends JpaRepository<RetirementPlan, Long> {
}
