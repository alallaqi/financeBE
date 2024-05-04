package FTbackend.finance.business.service;

import org.springframework.stereotype.Service;

@Service
public class LoanService {
    public double calculateMonthlyPayment(double principal, double annualRate, int years) {
        double monthlyRate = annualRate / 100 / 12;
        int totalPayments = years * 12;
        return (principal * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -totalPayments));
    }
}
