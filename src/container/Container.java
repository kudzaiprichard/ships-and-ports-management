package container;

public class Container {
    private int ID;
    private int weight;
    private Boolean isOnShip;
    private Boolean isOnPort;

    public Container(int ID, int weight, Boolean isOnShip, Boolean isOnPort) {
        this.ID = ID;
        this.weight = weight;
        this.isOnShip = isOnShip;
        this.isOnPort = isOnPort;
    }
    public Container() {}

    public Boolean getOnShip() {
        return isOnShip;
    }

    public void setOnShip(Boolean onShip) {
        isOnShip = onShip;
    }

    public Boolean getOnPort() {
        return isOnPort;
    }

    public void setOnPort(Boolean onPort) {
        isOnPort = onPort;
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
        return "{ " +
                "\nID: " + ID +
                "\nweight: " + weight +
                "\nisOnShip: " + isOnShip +
                "\nisOnPort: " + isOnPort;
    }
}

