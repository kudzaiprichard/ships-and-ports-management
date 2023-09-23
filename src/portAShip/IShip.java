package portAShip;

import container.Container;

import java.util.ArrayList;

public interface IShip {
    ArrayList<Container> getCurrentContainers();
    boolean sailTo(Port p); //returns true if a ship successfully sailed to the destination port
    void reFuel(double newFuel); //adds fuel to a ship
    boolean load(Container cont); //returns true if a container was successfully loaded to a ship
    boolean unload(Container cont); //returns true if a container was successfully unloaded from a ship
}
