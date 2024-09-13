package lab9_2;

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("Decha", "ABI");
        Student s2 = new Student("George1", "ABC");
        Student s3 = new Student("John Young", "DB");
        Student s4 = new Student("Ted", "JAB");
        Student s5 = new Student("Tony", "CAB");

        try {
            s1.show();
        } catch (Student.IncompleteException | Student.DigitException | Student.SpaceException | Student.GradeException e) {
            System.out.println(e.getMessage());
        }

        try {
            s2.show();
        } catch (Student.IncompleteException | Student.DigitException | Student.SpaceException | Student.GradeException e) {
            System.out.println(e.getMessage());
        }

        try {
            s3.show();
        } catch (Student.IncompleteException | Student.DigitException | Student.SpaceException | Student.GradeException e) {
            System.out.println(e.getMessage());
        }

        try {
            s4.show();
        } catch (Student.IncompleteException | Student.DigitException | Student.SpaceException | Student.GradeException e) {
            System.out.println(e.getMessage());
        }

        try {
            s5.show();
        } catch (Student.IncompleteException | Student.DigitException | Student.SpaceException | Student.GradeException e) {
            System.out.println(e.getMessage());
        }

    }

}
