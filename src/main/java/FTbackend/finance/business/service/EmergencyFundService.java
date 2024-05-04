package FTbackend.finance.business.service;

import FTbackend.finance.data.domain.EmergencyFund;
import FTbackend.finance.data.repository.EmergencyFundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmergencyFundService {

    @Autowired
    private EmergencyFundRepository emergencyFundRepository;

    public EmergencyFund calculateEmergencyFundGoal(EmergencyFund emergencyFund) {
        double totalMonthlyExpenses = emergencyFund.getHousing() + emergencyFund.getUtilities() +
                emergencyFund.getGroceries() + emergencyFund.getTransportation() +
                emergencyFund.getDebtPayments() + emergencyFund.getOtherEssentials();
        emergencyFund.setTotalMonthlyExpenses(totalMonthlyExpenses);
        double emergencyFundGoal = totalMonthlyExpenses * emergencyFund.getCoverageMonths();
        emergencyFund.setEmergencyFundGoal(emergencyFundGoal);
        return emergencyFundRepository.save(emergencyFund);
    }
}
