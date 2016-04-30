package se.kth.ict.iv1350.inspectionCompany.DTO;

import se.kth.ict.iv1350.inspectionCompany.util.Amount;

/**
 * Created by Joel Wachsler on 2016-04-29.
 */
public class InspectionDTO {
    private String inspect;
    private boolean passOrFail;
    private Amount cost;

    public InspectionDTO(String inspect, boolean passOrFail, Amount cost) {
        this.inspect = inspect;
        this.passOrFail = passOrFail;
        this.cost = cost;
    }

    public String getInspect() {
        return inspect;
    }

    public boolean getPassOrFail() {
        return passOrFail;
    }

    public Amount getCost() {
        return cost;
    }
}
