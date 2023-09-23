package util;

import container.*;
import portAShip.Port;
import portAShip.Ship;

import java.util.*;

public class Utils {

    public Utils() {
    }

    //User menu
    public static int mainMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n######################################################################################");
        System.out.println("*********************** Welcome to portAShip.Port Management System ********************");
        System.out.println("########################################################################################\n");
        System.out.println("""
                Choose an option
                 [1] Create a container
                 [2] Create a ship
                 [3] Create a port
                 [4] Load a container to a ship
                 [5] Unload a container from a ship
                 [6] Sail ship to another port
                 [7] Refuel ship
                 [8] Exit
                """);
        System.out.println("Enter Option: ");
        return sc.nextInt();
    }

    //Method for creating a new container
    public static Container createAContainer(Integer ID, Integer weight, Optional<String> heavyType){

        //Basic container category
        if(weight<5000){
            return new BasicContainer(ID,weight);
        }

        //Heavy container category
        else {
            if(heavyType.isPresent()){
                //Liquid container.Container category
                if(heavyType.get().equalsIgnoreCase("L")) {
                    return new LiquidContainer(ID, weight);
                }
                //Refrigerated container category
                else if (heavyType.get().equalsIgnoreCase("R")) {
                    return new RefrigeratedContainer(ID, weight);
                }
                //If not "L" or "R" its invalid input
                else {
                    System.out.println("[ERR]: Invalid container type");
                    return null;
                }
            }

            //If heavy type is not specified create a heavy container category
            else {
                return new HeavyContainer(ID,weight);
            }
        }
    }

    //Creating a new ship object
    public static Ship createAShip(int ID, Port port,
                                    int totalWeightCapacity, int maxNumberOfAllContainers,
                                    int maxNumberOfHeavyContainers, int maxNumberOfRefrigeratedContainers,
                                    int maxNumberOfLiquidContainers,
                                    double fuelConsumptionPerKM, double tankCapacity){

        //Check if there are no null or zero inputs
        if (totalWeightCapacity != 0 &&
                tankCapacity != 0 &&
                fuelConsumptionPerKM != 0
        ){
            return new Ship(ID,port,
                    totalWeightCapacity,maxNumberOfAllContainers,
                    maxNumberOfHeavyContainers,maxNumberOfRefrigeratedContainers,
                    maxNumberOfLiquidContainers,fuelConsumptionPerKM, tankCapacity);
        }

        System.out.println("[ERR]:Invalid inputs, could not create ship");
        return null;
    }

    //Method for creating a port
    public static Port createAPort(Double latitude, Double longitude){
        Random random = new Random();//simulating a random id
        return new Port(random.nextInt(),latitude,longitude);
    }

    //check if ship exists on port
    public static boolean checkIfShipExist(Ship ship, Port port) {
        for (Ship s: port.getCurrent()){
            //find matching ship
            if(Objects.equals(s, ship)){
                return true;
            }
        }
        return false;
    }

    //Method to check if a given container exists on a given port
    public static boolean checkIfContainerExist(Container container, Port port) {
        for (Container c: port.getContainers()){
            //find containers that match
            if(Objects.equals(c, container)){
                return true;
            }
        }
        return false;
    }

    //Calculate ship loading restrictions
    public static List<Integer> loadingShipRestrictions(Ship ship) {
        int weightOfHeavyContainers = 0,
                weightOfBasicContainers = 0,
                numOfBasicContainers = 0,
                numOfHeavyContainers = 0;

        List<Integer> returnValue = new ArrayList<>();

        for (Container c: ship.getCurrentContainers()){
            if (c.getWeight()>5000){
                weightOfHeavyContainers += c.getWeight();
                numOfHeavyContainers ++;
            }
            else if (c.getWeight()<5000){
                weightOfBasicContainers += c.getWeight();
                numOfBasicContainers ++;
            }
        }

        //add restriction inside return value list
        returnValue.add(weightOfHeavyContainers);
        returnValue.add(weightOfBasicContainers);
        returnValue.add(numOfBasicContainers);
        returnValue.add(numOfHeavyContainers);

        //return calculated values
        return returnValue;
    }

    //Method for loading a container into a ship
    public static void loadAContainer(Container container, Ship ship, Port port){

        //Can only Load a container if a container and ship exits on the same port
        if(checkIfContainerExist(container, port) && checkIfShipExist(ship, port) ){
            //Give appropriate response
            System.out.println("container.Container and portAShip.Ship exists on the same port");

            //Load container to ship
            // Calculate ship Restrictions
            List<Integer> restricitons = loadingShipRestrictions(ship);

            //calculate the amount of space(in weight) left in ship
            int spaceLeftInWeight = ship.getTotalWeightCapacity() - (restricitons.get(0) + restricitons.get(1));

            // Checking if loading is possible in the portAShip.Ship
            if (spaceLeftInWeight != 0 && spaceLeftInWeight <= 5000 && container.getWeight() < 5000){
                ship.load(container);
            }

            else if(spaceLeftInWeight > 5000 && container.getWeight() > 5000){
                ship.load(container);
            }

            else{
                System.out.println("[ERR]: ship is full");
            }

        }
        else {
            System.out.println("[ERR] : Could not find container or ship on port");
        }

    }

    //Method to unload a container from a ship
    public static void unloadAContainer(Container container, Ship ship, Port port){

        //check if container exists in ship
        for(Container c: ship.getCurrentContainers()){
            if(Objects.equals(container, c)){
                //if container exists unload container
                ship.unload(container);
                return;
            }
        }
        System.out.println("[ERR]: container.Container does not exits");
    }

    //Method to sail ship to port
    public static void sailShipToPort(Ship ship, Port port){
        int totalContainerFuelConsumption=0;

        //calculate the amount of fuel consumption per container
        for(Container c: ship.getCurrentContainers()){
            totalContainerFuelConsumption += c.fuelConsumption();
        }

        //check ship's fuel consumption against fuel consumption per container
        if (totalContainerFuelConsumption > ship.getFuelConsumptionPerKM()){
            System.out.println("portAShip.Ship cannot sail");
            return;
        }

        System.out.println("portAShip.Ship can sail to destination");
    }

    //Method for refueling a ship
    public static void refuel(Ship ship, double fuelAmount){
        //Calculate the current fuel amount
        int currentFuelAmount = (int) (ship.getTankCapacity() - ship.getFuel());

        //Check if ship can refuel
        if (fuelAmount > 0 && currentFuelAmount<= fuelAmount){
            ship.setFuel(ship.getFuel()+fuelAmount);
            System.out.println("portAShip.Ship ID: "+ ship.getID()+"\n portAShip.Ship current fuel: "+ship.getFuel());
            return;
        }

        System.out.println("[ERR]: Invalid input");
    }


}
