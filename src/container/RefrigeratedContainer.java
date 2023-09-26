package container;

public class RefrigeratedContainer extends HeavyContainer{
    public RefrigeratedContainer(int ID, int weight, Boolean isOnShip, Boolean isOnPort) {
        super(ID, weight, isOnShip, isOnPort);
    }

    public RefrigeratedContainer() {
    }

    @Override
    public double fuelConsumption() {
        return (5.00*super.getWeight());
    }

    @Override
    public String toString() {
        return super.toString() + "\nCategory: Refrigerated Container\n}";
    }
}
