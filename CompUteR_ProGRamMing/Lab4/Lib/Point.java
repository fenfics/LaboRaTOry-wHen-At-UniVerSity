package Lib;
import java.lang.Math;

public class Point {
    private double x;
    private double y;
    private static int count=0;

     public Point(){
       this(0,0);
    }

    public Point(double x, double y){
        this.setX(x);
        this.setY(y);
        Point.increaseCount();
    }

    public static void increaseCount(){
        Point.count++;
    }

    public double getX(){
        return this.x;
    }

    public void setX(double x){
        this.x=x;
    }

    public double getY(){
        return this.y;
    }

    public void setY(double y){
        this.y=y;
    }

    public static int getCount(){
        return Point.count;
    }

    public static void setCount(int count){
        Point.count=count;
    }

    public double Distance(){
    return Distance(0,0);
   }

   public double Distance(double x2, double y2){
        return Math.sqrt(Math.pow(x2-this.x,2)+Math.pow(y2-this.y, 2));
   }

   public double Distance(Point p){
    return Distance(p.getX(),p.getY());
   }

   public String toString(){
    return "("+this.getX()+","+this.getY()+")";
   }
}
