import java.util.Arrays;
import java.util.function.Function;
import java.util.*;

public class RecursiveCompose<T> implements Function<T, T> {
  private ArrayList<Function<T, T>> fns;

  @SafeVarargs
  public RecursiveCompose(Function<T, T>... fns) {
    this.fns = new ArrayList<>(Arrays.asList(fns));
  }

  @Override
  public T apply(T t) {
    if (fns.size() == 0)
      return null;
    Function<T, T> fn = fns.remove(0);
    T res = fn.apply(t);
    if (fns.size() == 0)
      return res;
    return apply(res);
  }

  public static void main(String[] args) {
    Function<String, String> upperFirst = word -> word.length() > 0 ? word.substring(0, 1).toUpperCase() + word.substring(1) : "";
    Function<String, String> upperCapital = s -> String.join(" ", Arrays.asList(s.split(" ")).stream().map(upperFirst).toList());
    Function<String, String> lower = s -> s.toLowerCase();
    Function<String, String> trim = s -> s.trim();

    String str = "    MARCUS AURELIUS    ";
    System.out.println(str);
    System.out.println("lower.apply(" + str + ") = " + lower.apply(str));
    System.out.println("upperCapital.apply(" + str + ") = " + upperCapital.apply(str));

    RecursiveCompose<String> capitalize = new RecursiveCompose<>(trim, lower, upperCapital);
    System.out.println("capitalize.apply(" + str + ") = " + capitalize.apply(str));
  }
}
