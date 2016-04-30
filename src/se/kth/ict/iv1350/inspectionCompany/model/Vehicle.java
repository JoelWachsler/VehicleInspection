package se.kth.ict.iv1350.inspectionCompany.model;

import se.kth.ict.iv1350.inspectionCompany.DTO.InspectionDTO;
import se.kth.ict.iv1350.inspectionCompany.util.Amount;

/**
 * Keeps track of the inspections to be carried out and weather the ones carried out passed or failed.
 */
public class Vehicle {
    private String licenseNumber;
    private InspectionDTO[] inspections;
    private InspectionDTO[] inspectionsCarriedOut;

    public Vehicle(String licenseNumber, InspectionDTO[] inspections) {
        this.licenseNumber = licenseNumber;
        this.inspections = inspections;
    }

    /**
     * Calculate cost for the inspection.
     * @return The cost for the inspection.
     */
    public Amount calculateTotalCost() {
        Amount totalCost = new Amount(0);

        for(InspectionDTO inspection : inspections)
            totalCost.add(inspection.getCost());

        return totalCost;
    }
}
