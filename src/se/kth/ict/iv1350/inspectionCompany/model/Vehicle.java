package se.kth.ict.iv1350.inspectionCompany.model;

import se.kth.ict.iv1350.inspectionCompany.DTO.InspectionDTO;
import se.kth.ict.iv1350.inspectionCompany.util.Amount;

/**
 * Keeps track of the inspections to be carried out and weather the ones carried out passed or failed.
 */
public class Vehicle {
    private final String licenseNumber;
    private final InspectionDTO[] inspections;
    private InspectionDTO[] inspectionsCarriedOut;
    private int inspectionsComplete;

    public Vehicle(String licenseNumber, InspectionDTO[] inspections) {
        this.licenseNumber = licenseNumber;
        this.inspections = inspections;
        this.inspectionsComplete = 0;
    }

    public Vehicle(Vehicle vehicle) {
        this.licenseNumber = vehicle.getLicenseNumber();
        this.inspections = vehicle.inspections;
        // TODO: Make this safer (we are giving out our results)
        this.inspectionsCarriedOut = vehicle.inspectionsCarriedOut;
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

    /**
     * Assuming the inspection always contains more than one inspection
     * @return The first inspection to be done.
     */
    public InspectionDTO getFirstInspection() {
        // Create the holder for inspections carried out
        this.inspectionsCarriedOut = new InspectionDTO[inspections.length];

        // May not be necessary
        if (this.inspections.length > 0)
            return this.inspections[0];
        else
            return null;
    }

    /**
     * Update the inspection result and store it in this object.
     * @param inspectionCarriedOut The updated inspection result
     * @return The next inspection
     */
    public InspectionDTO addToInspectionsCarriedOut(InspectionDTO inspectionCarriedOut) {
        // Create a new object so we can be certain that nobody has direct access to it
        this.inspectionsCarriedOut[inspectionsComplete] = new InspectionDTO(inspectionCarriedOut);

        try {
            return this.inspections[++inspectionsComplete];
        } catch (IndexOutOfBoundsException e) {
            // If we get here this was the last inspection
            return null;
        }
    }

    /**
     * Gives out the inspection result.
     * @return The inspection result.
     */
    public InspectionDTO[] getInspectionResult() {
        return this.inspectionsCarriedOut;
    }

    /**
     * Gives out the license number of this vehicle.
     * @return The license number.
     */
    public String getLicenseNumber() {
        return this.licenseNumber;
    }
}
