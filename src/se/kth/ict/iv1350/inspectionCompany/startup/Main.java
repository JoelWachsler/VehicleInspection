package se.kth.ict.iv1350.inspectionCompany.startup;

import se.kth.ict.iv1350.inspectionCompany.controller.Controller;
import se.kth.ict.iv1350.inspectionCompany.integration.InspectionCatalog;
import se.kth.ict.iv1350.inspectionCompany.view.View;
import se.kth.iv1350.garage.Garage;
import se.kth.iv1350.payauth.PaymentAuthorization;

/**
 * Initializes everything.
 */
public class Main {

    public static void main(String[] args) {
        Garage garage = new Garage();
        InspectionCatalog inspectionCatalog = new InspectionCatalog();
        PaymentAuthorization paymentAuthorization = new PaymentAuthorization();
        Controller controller = new Controller(garage, inspectionCatalog, paymentAuthorization);
        View view = new View(controller);

        // Start the menu
        while (view.interactionFlow());

        System.exit(0);
    }
}
