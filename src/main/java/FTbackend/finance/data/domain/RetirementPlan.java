package FTbackend.finance.data.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RetirementPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double currentAge;
    private double retirementAge;
    private double monthlyContribution;
    private double currentSavings;
    private double annualReturn;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public double getCurrentAge() { return currentAge; }
    public void setCurrentAge(double currentAge) { this.currentAge = currentAge; }
    public double getRetirementAge() { return retirementAge; }
    public void setRetirementAge(double retirementAge) { this.retirementAge = retirementAge; }
    public double getMonthlyContribution() { return monthlyContribution; }
    public void setMonthlyContribution(double monthlyContribution) { this.monthlyContribution = monthlyContribution; }
    public double getCurrentSavings() { return currentSavings; }
    public void setCurrentSavings(double currentSavings) { this.currentSavings = currentSavings; }
    public double getAnnualReturn() { return annualReturn; }
    public void setAnnualReturn(double annualReturn) { this.annualReturn = annualReturn; }
}
