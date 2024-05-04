package FTbackend.finance.data.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double principal;
    private double interestRate;
    private int term;

    // Default constructor
    public Loan() {
    }

    // Parameterized constructor
    public Loan(double principal, double interestRate, int term) {
        this.principal = principal;
        this.interestRate = interestRate;
        this.term = term;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public double getPrincipal() {
        return principal;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public int getTerm() {
        return term;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void setTerm(int term) {
        this.term = term;
    }
}
