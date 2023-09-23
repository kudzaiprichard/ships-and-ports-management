package container;

public class LiquidContainer extends HeavyContainer{
    public LiquidContainer(int ID, int weight) {
        super(ID, weight);
    }
    @Override
    public double fuelConsumption() {
        return (4.00*super.getWeight());
    }
}
