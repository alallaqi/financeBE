package FTbackend.finance.business.service;

import org.springframework.stereotype.Service;

@Service
public class RetirementService {

    public double calculateRetirementSavings(double currentAge, double retirementAge, double monthlyContribution, double currentSavings, double annualReturn) {
        int months = (int) ((retirementAge - currentAge) * 12);
        double monthlyReturnRate = annualReturn / 12 / 100;
        double futureValue = currentSavings * Math.pow(1 + monthlyReturnRate, months);
        for (int i = 0; i < months; i++) {
            futureValue += monthlyContribution * Math.pow(1 + monthlyReturnRate, i);
        }
        return futureValue;
    }
}
