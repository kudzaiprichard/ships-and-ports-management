package container;

public class Container {
    private int ID;
    private int weight;

    public Container(int ID, int weight){
        this.ID = ID;
        this.weight = weight;
    }

    public double consumption(){
        return 0;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Container container = (Container) o;
        return ID == container.ID && weight == container.weight;
    }
    public double fuelConsumption(){
        return 0;
    }

    @Override
    public String toString() {
        return "Container{}";
    }
}

