package container;

public class HeavyContainer extends Container{
    public HeavyContainer(int ID, int weight) {
        super(ID, weight);
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
}
