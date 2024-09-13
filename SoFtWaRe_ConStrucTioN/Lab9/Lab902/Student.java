package lab9_2;

public class Student {
    private String name;
    private String grade;

    public Student(String n, String grade) {
        this.name = n;
        this.grade = grade;
    }

    public String getName() {
        return this.name;
    }

    public String getSubject() {
        return this.grade;
    }

    public double calGPA() {
        double sum = 0;
        int count = 0;

        for (char grades : grade.toCharArray()) {
            switch (Character.toUpperCase(grades)) {
                case 'A':
                    sum += 4.0;
                    count++;
                    break;
                case 'B':
                    sum += 3.0;
                    count++;
                    break;
                case 'C':
                    sum += 2.0;
                    count++;
                    break;
                case 'D':
                    sum += 1.0;
                    count++;
                    break;
                case 'F':
                    count++;
                    break;
                default:

                    break;
            }
        }

        if (count == 0) {
            return 0;
        } else {
            return sum / count;
        }
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

        System.out.println(name + " registered " + grade.length() + " subjects and got GPA " + calGPA());
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
