package lab9_2;

public class Student {
    private String name;
    private String grade;
    private static int count;
    private double sum;

    public Student(String n, String grade) {
        this.name = n;
        this.grade = grade;
        this.sum = 0.0;
        this.count = 0;
    }

    public String getName() {
        return this.name;
    }

    public String getSubject() {
        return this.grade;
    }

    public static double calGPA(String grade) {
        double sum = 0;
        int count = 0;

        

         

        

    
        return count == 0 ? 0 : sum / count;
    }

    public void show() throws IncompleteException, DigitException, SpaceException, GradeException {
        if (name.matches(".*\\d.*")) {
            throw new DigitException("(digit is not allowed in name) cannot display.");
        }

        if (name.contains(" ")) {
            throw new SpaceException("(space is not allowed in name) cannot display.");
        }

        if (!grade.matches("[ABCDEF]+")) {
            throw new GradeException("(grade must be A B C D E F) cannot display.");
        }

        if (grade.equals("I")) {
            throw new GradeException("(grade I is incomplete) cannot display.");
        }

        double gpa = calGPA(grade);

        System.out.println(name + " registered " + count + " subjects and got GPA " + gpa);
    }

    class IncompleteException extends Exception {
        public IncompleteException(String message) {
            super(message);
        }
    }

    class DigitException extends Exception {
        public DigitException(String message) {
            super(message);
        }
    }

    class SpaceException extends Exception {
        public SpaceException(String message) {
            super(message);
        }
    }

    class GradeException extends Exception {
        public GradeException(String message) {
            super(message);
        }
    }
}