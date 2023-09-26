package util;

import Repository.ContainerRepository;
import Repository.PortRepository;
import Repository.ShipRepository;
import container.*;
import portAShip.Port;
import portAShip.Ship;

import java.util.*;

public class Utils {
    static ShipRepository shipRepository = new ShipRepository();
    static ContainerRepository containerRepository = new ContainerRepository();
    PortRepository portRepository = new PortRepository();
    public Utils() {
    }

    //User menu
    public static int mainMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n########################################################################################");
        System.out.println("*********************** Welcome to Ships And Port Management System ********************");
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
            return new BasicContainer(ID,weight,false, true);
        }

        //Heavy container category
        else {
            if(heavyType.isPresent()){
                //Liquid container.Container category
                if(heavyType.get().equalsIgnoreCase("L")) {
                    return new LiquidContainer(ID, weight,false, true);
                }
                //Refrigerated container category
                else if (heavyType.get().equalsIgnoreCase("R")) {
                    return new RefrigeratedContainer(ID, weight,false, true);
                }

                //Refrigerated container category
                else if (heavyType.get().equalsIgnoreCase("N")) {
                    return new HeavyContainer(ID,weight,false, true);
                }

                //If not "L" or "R" or "N" its invalid input
                else {
                    System.out.println("[ERR]-> Invalid container type");
                    return null;
                }
            }
            System.out.println("[ERR]-> Please specify container type");
            return null;
        }
    }

    //Creating a new ship object
    public static Ship createAShip(int ID, Port port,
                                    int totalWeightCapacity, int maxNumberOfAllContainers,
                                    int maxNumberOfHeavyContainers, int maxNumberOfRefrigeratedContainers,
                                    int maxNumberOfLiquidContainers,
                                    double fuelConsumptionPerKM, double tankCapacity, double fuelInTank){

        //Check if there are no null or zero inputs
        if (totalWeightCapacity != 0 &&
                tankCapacity != 0 &&
                fuelConsumptionPerKM != 0
        ){
            return new Ship(ID,port,totalWeightCapacity,maxNumberOfAllContainers,
                            maxNumberOfHeavyContainers,maxNumberOfRefrigeratedContainers,
                                maxNumberOfLiquidContainers,fuelConsumptionPerKM,tankCapacity,fuelInTank
                            );
        }

        System.out.println("[ERR]-> Invalid inputs, could not create ship");
        return null;
    }

    //Method for creating a port
    public static Port createAPort(int ID,Double latitude, Double longitude){
        return new Port(ID,latitude,longitude);
    }

    //check if ship exists on port
    public static boolean checkIfShipExist(Ship ship, Port port) {
        if(port.getOnPortShipList() != null){
            for (Ship s: port.getOnPortShipList()){
                //find matching ship
                if(s.getID() ==  ship.getID()){
                    return true;
                }
            }
        }
        return false;
    }

    //Method to check if a given container exists on a given port
    public static boolean checkIfContainerExist(Container container, Port port) {
        if(port.getOnPortContainerList() != null){
            for (Container c: port.getOnPortContainerList()){
                //find containers that match
                if(c.getID() == container.getID()){
                    return true;
                }
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

        if(ship.getCurrentContainers() != null){
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
        int maxNumberOfHeavyContainers = ship.getMaxNumberOfHeavyContainers();
        int maxNumberOfAllContainers = ship.getMaxNumberOfAllContainers();
        int maxNumberOfBasicContainers = (maxNumberOfAllContainers - maxNumberOfHeavyContainers);
        for(Container c: ship.getOnShipContainerList()){
            if(c.getID() == container.getID()){
                System.out.println("Container already exist in ship");
                return;
            }
        }

        //load heavy container
        if(container.getWeight() > 5000){
            if(maxNumberOfHeavyContainers > 0){
                if(ship.load(container)){
                    container.setOnPort(false);
                    container.setOnShip(true);
                    System.out.println("Container was loaded successfully");
                }else{
                    System.out.println("[sERR]-> failed to load container ");
                }

            }
            else if(maxNumberOfBasicContainers > 0) {
                System.out.println("[ERR]-> Cannot load container, no more space left for heavy container in ship ");
                System.out.println("[NB]-> Try loading basic container");
            }
            else {
                System.out.println("[ERR]-> Cannot load container, no more space left  in ship ");
            }
        }

        //load basic container
        else if(container.getWeight() < 5000){
            if (maxNumberOfBasicContainers > 0){
                if(ship.load(container)){
                    container.setOnPort(false);
                    container.setOnShip(true);
                    System.out.println("Container was loaded successfully");
                }else{
                    System.out.println("[sERR]-> failed to load container ");
                }
            }
            else if(maxNumberOfHeavyContainers > 0) {
                System.out.println("[ERR]-> Cannot load container, no more space left for basic container in ship ");
                System.out.println("[NB]-> Try loading heavy container");
            }
            else {
                System.out.println("[ERR]-> Cannot load container, no more space left  in ship ");
            }
        }
    }

    //Method to unload a container from a ship
    public static void unloadAContainer(Container container, Ship ship, Port port){

        //Check if container exist in ship
        if(ship.getOnShipContainerList() != null){
            for(Container c: ship.getOnShipContainerList()){
                if(c.getID() != container.getID()){
                    System.out.println("[ERR]-> Container already does not exist in ship");
                    return;
                }
            }
        }

        //unload container
        if(ship.unload(container)){
            container.setOnPort(true);
            container.setOnShip(false);
            System.out.println("Container was unloaded successfully");
        }else {
            System.out.println("[ERR]-> Failed to unload container");
        }

    }

    //Calculate distance
    private static double calculateDistance(double lat1, double lon1, double lat2, double lon2, String unit) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        }
        else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            if (unit.equals("K")) {
                dist = dist * 1.609344;
            } else if (unit.equals("N")) {
                dist = dist * 0.8684;
            }
            return (dist);
        }
    }

    //Method to sail ship to port
    public static void sailShipToPort(Ship ship, Port port){

        double distance = calculateDistance(
                ship.getCurrentPort().getLatitude(),
                ship.getCurrentPort().getLongitude(),
                port.getLatitude(),
                port.getLongitude(),
                "K"
        );

        if(distance == 0){
            System.out.println("[ERR]-> Ship Can not navigate to the same destination");
        }else{
            int totalContainerFuelConsumption = 0;
            double tankCapacity = ship.getTankCapacity();
            double fuelInTank = ship.getFuelInTank();
            double fuelConsuptionPerKM = ship.getFuelConsumptionPerKM();

            if(tankCapacity < (fuelConsuptionPerKM * distance)){
                System.out.println("[ERR]-> Cannot sail ship, tank capacity too small for sail");
                System.out.println("Distance to sail port destination is: " + distance);
                System.out.println("Amount of fuel needed to make this sail: " + (fuelConsuptionPerKM * distance));
            }
            else if(fuelInTank < (fuelConsuptionPerKM * distance)){
                System.out.println("[ERR]-> Cannot sail ship, current fuel too small for sail");
                System.out.println("Distance to sail port destination is: " + distance);
                System.out.println("Amount of fuel needed to make this sail: " + (fuelConsuptionPerKM * distance));
            }
            else{
                System.out.println("Distance to sail port destination is: " + distance);
                System.out.println("Ship is sailing to destination");
            }

        }

    }

    //Method for refueling a ship
    public static void refuel(Ship ship, double fuelAmount){

        if(ship.getTankCapacity() < fuelAmount){
            System.out.println("[ERR]-> cannot enter fuel amount greater than fuel tank capacity");
        }
        else {
            //Check if ship can refuel
            if (fuelAmount > 0){//&& currentFuelAmount <= fuelAmount
                //Calculate the current fuel amount
                double currentFuelAmount = (ship.getTankCapacity() - ship.getFuelInTank());
                ship.setFuelInTank(ship.getFuelInTank()+fuelAmount);
                System.out.println("Ship ID: "+ ship.getID()+"\nShip refueled to: "+ship.getFuelInTank());
            }
            else {
                System.out.println("[ERR]-> cannot enter fuel amount less than or equal to zero");
            }
        }
    }


}
