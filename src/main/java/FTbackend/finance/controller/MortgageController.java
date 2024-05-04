package FTbackend.finance.controller;

import FTbackend.finance.business.service.MortgageService;
import FTbackend.finance.data.domain.Mortgage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mortgage")
public class MortgageController {

    private static final Logger log = LoggerFactory.getLogger(MortgageController.class);

    @Autowired
    private MortgageService mortgageService;

    @PostMapping("/calculate")
    public ResponseEntity<?> calculateMortgage(@RequestBody Mortgage mortgage) {
        log.info("Received mortgage calculation request with principal={}, interestRate={}, term={}",
                mortgage.getPrincipal(), mortgage.getInterestRate(), mortgage.getTerm());

        try {
            double result = mortgageService.calculateMonthlyPayment(
                    mortgage.getPrincipal(), mortgage.getInterestRate(), mortgage.getTerm());

            if (Double.isNaN(result)) {
                log.error("Calculation resulted in NaN. Input values may be incorrect or lead to undefined operations.");
                return ResponseEntity.badRequest().body("Calculation resulted in NaN. Check input values.");
            }

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("Error calculating mortgage payment", e);
            return ResponseEntity.internalServerError().body("An error occurred during the calculation.");
        }
    }
}
