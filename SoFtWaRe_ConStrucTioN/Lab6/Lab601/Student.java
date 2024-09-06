public class Student {
    protected String name;
    protected int id;
    protected double gpa;

    public Student(int id,String name, double gpa) {
        this.name = name;
        this.id = id;
        this.gpa = gpa;
    }

    public Student(int id,double gpa) {
        this.name = " ";
        this.id = id;
        this.gpa = gpa;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return this.name;
    }

    public int getId(){
        return this.id;
    }

    public double getGPA(){
        return this.gpa;
    }

    public String toString(){
        return "Student:\nID: "+this.getId()+"\nName: "+this.getName()+"\nGPA: "+this.getGPA();
    }
    
}
