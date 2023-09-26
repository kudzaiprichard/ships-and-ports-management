package portAShip;

import container.Container;

import java.util.ArrayList;
import java.util.List;

public class Ship implements IShip {

    private int ID;
    private Port currentPort;
    private int totalWeightCapacity;
    private int maxNumberOfAllContainers;
    private int maxNumberOfHeavyContainers;
    private int maxNumberOfRefrigeratedContainers;
    private int maxNumberOfLiquidContainers;
    private double fuelConsumptionPerKM;
    private double tankCapacity;
    private double fuelInTank;

    private List<Container> onShipContainerList = new ArrayList<>();


    public Ship(int ID, Port currentPort, int totalWeightCapacity, int maxNumberOfAllContainers,
                int maxNumberOfHeavyContainers, int maxNumberOfRefrigeratedContainers,
                int maxNumberOfLiquidContainers, double fuelConsumptionPerKM, double tankCapacity,
                double fuelInTank
    ) {
        this.ID = ID;
        this.currentPort = currentPort;
        this.totalWeightCapacity = totalWeightCapacity;
        this.maxNumberOfAllContainers = maxNumberOfAllContainers;
        this.maxNumberOfHeavyContainers = maxNumberOfHeavyContainers;
        this.maxNumberOfRefrigeratedContainers = maxNumberOfRefrigeratedContainers;
        this.maxNumberOfLiquidContainers = maxNumberOfLiquidContainers;
        this.fuelConsumptionPerKM = fuelConsumptionPerKM;
        this.tankCapacity = tankCapacity;
        this.fuelInTank = fuelInTank;
    }


    @Override
    public List<Container> getCurrentContainers() {
        return this.onShipContainerList;
    }

    @Override
    public boolean sailTo(Port p) {
        return false;
    }

    @Override
    public void reFuel(double newFuel) {
        setFuelInTank(newFuel);
    }

    @Override
    public boolean load(Container cont) {
        return getOnShipContainerList().add(cont);
    }

    @Override
    public boolean unload(Container cont) {
        return getOnShipContainerList().remove(cont);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Port getCurrentPort() {
        return currentPort;
    }

    public void setCurrentPort(Port currentPort) {
        this.currentPort = currentPort;
    }

    public int getTotalWeightCapacity() {
        return totalWeightCapacity;
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

    public double getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    public List<Container> getOnShipContainerList() {
        return onShipContainerList;
    }

    public void setOnShipContainerList(List<Container> onShipContainerList) {
        this.onShipContainerList = onShipContainerList;
    }

    public double getFuelInTank() {
        return fuelInTank;
    }

    public void setFuelInTank(double fuelInTank) {
        this.fuelInTank = fuelInTank;
    }

    @Override
    public String toString() {
        return "{ " +
                "\nID: " + ID +
                "\ncurrentPort: " + currentPort.getPortID() +
                "\ntotalWeightCapacity: " + totalWeightCapacity +
                "\nmaxNumberOfAllContainers: " + maxNumberOfAllContainers +
                "\nmaxNumberOfHeavyContainers: " + maxNumberOfHeavyContainers +
                "\nmaxNumberOfRefrigeratedContainers: " + maxNumberOfRefrigeratedContainers +
                "\nmaxNumberOfLiquidContainers: " + maxNumberOfLiquidContainers +
                "\nfuelConsumptionPerKM: " + fuelConsumptionPerKM +
                "\ntankCapacity: " + tankCapacity +
                "\nfuelInTank: " + fuelInTank + "\n" +
                "\nContainers in ship: " + onShipContainerList +
                "\n} ";
    }
}
