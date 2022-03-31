import java.util.Arrays;
import java.util.function.Function;

public class CompositionExample {
  public static void main(String[] args) {
    Function<String, String> upperFirst = word -> word.substring(0, 1).toUpperCase() + word.substring(1);
    Function<String, String> upperCapital = s -> String.join(" ", Arrays.asList(s.split(" ")).stream().map(upperFirst).toList());
    Function<String, String> lower = s -> s.toLowerCase();

    Function<String, String> capitalize = upperCapital.compose(lower);

    String str = "MARCUS AURELIUS";
    System.out.println(str);
    System.out.println("lower.apply(" + str + ") = " + lower.apply(str));
    System.out.println("upperCapital.apply(" + str + ") = " + upperCapital.apply(str));
    System.out.println("capitalize.apply(" + str + ") = " + capitalize.apply(str));
  }
}