
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {

    public static void main(String[] args) {
        //rand();
        //newRoad(1);
        mod(7);
        mod(5);
        mod(2);
    }



    public static void mod(int e){
        if (e % 2 != 0){
            System.out.println("Vertical Road");
        } else {
            System.out.println("Horizontal Road");
        }
    }


    public static void newRoad(int ID) {
        int r = 1;
        int c1 = 1;
        int c2 = 2;
        int c3 = 3;
        if (r == ID) {
            List<Integer> l1 = new ArrayList<Integer>();
            l1.add(c1);
            l1.add(c2);
            l1.add(c3);
            Random random = new Random();
            System.out.println(l1.get(random.nextInt(3)));
        }

    }
    private static void rand(){
        for (int i=0; i < 20; i++) {
            Random random = new Random();
            System.out.println((int)(Math.random()*3));
        }
    }


}
