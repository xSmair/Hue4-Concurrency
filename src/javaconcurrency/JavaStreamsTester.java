package javaconcurrency;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class JavaStreamsTester {

    static List<Integer> numbers = new ArrayList<>();
    static List<String> strings = new ArrayList<>();

    public static void main(String[] args) {
        numbers.add(1);
        numbers.add(6);
        numbers.add(56);
        numbers.add(89);
        numbers.add(17);
        numbers.add(22);
        numbers.add(35);

        strings.add("lala");
        strings.add("blaaaa");
        strings.add("hihihihihi");
        strings.add("du");
        strings.add("i'm");
        strings.add("");
        strings.add("bubls");
        System.out.println(getCountEmptyString(strings));
        System.out.println(getCountLengthThree(strings));
        System.out.println(getMergedstring(strings, ";"));
        System.out.println(deleteEmptyStringsUsingJavaSeven(strings));
        System.out.println(getSquares(numbers));
        System.out.println(getMax(numbers));
        System.out.println(getMin(numbers));
        System.out.println(getSum(numbers));
        System.out.println(getAverage(numbers));

    }

    private static int getCountEmptyString(List<String> strings) {
        return strings.stream().filter(s -> s.equals("")).collect(Collectors.toList()).size();
    }

    private static int getCountLengthThree(List<String> strings) {
        return strings.stream().filter(s -> s.length() == 3).collect(Collectors.toList()).size();
    }

    private static List<String> deleteEmptyStringsUsingJavaSeven(List<String> strings) {
        return strings.stream().filter(s -> !s.equals("")).collect(Collectors.toList());
    }

    private static String getMergedstring(List<String> strings, String seperator) {
        return strings.stream().reduce((s, t) -> {
            return s + seperator + t;
        }).get();
    }

    private static List<Integer> getSquares(List<Integer> numbers) {
        return numbers.stream().map(s -> s * s).collect(Collectors.toList());
    }

    private static int getMax(List<Integer> numbers) {
        return numbers.stream().max((a, b) -> {
            return a - b;
        }).get();
    }

    private static int getMin(List<Integer> numbers) {
        return numbers.stream().min((a, b) -> {
            return a - b;
        }
        ).get();
    }

    private static int getSum(List<Integer> numbers) {
        return numbers.stream().reduce(0, (a, b) -> a+b);
    }

    private static int getAverage(List<Integer> numbers) {
        return numbers.stream().reduce(0, (a, b) -> (a+b))/numbers.size();
    }
}
