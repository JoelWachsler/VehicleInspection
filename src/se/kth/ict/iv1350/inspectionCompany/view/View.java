package se.kth.ict.iv1350.inspectionCompany.view;

import se.kth.ict.iv1350.inspectionCompany.controller.Controller;
import se.kth.ict.iv1350.inspectionCompany.model.Vehicle;
import se.kth.iv1350.payauth.CreditCard;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Handles direct contact with the user.
 */
public class View {
    private Controller controller;
    private Scanner scanner;
    private Vehicle vehicle;
    // Credit Card variables
    private int pin;
    private String cardNumber;
    private String holder;
    private Date expiryDate;

    // The different states the program can be in
    private enum State {
        INIT,
        DOOR_OPEN,
        SEARCH,
        COST,
        PAYMENT_INIT,
        PAYMENT_NUMBER,
        PAYMENT_HOLDER,
        PAYMENT_EXPIRY_DATE,
        PAYMENT_CVC,
        PAYMENT_SUCCESS,
        PAYMENT_FAILED,
        INSPECTION_INIT,
        INSPECTION,
        EXIT
    }

    private State currentState;

    public View(Controller controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);

        this.currentState = State.INIT;
    }

    private String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * Displays the different menu items the user can choose and request input.
     */
    public boolean interactionFlow() {
        switch (currentState) {
            case INIT:
                System.out.println("Welcome!");
                System.out.println("Press:\n[1] - Start a new inspection");
                break;

            case DOOR_OPEN:
                System.out.println("The door is currently open!");
                System.out.println("Press:\n[1] - Close the door");
                break;

            case SEARCH:
                System.out.println("Enter a license number: ");
                break;

            case COST:
                System.out.printf("\nThe cost for the inspection is: $%d\n", controller.calculateTotalCost(this.vehicle).getAmount());
                System.out.println("[1] - Pay with credit card");
                break;

            case PAYMENT_INIT:
                System.out.println("Payment...");
                System.out.print("\nEnter pin: ");
                break;

            case PAYMENT_NUMBER:
                System.out.print("\nEnter card number: ");
                break;

            case PAYMENT_HOLDER:
                System.out.print("\nEnter card holder:");
                break;

            case PAYMENT_EXPIRY_DATE:
                System.out.print("\nEnter card expiry date (mm/yy): ");
                break;

            case PAYMENT_CVC:
                System.out.print("\nEnter CVC: ");
                break;

            case PAYMENT_SUCCESS:
                System.out.println("Payment successful!");
                break;

            case PAYMENT_FAILED:
                System.out.println("Payment failed!");
                break;

            case INSPECTION_INIT:
                System.out.println("Inspection has started!");
                break;

            case EXIT:
                // Exit program
                System.out.println("Goodbye!");
                return false;
        }

        // Exit is always an option
        System.out.println("[0] - Exit");

        this.applyInstructionAndDetermineNextState(getUserInput());

        return true;
    }

    /**
     * Apply the command passed to this function determined by the current state.
     * And determine the next state depending on the current one.
     *
     * @param userInput Input from the user.
     */
    private void applyInstructionAndDetermineNextState(String userInput) {
        // The user input parsed as an integer
        int integerInstruction;
        try {
            integerInstruction = Integer.parseInt(userInput);
        } catch (Exception e) {
            // Not a integer!
            // Set default value
            integerInstruction = -1;
        }

        // The exit check is always done
        if (integerInstruction == 0)
            currentState = State.EXIT;

        // Determine the next state and apply what the instruction
        switch (currentState) {
            case INIT:
                if (integerInstruction == 1) {
                    controller.startNewInspection();
                    currentState = State.DOOR_OPEN;
                }
                break;

            case DOOR_OPEN:
                if (integerInstruction == 1) {
                    controller.closeGarageDoor();
                    currentState = State.SEARCH;
                }
                break;

            case SEARCH:
                this.vehicle = controller.searchVehicleInspection(userInput);
                currentState = State.COST;
                break;

            case COST:
                if (integerInstruction == 1)
                    this.currentState = State.PAYMENT_INIT;
                break;

            case PAYMENT_INIT:
                if (integerInstruction != -1) {
                    this.pin = integerInstruction;
                    this.currentState = State.PAYMENT_NUMBER;
                }
                break;

            case PAYMENT_NUMBER:
                this.cardNumber = userInput;
                this.currentState = State.PAYMENT_HOLDER;
                break;

            case PAYMENT_HOLDER:
                this.holder = userInput;
                this.currentState = State.PAYMENT_EXPIRY_DATE;
                break;

            case PAYMENT_EXPIRY_DATE:
                // Parse input mm/yy
                String[] input;
                int[] inputInteger = new int[2];
                try {
                    input = userInput.split("/");
                    // Month
                    inputInteger[0] = Integer.parseInt(input[0]);
                    // Year
                    inputInteger[1] = Integer.parseInt(input[1]);
                } catch (Exception e) {
                    // Input not valid, abort!
                    return;
                }

                // Is there a better way to create a "Date" object?
                expiryDate = new GregorianCalendar(inputInteger[1], inputInteger[0], 0).getTime();

                this.currentState = State.PAYMENT_CVC;
                break;

            case PAYMENT_CVC:
                int cvc = integerInstruction;

                // Put all data in the holder
                CreditCard card = new CreditCard(this.pin, this.cardNumber, this.holder, this.expiryDate, cvc);

                // Check if payment passed
                if (controller.paymentRequest(card, vehicle.calculateTotalCost()))
                    this.currentState = State.PAYMENT_SUCCESS;
                else
                    this.currentState = State.PAYMENT_FAILED;
                break;

            case PAYMENT_SUCCESS:
                this.currentState = State.INSPECTION_INIT;
                break;

            case PAYMENT_FAILED:
                this.currentState = State.PAYMENT_INIT;
                break;

            case INSPECTION:
                break;
        }
    }
}
