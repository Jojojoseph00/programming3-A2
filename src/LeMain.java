import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeMain implements ActionListener, Runnable {
    String fileName = "Untitled";
    JFrame frame = new JFrame("Traffic Simulation");
    Roads roads = new Roads();
    JButton start = new JButton("Advance");
    JButton stop = new JButton("Pause");
    Container buttons = new Container();

    boolean running = false;
    static boolean xTL = false;
    int count = 0;



    public LeMain(){
        frame.setSize(1200,800);
        frame.setLayout(new BorderLayout());
        frame.add(roads, BorderLayout.CENTER);
        buttons.setLayout(new GridLayout(1,2));
        buttons.add(start);
        start.addActionListener(this);
        buttons.add(stop);
        stop.addActionListener(this);
        frame.add(buttons, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        roads.initDraw(); //Initial map
        frame.repaint();


    }

    public static void main(String[] args) {
        new LeMain();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(start)){
            if (running == false){
                running = true;
                Thread thread = new Thread(this);
                thread.start();
            }
        }
        if (actionEvent.getSource().equals(stop)){
            running = false;
        }
    }

    @Override
    public void run() {
        while (running == true) {
            roads.move();
            eventHandler();
            frame.repaint();
            try {
                Thread.sleep(100);
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public void eventHandler() {
        count++;
        if (count == 60) {
            roads.lightChange();
            count = 0;
        }
        if (count % 50 == 0){
            roads.spawnCar();
        }
    }
}
