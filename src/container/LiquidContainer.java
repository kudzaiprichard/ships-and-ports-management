package container;

public class LiquidContainer extends HeavyContainer{
    public LiquidContainer(int ID, int weight, Boolean isOnShip, Boolean isOnPort) {
        super(ID, weight, isOnShip, isOnPort);
    }

    public LiquidContainer() {
    }

    @Override
    public double fuelConsumption() {
        return (4.00*super.getWeight());
    }

    @Override
    public String toString() {
        return super.toString() + "\nCategory: Liquid Container\n}";
    }
}
