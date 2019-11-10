import java.awt.*;

public class Bus extends Vehicle {

    public Bus(int x, int y, int roadID, char Direction){
        super(x,y,roadID, Direction);
        length = 20;
        width = 5;
        speed = 2;

    }


    public void paint(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x,y,length,width);
    }

}
