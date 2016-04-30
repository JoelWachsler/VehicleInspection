package se.kth.ict.iv1350.inspectionCompany.DTO;

import se.kth.ict.iv1350.inspectionCompany.util.Amount;

/**
 * DTO for holding various inspection data.
 */
public class InspectionDTO {
    private final String inspect;
    private final boolean passOrFail;
    private final Amount cost;

    public InspectionDTO(String inspect, boolean passOrFail, Amount cost) {
        this.inspect = inspect;
        this.passOrFail = passOrFail;
        this.cost = cost;
    }

    public InspectionDTO(InspectionDTO inspection) {
        this.inspect = inspection.getInspect();
        this.passOrFail = inspection.getPassOrFail();
        this.cost = inspection.getCost();
    }

    /**
     * Returns this inspection.
     * @return This inspection.
     */
    public String getInspect() {
        return inspect;
    }

    /**
     * Returns pass or fail for this inspection.
     * @return The pass or fail of this inspection.
     */
    public boolean getPassOrFail() {
        return passOrFail;
    }

    /**
     * Returns the inspection cost for this inspection.
     * @return The inspection cost for this inspection.
     */
    public Amount getCost() {
        return cost;
    }
}
