package FTbackend.finance.controller;

import FTbackend.finance.business.service.EmergencyFundService;
import FTbackend.finance.data.domain.EmergencyFund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/emergencyfund")
public class EmergencyFundController {

    @Autowired
    private EmergencyFundService emergencyFundService;

    @PostMapping("/calculate")
    public ResponseEntity<EmergencyFund> calculateEmergencyFund(@RequestBody EmergencyFund emergencyFund) {
        EmergencyFund result = emergencyFundService.calculateEmergencyFundGoal(emergencyFund);
        return ResponseEntity.ok(result);
    }
}
