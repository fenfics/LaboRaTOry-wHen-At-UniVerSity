import Lib.*;

public class Main {

    public static void main(String[] args) {
        
    /*Point A = new Point();
    Point B = new Point(30, 15);
    A.setX(20);
    A.setY(50);

    System.out.println(A.toString());
    System.out.println(B.toString());
    System.out.println(A.Distance(B));
    System.out.println(A.Distance());
    System.out.println(B.Distance(10, 100));
    System.out.println(Point.getCount());*/

    MoveablePoint C = new MoveablePoint(25,60,5);
    System.out.println(C.toString());
    C.Forward();
    System.out.println(C.toString());
    C.Backward();
    System.out.println(C.toString());
    }
}
