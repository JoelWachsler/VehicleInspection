package se.kth.ict.iv1350.inspectionCompany.controller;

import se.kth.ict.iv1350.inspectionCompany.integration.InspectionCatalog;
import se.kth.ict.iv1350.inspectionCompany.model.Vehicle;
import se.kth.ict.iv1350.inspectionCompany.util.Amount;
import se.kth.iv1350.garage.Garage;
import se.kth.iv1350.payauth.CreditCard;
import se.kth.iv1350.payauth.PaymentAuthorization;

/**
 * Handles communication with the different layers.
 */
public class Controller {
    private Garage garage;
    private InspectionCatalog inspectionCatalog;
    private PaymentAuthorization paymentAuthorization;

    public Controller(Garage garage, InspectionCatalog inspectionCatalog, PaymentAuthorization paymentAuthorization) {
        this.garage = garage;
        this.inspectionCatalog = inspectionCatalog;
        this.paymentAuthorization = paymentAuthorization;
    }

    /**
     * Calls the external system <code>Garage</code> which opens the garage door and updates the queue.
     */
    public void startNewInspection() {
        garage.nextCustomer();
    }

    /**
     * Calls the external system <code>Garage</code> and tells is to close the door.
     */
    public void closeGarageDoor() {
        garage.closeDoor();
    }

    /**
     * Calls an external database and checks if a vehicle with the provided license number exists.
     * @param licenseNumber The license number of the vehicle sought after.
     */
    public Vehicle searchVehicleInspection(String licenseNumber) {
        return inspectionCatalog.vehicleSearch(licenseNumber);
    }

    /**
     * Calculates the total cost for the current inspection.
     * @param vehicle The vehicle to check the cost for.
     * @return The total cost for the inspection.
     */
    public Amount calculateTotalCost(Vehicle vehicle) {
        return vehicle.calculateTotalCost();
    }

    /**
     * Call the external payment system to perform the payment.
     * @param card The customers credit card information.
     * @param cost The cost for the current inspection.
     * @return True or false which is determined by if the payment was successful or not.
     */
    public boolean paymentRequest(CreditCard card, Amount cost) {
        // Convert amount to an integer value
        return paymentAuthorization.authorizePayment(card, cost.getAmount());
    }
}
