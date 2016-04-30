package se.kth.ict.iv1350.inspectionCompany.integration;

import se.kth.ict.iv1350.inspectionCompany.DTO.InspectionDTO;
import se.kth.ict.iv1350.inspectionCompany.model.Vehicle;
import se.kth.ict.iv1350.inspectionCompany.util.Amount;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * External database search for vehicles.
 */
public class InspectionCatalog {
    private final Random random = new Random(new Date().getTime());
    private Map<String, Vehicle> inspectionDatabase;


    // Define a couple of inspections
    private static final InspectionDTO[] availableInspections = {
        new InspectionDTO("Lights", false, new Amount(50)),
        new InspectionDTO("Tires", false, new Amount(1)),
        new InspectionDTO("Brakes", false, new Amount(100)),
        new InspectionDTO("Windscreen wipers", false, new Amount(5)),
        new InspectionDTO("Washer fluid", false, new Amount(3)),
        new InspectionDTO("Steering wheel", false, new Amount(10)),
        new InspectionDTO("Gearbox", false, new Amount(20)),
    };

    public InspectionCatalog() {
        this.inspectionDatabase = new HashMap<>();
    }

    /**
     * Searches database for a vehicle with the provided license number.
     * @param licenseNumber The license number of the vehicle sought after.
     * @return The vehicle found in the database
     */
    public Vehicle vehicleSearch(String licenseNumber) {
        // Create a holder for the random instructions
        // Can be between 2 and 6 inspections
        InspectionDTO[] inspections = new InspectionDTO[random.nextInt(4) + 2];
        // Take a couple of random inspections
        for (int i = 0; i < inspections.length; i++) {
            // Check if this item is already in the inspections
            InspectionDTO inspectionCandidate = availableInspections[random.nextInt(availableInspections.length)];
            if (alreadyInArray(inspections, inspectionCandidate, i)) {
                i--;
                continue;
            }

            // This inspection has not been added, so lets add it!
            inspections[i] = inspectionCandidate;
        }

        return new Vehicle(licenseNumber, inspections);
    }

    private boolean alreadyInArray(InspectionDTO[] inspections, InspectionDTO potentialInspection, int length) {
        // TODO: Check if this can potentially crash the program
        for (int j = 0; j < length; j++)
            if (inspections[j].getInspect().equals(potentialInspection.getInspect()))
                // This item already exists, try again!
                return true;
        return false;
    }

    /**
     * Save the inspection result.
     * @param vehicleInspectionResult The inspection result to save.
     */
    public void storeInspectionResult(Vehicle vehicleInspectionResult) {
        this.inspectionDatabase.put(vehicleInspectionResult.getLicenseNumber(), new Vehicle(vehicleInspectionResult));
    }
}
