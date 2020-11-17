package model;

/**
 *  InHouse.java model
 */

/**
 *
 * @author Jonathan Payarers
 */
public class InHouse extends Part {
    private int MachineId;

    public InHouse(int id, String name, double price, int stock, int min, int max,int MachineId) {
        super(id, name, price, stock, min, max);
        this.MachineId = MachineId;
    }

    /**
     * @return the companyName
     */
    public int getMachineId() {
        return MachineId;
    }
    /**
     * @param  machineId The machineId to set
     */
    public void setMachineId(int machineId) {
        MachineId = machineId;
    }
}
