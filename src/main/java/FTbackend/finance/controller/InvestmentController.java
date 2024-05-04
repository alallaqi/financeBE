package FTbackend.finance.controller;

import FTbackend.finance.business.service.InvestmentService;
import FTbackend.finance.data.domain.Investment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/investment")
public class InvestmentController {

    @Autowired
    private InvestmentService investmentService;

    @PostMapping("/calculate")
    public ResponseEntity<Double> calculateInvestment(@RequestBody Investment investment) {
        double result = investmentService.calculateFutureValue(
                investment.getInitialAmount(), investment.getMonthlyContribution(),
                investment.getAnnualReturn(), investment.getYears());
        return ResponseEntity.ok(result);
    }
}
