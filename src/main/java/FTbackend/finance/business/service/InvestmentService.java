package FTbackend.finance.business.service;
import FTbackend.finance.data.repository.UserRepository;
import FTbackend.finance.data.domain.Investment;
import FTbackend.finance.data.domain.User;
import FTbackend.finance.data.repository.InvestmentRepository;
import FTbackend.finance.data.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestmentService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InvestmentRepository investmentRepository;

    public List<Investment> getUserInvestments(Long userId) {
        return investmentRepository.findByUserId(userId);
    }

    @Transactional
    public Investment saveInvestment(Investment investment, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        investment.setUser(user);
        return investmentRepository.save(investment);
    }

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
