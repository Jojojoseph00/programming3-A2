import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Roads extends JPanel {

    ArrayList<Vehicle> cars = new ArrayList<Vehicle>();
    ArrayList<xRoads> horizontalRoads = new ArrayList<xRoads>();
    ArrayList<xTrafficLights> horizontalTL = new ArrayList<xTrafficLights>();
    ArrayList<yTrafficLights> verticalTL = new ArrayList<yTrafficLights>();
    ArrayList<yRoads> verticalRoads = new ArrayList<yRoads>();
    ArrayList<RoadConnections> roadConnections = new ArrayList<RoadConnections>();


    final int MAP_WIDTH = 1200;
    final int ROAD_LENGTH = 300;
    final int XLANE_ONE_Y = 300;
    final int XLANE_TWO_Y = 500;


    public Roads(){
            super();
    }

    public void addVehicle(Vehicle v){
        cars.add(v);
    }

    public void addxRoad(xRoads xRoad){
        horizontalRoads.add(xRoad);
    }

    public void addyRoad(yRoads yRoad){
        verticalRoads.add(yRoad);
    }

    public void addxTL(xTrafficLights xTrafficLights){
        horizontalTL.add(xTrafficLights);
    }

    public void addyTL(yTrafficLights yTrafficLights){
        verticalTL.add(yTrafficLights);
    }

    public void paintComponent(Graphics g){
            super.paintComponent(g);
            for (int i = 0; i < cars.size(); i++){
                cars.get(i).paint(g);
            }
            for (int i = 0; i < horizontalRoads.size(); i++){
                horizontalRoads.get(i).paint(g);
            }
            for (int i = 0; i < verticalRoads.size(); i++){
                verticalRoads.get(i).paint(g);
            }
            for (int i = 0; i < horizontalTL.size(); i++){
                horizontalTL.get(i).paint(g);
            }


    }

    public void initDraw(){

        // Default x roads
        xRoads defRoad1 = new xRoads(0,XLANE_ONE_Y,300,XLANE_ONE_Y,0);
        addxRoad(defRoad1);
        xRoads defRoad2 = new xRoads(300,XLANE_ONE_Y,600,XLANE_ONE_Y,2);
        addxRoad(defRoad2);
        xRoads defRoad3 = new xRoads(600,XLANE_ONE_Y,900,XLANE_ONE_Y,4);
        addxRoad(defRoad3);
        xRoads defRoad4 = new xRoads(900,XLANE_ONE_Y,1200,XLANE_ONE_Y,6);
        addxRoad(defRoad4);

        //Default y roads
        xRoads defRoad5 = new xRoads(300,0,300,300,1);
        addxRoad(defRoad5);
        xRoads defRoad6 = new xRoads(300,300,300,500,3);
        addxRoad(defRoad6);
        xRoads defRoad7 = new xRoads(300,500,300,700,5);
        addxRoad(defRoad7);



        yRoads defRoady1 = new yRoads(300,300, 300, 500, 1);
        addyRoad(defRoady1);
        yRoads defRoady2 = new yRoads(300,500, 300, 700, 3);
        addyRoad(defRoady2);
        yRoads defRoady3 = new yRoads(300,0, 300, 300, 5);
        addyRoad(defRoady3);

        RoadConnections connection1 = new RoadConnections(0,2,1,3);
        roadConnections.add(connection1);
        RoadConnections connection2 = new RoadConnections(2,4,4,4);
        roadConnections.add(connection2);
        RoadConnections connection3 = new RoadConnections(4,6,6,6);
        roadConnections.add(connection3);
        RoadConnections connection4 = new RoadConnections(6,6,6,6);
        roadConnections.add(connection4);

        //Default x traffic lights
        xTrafficLights defTL1 = new xTrafficLights(300,300,0,false);
        addxTL(defTL1);
        xTrafficLights defTL2 = new xTrafficLights(600, 300,2,true);
        addxTL(defTL2);
        xTrafficLights defTL3 = new xTrafficLights(900, 300, 4, false);
        addxTL(defTL3);
        xTrafficLights defTL4 = new xTrafficLights(1195, 300, 6, true);
        addxTL(defTL4);

        //Default y traffic lights
        xTrafficLights defTL5 = new xTrafficLights(300,500,1,false);
        addxTL(defTL5);
        xTrafficLights defTL6 = new xTrafficLights(300,700,3,false);
        addxTL(defTL6);

        Car defCar1 = new Car(60,295,0, 'e');
        addVehicle(defCar1);
        Car defCar2 = new Car(450,295,2, 'e');
        addVehicle(defCar2);
        Car defCar3 = new Car(100,295,0, 'e');
        addVehicle(defCar3);


    }

    public boolean lightStatus(int id){
        xTrafficLights xTrafficLights = horizontalTL.get(id);
        return xTrafficLights.isRunning();
    }
    public void lightChange(){
        for (int i = 0; i < horizontalTL.size(); i++ ){
            TrafficLights trafficLights = horizontalTL.get(i);
            if (trafficLights.running){
                trafficLights.setRunning(false);
            } else {
                trafficLights.setRunning(true);
            }
        }
    }


    public void move(){
        for (int i = 0; i < cars.size(); i++) {
            Vehicle v = cars.get(i);
            xRoads xRoads = horizontalRoads.get(v.roadID);
            v.setSpeed(4);

            if (v.roadID % 2 != 0){
                System.out.println("Vertical Road");
            } else {
                System.out.println("Horizontal Road");
                xTraversal(i);
                v.setX(v.getX() + v.getSpeed());
            }


        }
    }

    private Integer AddRoad(int ID) {
        int v;
        for (int i = 0; i < roadConnections.size(); i++){
            RoadConnections r = roadConnections.get(i);
            if (r.roadID == ID) {
                List<Integer> l1 = new ArrayList<Integer>();
                l1.add(r.c1);
                l1.add(r.c2);
                l1.add(r.c3);
                v = l1.get((int)(Math.random()*3));
                if ((r.c1 == r.c2) && (r.c2 == r.c3)) {
                    System.out.println("backtostart");
                    return 0;
                }
                while (v == r.roadID) {
                    v = l1.get((int)(Math.random()*3));
                    System.out.println("Reroll " +v);
                }
                System.out.println("V ==="+v);
                return v;
            }
        }
        return 0;
    }

    public boolean collision(Vehicle v, int x, int y){
        for (int i = 0; i <cars.size();i++){ //loop through vehicles
            Vehicle c = cars.get(i);
            if (y == c.getY() || (x == c.getX())) {  //check vehicles in X lane
                if (c.equals(v) == false) { //make sure the vehicle don't collide with itself
                    if (x == (c.getX()-8)){
                        return true;
                    }

                }
            }
        }
        return false;
    }


    public void spawnCar() {
        Car defCar10 = new Car(40,295,0, 'e');
        addVehicle(defCar10);
    }

    public void xTraversal(int i){
            Vehicle v = cars.get(i);
            xRoads xRoads = horizontalRoads.get(v.roadID);

            v.setSpeed(4);
            if (((v.getX() + v.getLength()) > xRoads.x2) && (!lightStatus(v.roadID))) {
                v.setSpeed(0);
            }
            if (((v.getX() + v.getLength() > xRoads.x2) && lightStatus(v.roadID))){
                v.setSpeed(4);
                //Choose next road
                int newR = AddRoad(v.getRoadID());
                v.setRoadID(newR);
                xRoads xRoads1 = horizontalRoads.get(v.roadID);
                if (collision(v, xRoads1.x1, v.getY())) {
                    v.setSpeed(0);
                    v.setX(v.getX() + v.getSpeed());
                }
                else if (!collision(v, xRoads1.x1, v.getY())) {
                    v.setX(xRoads1.x1);
                    v.setSpeed(4);
                    v.setX(v.getX() + v.getSpeed());
                }
            }
            if (collision(v, (v.getX() + v.getSpeed()), v.getY())){
                v.setSpeed(0);
            }
            v.setX(v.getX() + v.getSpeed());
    }


}







