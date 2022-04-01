import java.util.Arrays;
import java.util.function.Function;

public class MultipleCompose<T> implements Function<T, T> {
  private Function<T, T>[] fns;

  @SafeVarargs
  public MultipleCompose(Function<T, T>... fns) {
    this.fns = fns;
  }

  @Override
  public T apply(T t) {
    T res = t;
    for (int i = 0; i < fns.length; i++) {
      res = fns[i].apply(res);
    }
    return res;
  }

  public static void main(String[] args) {
    Function<String, String> upperFirst = word -> word.length() > 0
        ? word.substring(0, 1).toUpperCase() + word.substring(1)
        : "";
    Function<String, String> upperCapital = s -> String.join(" ",
        Arrays.asList(s.split(" ")).stream().map(upperFirst).toList());
    Function<String, String> lower = s -> s.toLowerCase();
    Function<String, String> trim = s -> s.trim();

    MultipleCompose<String> capitalize = new MultipleCompose<>(trim, lower, upperCapital);

    String str = "    MARCUS AURELIUS    ";
    System.out.println(str);
    System.out.println("lower.apply(" + str + ") = " + lower.apply(str));
    System.out.println("upperCapital.apply(" + str + ") = " + upperCapital.apply(str));
    System.out.println(capitalize.apply(str));

  }

}
