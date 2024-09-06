package Lib;

public class MoveablePoint extends Point{
    private double speed;

    public double getSpeed(){
        return this.speed;
    }

    public void setSpeed(double speed){
        this.speed=speed;
    }

    public MoveablePoint(){
        this(0,0,0);
    }

    public MoveablePoint(double x, double y,double speed){
        super(x,y);//แม่ต้องอยู่บรรทัดแรก
        this.setSpeed(speed);
    }

    public void Forward(){
        this.setX(this.getX()+this.getSpeed());
        this.setY(this.getY()+this.getSpeed());
    }

    public void Backward(){
        this.setX(this.getX()-this.getSpeed());
        this.setY(this.getY()-this.getSpeed());
    }

    public String toString(){
        return "("+this.getX()+","+this.getY()+","+this.getSpeed()+")";
    }

}
