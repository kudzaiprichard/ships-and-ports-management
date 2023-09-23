package portAShip;

import container.Container;

import java.util.ArrayList;

public class Ship implements IShip {
    int portID ;
    double fuel;
    Port currentPort;

    private int ID;
    private Port p;
    private int totalWeightCapacity;
    private int maxNumberOfAllContainers;
    private int maxNumberOfHeavyContainers;
    private int maxNumberOfRefrigeratedContainers;
    private int maxNumberOfLiquidContainers;
    private double fuelConsumptionPerKM;

    private double tankCapacity;

    public Ship(int ID, Port p,
                int totalWeightCapacity, int maxNumberOfAllContainers,
                int maxNumberOfHeavyContainers, int maxNumberOfRefrigeratedContainers,
                int maxNumberOfLiquidContainers,
                double fuelConsumptionPerKM, double tankCapacity) {
        this.ID = ID;
        this.p = p;
        this.totalWeightCapacity = totalWeightCapacity;
        this.maxNumberOfAllContainers = maxNumberOfAllContainers;
        this.maxNumberOfHeavyContainers = maxNumberOfHeavyContainers;
        this.maxNumberOfRefrigeratedContainers = maxNumberOfRefrigeratedContainers;
        this.maxNumberOfLiquidContainers = maxNumberOfLiquidContainers;
        this.fuelConsumptionPerKM = fuelConsumptionPerKM;
        this.tankCapacity = tankCapacity;
    }

    @Override
    public ArrayList<Container> getCurrentContainers() {
        return null;
    }

    @Override
    public boolean sailTo(Port p) {
        return false;
    }

    @Override
    public void reFuel(double newFuel) {

    }

    @Override
    public boolean load(Container cont) {
        return false;
    }

    @Override
    public boolean unload(Container cont) {
        return false;
    }

    public int getPortID() {
        return portID;
    }

    public void setPortID(int portID) {
        this.portID = portID;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public Port getCurrentPort() {
        return currentPort;
    }

    public void setCurrentPort(Port currentPort) {
        this.currentPort = currentPort;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Port getP() {
        return p;
    }

    public void setP(Port p) {
        this.p = p;
    }

    public int getTotalWeightCapacity() {
        return totalWeightCapacity;
    }

    public double getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    public void setTotalWeightCapacity(int totalWeightCapacity) {
        this.totalWeightCapacity = totalWeightCapacity;
    }

    public int getMaxNumberOfAllContainers() {
        return maxNumberOfAllContainers;
    }

    public void setMaxNumberOfAllContainers(int maxNumberOfAllContainers) {
        this.maxNumberOfAllContainers = maxNumberOfAllContainers;
    }

    public int getMaxNumberOfHeavyContainers() {
        return maxNumberOfHeavyContainers;
    }

    public void setMaxNumberOfHeavyContainers(int maxNumberOfHeavyContainers) {
        this.maxNumberOfHeavyContainers = maxNumberOfHeavyContainers;
    }

    public int getMaxNumberOfRefrigeratedContainers() {
        return maxNumberOfRefrigeratedContainers;
    }

    public void setMaxNumberOfRefrigeratedContainers(int maxNumberOfRefrigeratedContainers) {
        this.maxNumberOfRefrigeratedContainers = maxNumberOfRefrigeratedContainers;
    }

    public int getMaxNumberOfLiquidContainers() {
        return maxNumberOfLiquidContainers;
    }

    public void setMaxNumberOfLiquidContainers(int maxNumberOfLiquidContainers) {
        this.maxNumberOfLiquidContainers = maxNumberOfLiquidContainers;
    }

    public double getFuelConsumptionPerKM() {
        return fuelConsumptionPerKM;
    }

    public void setFuelConsumptionPerKM(double fuelConsumptionPerKM) {
        this.fuelConsumptionPerKM = fuelConsumptionPerKM;
    }
}
