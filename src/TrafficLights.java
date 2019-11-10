import java.awt.*;

public class TrafficLights {
    int x;
    int y;
    int roadID;
    boolean running;

    public TrafficLights(int x, int y, int roadID, boolean running){
        this.x = x;
        this.y = y;
        this.roadID = roadID;
        this.running = running;
    }

    public void paint(Graphics g){
    }

    public void setRoadID(int roadID) {
        this.roadID = roadID;
    }
    public int getRoadID() {
        return roadID;
    }
    public boolean isRunning() {
        return running;
    }
    public void setRunning(boolean running) {
        this.running = running;
    }
}
