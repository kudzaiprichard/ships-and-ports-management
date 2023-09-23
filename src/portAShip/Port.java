package portAShip;

import container.Container;

import java.util.ArrayList;

public class Port implements IPort{
    int portID;
    double latitude;
    double longitude;

    //getDistanceFromLatLonInKm(lat1,lon1,lat2,lon2) {
    // var R = 6371; // Radius of the earth in km var dLat = deg2rad(lat2-lat1);
    ArrayList<Container>containers;
    ArrayList<Ship>history; //keeps track of every ship that has visited
    ArrayList<Ship>current; //keeps track of the ships currently here

    public Port(int portID, double latitude, double longitude){
        this.portID=portID;
        this.latitude=latitude;
        this.longitude=longitude;
    }
//    public double getDistance(portAShip.Port, container.Container){
//        return 0;
//    }

    @Override
    public void incomingShip(Ship s) {

    }

    @Override
    public void outgoingShip(Ship s) {

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

    public ArrayList<Container> getContainers() {
        return containers;
    }

    public void setContainers(ArrayList<Container> containers) {
        this.containers = containers;
    }

    public ArrayList<Ship> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<Ship> history) {
        this.history = history;
    }

    public ArrayList<Ship> getCurrent() {
        return current;
    }

    public void setCurrent(ArrayList<Ship> current) {
        this.current = current;
    }

}
