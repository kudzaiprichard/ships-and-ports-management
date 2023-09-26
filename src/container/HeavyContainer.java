package container;

public class HeavyContainer extends Container{
    public HeavyContainer(int ID, int weight, Boolean isOnShip, Boolean isOnPort) {
        super(ID, weight, isOnShip, isOnPort);
    }

    public HeavyContainer() {
    }

    @Override
    public void setWeight(int weight) {
        if (weight>5000){
            super.setWeight(weight);
        }
        else{
            System.out.println("This is a basic container");
        }
    }

    @Override
    public double fuelConsumption() {
        return (3.00*super.getWeight());
    }

    @Override
    public String toString() {
        return super.toString() + "\nType: Heavy Container\n}";
    }
}
