package container;

public class BasicContainer extends Container{
    public BasicContainer(int ID, Integer weight) {
        super(ID, weight);
    }
    @Override
    public void setWeight(int weight) {
        if (weight<=5000){
            super.setWeight(weight);
        }
        else{
            System.out.println("This is a heavy container");
        }
    }

    @Override
    public double fuelConsumption() {
       return (2.50*super.getWeight());
    }
}
