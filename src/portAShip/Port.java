package portAShip;

import container.Container;

import java.util.ArrayList;
import java.util.List;

public class Port implements IPort{
    int portID;
    double latitude;
    double longitude;

    List<Container> onPortContainerList = new ArrayList<>();
    List<Ship> onPortShipHistoryList = new ArrayList<>(); //keeps track of every ship that has visited
    List<Ship> onPortShipList = new ArrayList<>(); //keeps track of the ships currently here

    public Port(int portID, double latitude, double longitude){
        this.portID=portID;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    @Override
    public void incomingShip(Ship s) {
        onPortShipList.add(s);
        onPortShipHistoryList.add(s);
    }

    @Override
    public void outgoingShip(Ship s) {
        onPortShipList.remove(s);
    }

    public int getPortID() {
        return portID;
    }

    public void setPortID(int portID) {
        this.portID = portID;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<Container> getOnPortContainerList() {
        return onPortContainerList;
    }

    public void setOnPortContainerList(List<Container> onPortContainerList) {
        this.onPortContainerList = onPortContainerList;
    }

    public List<Ship> getOnPortShipHistoryList() {
        return onPortShipHistoryList;
    }

    public void setOnPortShipHistoryList(List<Ship> onPortShipHistoryList) {
        this.onPortShipHistoryList = onPortShipHistoryList;
    }

    public List<Ship> getOnPortShipList() {
        return onPortShipList;
    }

    public void setOnPortShipList(List<Ship> onPortShipList) {
        this.onPortShipList = onPortShipList;
    }

    @Override
    public String toString() {
        return "{ " +
                "\nportID: " + portID +
                "\nlatitude: " + latitude +
                "\nlongitude: " + longitude + "\n" +
                "\nContainers on this port: " + onPortContainerList + "\n" +
                "\nShips on this port: " + onPortShipList + "\n" +
                "\n}";
    }
}
