package FTbackend.finance.business.service;

import org.springframework.stereotype.Service;

@Service
public class InvestmentService {

    public double calculateFutureValue(double initialAmount, double monthlyContribution, double annualReturn, int years) {
        double monthlyRate = annualReturn / 100 / 12;
        int totalPayments = years * 12;
        double futureValue = initialAmount * Math.pow(1 + monthlyRate, totalPayments);
        for (int i = 1; i <= totalPayments; i++) {
            futureValue += monthlyContribution * Math.pow(1 + monthlyRate, totalPayments - i);
        }
        return futureValue;
    }
}
