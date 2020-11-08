package model;

public class InHouse extends Part {
    private int MachineId;

    public InHouse(int id, String name, double price, int stock, int min, int max) {
        super(id, name, price, stock, min, max);
    }

    public int getMachineId() {
        return MachineId;
    }

    public void setMachineId(int machineId) {
        MachineId = machineId;
    }
}
