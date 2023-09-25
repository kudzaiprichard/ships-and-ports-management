package Repository;

import container.Container;

import java.util.ArrayList;
import java.util.List;

public class ContainerRepository {
    private final List<Container> containerList = new ArrayList<>();
    public ContainerRepository(){}

    public void addContainer(Container container){
        containerList.add(container);
    }
    public Container findContainerById(int id){
        for (Container container: containerList){
            if(id == container.getID()) return container;
        }
        return null;
    }

    public boolean deleteContainerById(int id){
        return containerList.removeIf(container -> id == container.getID());
    }

    public List<Container> findAll() {
        return this.containerList;
    }
}
