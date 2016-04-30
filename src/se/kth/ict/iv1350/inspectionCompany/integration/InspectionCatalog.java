package se.kth.ict.iv1350.inspectionCompany.integration;

import se.kth.ict.iv1350.inspectionCompany.DTO.InspectionDTO;
import se.kth.ict.iv1350.inspectionCompany.model.Vehicle;
import se.kth.ict.iv1350.inspectionCompany.util.Amount;

import java.util.Date;
import java.util.Random;

/**
 * External database search for vehicles.
 */
public class InspectionCatalog {
    private Random random = new Random(new Date().getTime());

    // Define a couple of inspections
    private static final InspectionDTO[] availableInspections = {
        new InspectionDTO("Lights", false, new Amount(50)),
        new InspectionDTO("Tires", false, new Amount(1)),
        new InspectionDTO("Brakes", false, new Amount(100))
    };

    /**
     * Searches database for a vehicle with the provided license number.
     * @param licenseNumber The license number of the vehicle sought after.
     * @return The vehicle found in the database
     */
    public Vehicle vehicleSearch(String licenseNumber) {
        // Create a holder for the random instructions
        InspectionDTO[] inspections = new InspectionDTO[random.nextInt(6)];
        // Take a couple of random inspections
        for (int i = 0; i < inspections.length; i++)
            inspections[i] = availableInspections[random.nextInt(availableInspections.length)];

        return new Vehicle(licenseNumber, inspections);
    }
}
