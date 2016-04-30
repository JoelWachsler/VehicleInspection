package se.kth.ict.iv1350.inspectionCompany.util;

/**
 * Datatype Amount for easier change in the future.
 */
public class Amount {
    private int amount;

    public Amount(int amount) {
        this.amount = amount;
    }

    /**
     * Get the amount
     * @return The amount.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Combine this amount with the one passed to this function.
     * @param amount The amount to add.
     */
    public void add(Amount amount) {
        this.amount += amount.getAmount();
    }
}
