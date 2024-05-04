package FTbackend.finance.controller;

import FTbackend.finance.business.service.RetirementService;
import FTbackend.finance.data.domain.RetirementPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/retirement")
public class RetirementController {

    @Autowired
    private RetirementService retirementService;

    @PostMapping("/calculate")
    public ResponseEntity<Double> calculateRetirement(@RequestBody RetirementPlan plan) {
        double result = retirementService.calculateRetirementSavings(plan.getCurrentAge(), plan.getRetirementAge(), plan.getMonthlyContribution(), plan.getCurrentSavings(), plan.getAnnualReturn());
        return ResponseEntity.ok(result);
    }
}
