package FTbackend.finance.controller;

import FTbackend.finance.business.service.LoanService;
import FTbackend.finance.data.domain.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loan")
public class LoanController {
    @Autowired
    private LoanService loanService;

    @PostMapping("/calculate")
    public ResponseEntity<Double> calculateLoan(@RequestBody Loan loan) {
        double result = loanService.calculateMonthlyPayment(loan.getPrincipal(), loan.getInterestRate(), loan.getTerm());
        return ResponseEntity.ok(result);
    }
}
