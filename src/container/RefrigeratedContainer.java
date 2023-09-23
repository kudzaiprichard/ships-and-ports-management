package container;

public class RefrigeratedContainer extends HeavyContainer{
    public RefrigeratedContainer(int ID, int weight) {
        super(ID, weight);
    }
    @Override
    public double fuelConsumption() {
        return (5.00*super.getWeight());
    }
}
