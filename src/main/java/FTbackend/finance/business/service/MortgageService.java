package FTbackend.finance.business.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MortgageService {

    private static final Logger log = LoggerFactory.getLogger(MortgageService.class);

    public double calculateMonthlyPayment(double principal, double interestRate, int termYears) {
        log.info("Calculating monthly payment with principal: {}, interest rate: {}, term years: {}", principal, interestRate, termYears);

        if (interestRate == 0) {
            double payment = principal / (termYears * 12);
            log.info("Interest rate is 0, monthly payment is: {}", payment);
            return payment;
        }

        double monthlyRate = interestRate / 100 / 12;
        int totalPayments = termYears * 12;
        if (monthlyRate == 0) {
            log.error("Monthly rate calculation resulted in 0, which should not happen with non-zero interest rate");
        }

        double monthlyPayment = (principal * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -totalPayments));
        log.info("Calculated monthly payment: {}", monthlyPayment);

        return monthlyPayment;
    }
}
