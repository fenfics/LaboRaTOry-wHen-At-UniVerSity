package Lab1202;

import java.util.*;

public class MathOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter input: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            String[] parts = input.split("\\s*([+*\\-])\\s*");
            if (parts.length < 2) {
                System.out.println("Invalid input. Please enter a valid expression.");
                continue;
            }

            List<Integer> set1 = parseSet(parts[0]);
            List<Integer> set2 = parseSet(parts[1]);
            char operation = input.replaceAll("[^+*\\-]", "").charAt(0);

            List<Integer> result = new ArrayList<>();
            switch (operation) {
                case '+':
                    result = union(set1, set2);
                    break;
                case '*':
                    result = intersection(set1, set2);
                    break;
                case '-':
                    result = difference(set1, set2);
                    break;
                default:
                    System.out.println("Invalid operation.");
                    continue;
            }

            System.out.println("Output: " + result);
        }

        scanner.close();
    }

    private static List<Integer> parseSet(String input) {
        input = input.replaceAll("[\\[\\]\\s]", "");
        String[] numbers = input.split(",");
        List<Integer> set = new ArrayList<>();
        for (String num : numbers) {
            set.add(Integer.parseInt(num));
        }
        return set;
    }

    private static List<Integer> union(List<Integer> set1, List<Integer> set2) {
        Set<Integer> unionSet = new HashSet<>(set1);
        unionSet.addAll(set2);
        return new ArrayList<>(unionSet);
    }

    private static List<Integer> intersection(List<Integer> set1, List<Integer> set2) {
        List<Integer> intersectionSet = new ArrayList<>(set1);
        intersectionSet.retainAll(set2);
        return intersectionSet;
    }

    private static List<Integer> difference(List<Integer> set1, List<Integer> set2) {
        List<Integer> differenceSet = new ArrayList<>(set1);
        differenceSet.removeAll(set2);
        return differenceSet;
    }
}
