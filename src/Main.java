import Repository.ContainerRepository;
import Repository.PortRepository;
import Repository.ShipRepository;
import container.Container;
import portAShip.Ship;

import java.util.*;
import static java.lang.System.exit;
import static util.Utils.*;


public class Main {

    public static void main(String[] args) {
        PortRepository portRepository = new PortRepository();
        ShipRepository shipRepository = new ShipRepository();
        ContainerRepository containerRepository = new ContainerRepository();

        //Run the system methods
        try{
            //Run loop until user chooses to exit system
            while(true){
                int option = mainMenu();
                switch (option) {
                    case 1 -> {
                        Optional<String> type = Optional.empty();
                        System.out.println("::::::::::::::::::CREATE CONTAINER::::::::::::::::::");

                        System.out.println("Enter ID: ");
                        Scanner sc = new Scanner(System.in);
                        int ID = sc.nextInt();

                        System.out.println("Enter weight: ");
                        int weight = sc.nextInt();

                        if(weight >= 5000){
                            System.out.println("Enter type of heavy container: ");
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
                        Scanner sc = new Scanner(System.in);
                        Ship ship = createAShip();
                    }

                    //Create port menu option
                    case 3 ->{
                        System.out.println("::::::::::::::::::CREATE PORT::::::::::::::::::");
                    }

                    //Load container to ship menu option
                    case 4 ->{
                        System.out.println("::::::::::::::::::LOAD CONTAINER::::::::::::::::::");
                    }

                    //Unload container from ship menu option
                    case 5 ->{
                        System.out.println("::::::::::::::::::UNLOAD CONTAINER::::::::::::::::::");
                    }

                    //Sail ship to another port menu option
                    case 6 ->{
                        System.out.println("::::::::::::::::::SAIL SHIP::::::::::::::::::");
                    }

                    //Refuel ship menu option
                    case 7 ->{
                        System.out.println("::::::::::::::::::REFUEL SHIP::::::::::::::::::");
                    }

                    //Exit system menu option
                    case 8 -> {
                        System.out.println("Thank you for using ships and port manager v1.0");
                        System.out.println("Exiting System......");
                        exit(0);
                    }
                    default -> System.out.println("[ERR]: Invalid input");
                }
            }
        }catch (Exception e){
            System.out.println("[ERR] : could not run system " + e);
        }
    }
}