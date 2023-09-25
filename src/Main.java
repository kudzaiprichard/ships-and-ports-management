import Repository.ContainerRepository;
import Repository.PortRepository;
import Repository.ShipRepository;
import portAShip.Port;
import portAShip.Ship;

import java.util.*;
import static java.lang.System.exit;
import static util.Utils.*;


public class Main {

    public static void main(String[] args) {
        //Run the system methods
        try{
            PortRepository portRepository = new PortRepository();
            ShipRepository shipRepository = new ShipRepository();
            ContainerRepository containerRepository = new ContainerRepository();
            Scanner sc = new Scanner(System.in);
            //Run loop until user chooses to exit system
            while(true){
                int option = mainMenu();
                switch (option) {
                    case 1 -> {
                        Optional<String> type = Optional.empty();
                        System.out.println("::::::::::::::::::CREATE CONTAINER::::::::::::::::::");

                        System.out.println("Enter ID: ");
                        int ID = sc.nextInt();

                        System.out.println("Enter weight: ");
                        int weight = sc.nextInt();

                        if(weight >= 5000){
                            System.out.println("Enter type of heavy container [L] for Liquid  [R] for Refrigerated: ");
                            type = Optional.ofNullable(sc.next());
                        }

                        //Check if container already exists
                        if(containerRepository.findContainerById(ID) != null) {
                            System.out.println("[ERR]-> ID already exists");
                            return;
                        }

                        var container = createAContainer(ID, weight, type);
                        containerRepository.addContainer(container);

                        if(container != null)
                            System.out.println("Container (id: " + container.getID()+ " weight: "
                                    + container.getWeight()
                                    + ") was created successfully"
                            );
                        else System.out.println("[ERR]-> Failed to create container");
                    }

                    //Create ship menu option
                    case 2 ->{
                        System.out.println("::::::::::::::::::CREATE SHIP::::::::::::::::::");

                        System.out.println("Enter Ship ID: ");
                        int ID = sc.nextInt();

                        System.out.println("Enter Port ID: ");
                        int portID = sc.nextInt();

                        System.out.println("Enter total weight capacity: ");
                        int totalWeightCapacity = sc.nextInt();

                        System.out.println("Enter max number of all containers: ");
                        int maxNumberOfAllContainers = sc.nextInt();

                        System.out.println("Enter max number of heavy containers: ");
                        int maxNumberOfHeavyContainers  = sc.nextInt();

                        System.out.println("Enter max number of refrigerated containers: ");
                        int maxNumberOfRefrigeratedContainers = sc.nextInt();

                        System.out.println("Enter max number of liquid containers: ");
                        int maxNumberOfLiquidContainers = sc.nextInt();

                        System.out.println("Enter fuel consumption per km: ");
                        double fuelConsumptionPerKM = sc.nextDouble();

                        System.out.println("Enter ship tank capacity: ");
                        double tankCapacity = sc.nextDouble();

                        if(shipRepository.findShipById(ID) != null){
                            System.out.println("[ERR]-> Ship ID already exists");
                            return;
                        }

                        Port port = portRepository.findPortById(portID);

                        if(port == null){
                            System.out.println("[ERR]-> Port does not exist ");
                            return;
                        }

                        Ship ship = createAShip(ID,port,totalWeightCapacity,maxNumberOfAllContainers,
                                    maxNumberOfHeavyContainers,maxNumberOfRefrigeratedContainers,
                                    maxNumberOfLiquidContainers,fuelConsumptionPerKM,tankCapacity);

                        if(ship != null){
                            System.out.println("Ship (ship id: " + ship.getID()+", Port latitude: "
                                    + ship.getCurrentPort().getLatitude()
                                    + " and Port longitude : "+ ship.getCurrentPort().getLongitude()
                                    +") was created successfully");
                            shipRepository.addShip(ship);
                        }
                    }

                    //Create port menu option
                    case 3 ->{
                        System.out.println("::::::::::::::::::CREATE PORT::::::::::::::::::");

                        System.out.println("Enter longitude: ");
                        double longitude = sc.nextDouble();

                        System.out.println("Enter latitude: ");
                        double latitude = sc.nextDouble();

                        if(portRepository.findPortByLatitudeAndLongitude(latitude,longitude) != null){
                            System.out.println("[ERR]-> Port already exists ");
                            return;
                        }

                        Random random = new Random();//simulating a random id
                        int ID = random.nextInt();

                        ID = generateID(portRepository, ID);
                        Port port = createAPort(ID,longitude,latitude);
                        portRepository.addPort(port);

                        System.out.println("Port (ID: " + port.getPortID() +", latitude: " + port.getLatitude() +
                                " and longitude: " + port.getLongitude() + ") was created successfully");
                    }

                    //Load container to ship menu option
                    case 4 ->{
                        System.out.println("::::::::::::::::::LOAD CONTAINER::::::::::::::::::");

                        System.out.println("Enter ship id: ");
                        int shipId = sc.nextInt();

                        System.out.println("Enter port id: ");
                        int portId = sc.nextInt();

                        System.out.println("Enter container id: ");
                        int containerId = sc.nextInt();

                        var container = containerRepository.findContainerById(containerId);
                        var ship = shipRepository.findShipById(shipId);
                        var port = portRepository.findPortById(portId);

                        //Check if specified container, port and ship exists
                        if(container == null){
                            System.out.println("[ERR]-> Container does not exist");
                        }

                        if (ship == null){
                            System.out.println("[ERR]-> Ship does not exist");
                            return;
                        }

                        if(port == null){
                            System.out.println("[ERR]-> Port does not exist");
                            return;
                        }

                        loadAContainer(container, ship, port);

                        //Should be able to assign container to ship or port on creation
                        //Should also remove container from ship or port on unloading or loading
                        //containerRepository.deleteContainerById(containerId);


                    }

                    //Unload container from ship menu option
                    case 5 ->{
                        System.out.println("::::::::::::::::::UNLOAD CONTAINER::::::::::::::::::");

                        System.out.println("Enter ship id: ");
                        int shipId = sc.nextInt();

                        System.out.println("Enter port id: ");
                        int portId = sc.nextInt();

                        System.out.println("Enter container id: ");
                        int containerId = sc.nextInt();

                        var container = containerRepository.findContainerById(containerId);
                        var ship = shipRepository.findShipById(shipId);
                        var port = portRepository.findPortById(portId);

                        //Check if specified container, port and ship exists
                        if(container == null){
                            System.out.println("[ERR]-> Container does not exist");
                        }

                        if (ship == null){
                            System.out.println("[ERR]-> Ship does not exist");
                            return;
                        }

                        if(port == null){
                            System.out.println("[ERR]-> Port does not exist");
                            return;
                        }

                        unloadAContainer(container, ship, port);

                        //Should be able to assign container to ship or port on creation
                        //Should also remove container from ship or port on unloading or loading
                        //containerRepository.deleteContainerById(containerId);
                    }

                    //Sail ship to another port menu option
                    case 6 ->{
                        System.out.println("::::::::::::::::::SAIL SHIP::::::::::::::::::");

                        System.out.println("Enter ship id: ");
                        int shipId = sc.nextInt();

                        System.out.println("Enter port id: ");
                        int portId = sc.nextInt();

                        var ship = shipRepository.findShipById(shipId);
                        var port = portRepository.findPortById(portId);

                        if (ship == null){
                            System.out.println("[ERR]-> Ship does not exist");
                            return;
                        }

                        if(port == null){
                            System.out.println("[ERR]-> Port does not exist");
                            return;
                        }
                        sailShipToPort(ship, port);
                    }

                    //Refuel ship menu option
                    case 7 ->{
                        System.out.println("::::::::::::::::::REFUEL SHIP::::::::::::::::::");

                        System.out.println("Enter fuel amount: ");
                        double fuelAmount = sc.nextDouble();

                        System.out.println("Enter ship id: ");
                        int shipId = sc.nextInt();

                        var ship = shipRepository.findShipById(shipId);

                        //Check if ship exists
                        if (ship == null){
                            System.out.println("[ERR]-> Ship does not exist");
                            return;
                        }

                        refuel(ship, fuelAmount);
                    }

                    //Exit system menu option
                    case 8 -> {
                        System.out.println("Thank you for using ships and port manager v1.0");
                        System.out.println("Exiting System......");
                        exit(0);
                    }
                    default -> System.out.println("[ERR]-> Invalid input");
                }
            }
        }catch (Exception e){
            System.out.println("[ERR] : could not run system " + e);
        }
    }

    private static int generateID(PortRepository portRepository, int ID) {
        if(portRepository.findPortById(ID) == null){
            return ID;
        }
        return generateID(portRepository,ID++);
    }
}