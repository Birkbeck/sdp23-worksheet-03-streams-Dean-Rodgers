import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Outline {

  public static List<String> getList() {
    return List.of("hi", "bat", "ear", "hello", "iguana",
            "beaver", "winterland", "elephant", "eye", "qi");
  }

  // Loop through the words and print each one on a separate line,
  // with two spaces in front of each word.
  public static void question1() {
    List<String> words = getList();
    System.out.println("1: ");
    words.stream().forEach(w -> System.out.println("  " + w));
  }

  // Repeat this problem but without two spaces in front of each word.
  // This should be trivial if you use the same approach as the previous
  // question; the point here is to make use of a method reference.
  public static void question2() {
    List<String> words = getList();
    System.out.println("2: ");
    words.stream().forEach(System.out::println);
  }

  // For each of the following lambda expressions (see Question 5 in Worksheet 2),
  // produce the list that contains the elements of the original list
  // that satisfy the predicate defined by the lambda expression
  // (use the filter stream operation):
  //  - s -> s.length() < 4 (strings with no more than 3 characters),
  //  -  s -> s.contains("b") (strings containing "b"),
  // s -> (s.length() % 2) == 0 (strings of even length).

  public static void question3() {
    List<String> words = getList();
    System.out.println("3:");
    List<String> shortWords = words.stream()
            .filter(s -> s.length() < 4)
            .collect(Collectors.toList());
    System.out.println(shortWords);

    List<String> wordsWithB = words.stream()
            .filter(s -> s.contains("b"))
            .collect(Collectors.toList());
    System.out.println(wordsWithB);

    List<String> evenLengthWords = words.stream()
            .filter(s -> (s.length() % 2) == 0)
            .collect(Collectors.toList());
    System.out.println(evenLengthWords);
  }


  // For each of the following lambda expressions (see Question 7 in Worksheet 2),
  // produce the list that contains the results of applying the function
  // defined by the lambda expression to each element of the original list
  // (use the map stream operation):
  // - s -> s + "!",
  //  s -> s.replace("i", "eye"),
  //  s -> s.toUpperCase().

  public static void question4() {
    List<String> words = getList();
    System.out.println("4:");

    List<String> excitingWords = words.stream().
            map(s -> s + "!").
            collect(Collectors.toList());
    System.out.println(excitingWords);

    List<String> replaceWithEye = words.stream().
            map(s -> s.replace("i", "eye")).
            collect(Collectors.toList());
    System.out.println(replaceWithEye);

    List<String> upperWords = words.stream().
            map(String::toUpperCase).
            collect(Collectors.toList());
    System.out.println(upperWords);
  }


  // (*) Turn the strings in the list into uppercase, keep only the
  // ones that are shorter than four characters, and, of what is remaining,
  // keep only the ones that contain "e", and print the first result.
  // Repeat the process, except checking for a "q" instead of an "e".

  public static void question5() {
    List<String> words = getList();
    System.out.println("5a:");
    words.stream()
            .map(String::toUpperCase)
            .filter(w -> w.length() < 4)
            .filter(w -> w.contains("Q"))
            .limit(1)
            .forEach(System.out::println);

    words.stream()
            .map(String::toUpperCase)
            .filter(w -> w.length() < 4)
            .filter(w -> w.contains("Q"))
            .limit(1)
            .forEach(System.out::println);
  }


  // (** ) The above example uses lazy evaluation, but it is not easy to see
  // that it is doing so. Create a variation of the above example that shows
  // that it is doing lazy evaluation. The simplest way is to track which
  // entries are turned into upper case.

  public static void question6() {
    List<String> words = getList();
    System.out.println("6:");
    Optional<String> result = words.stream()
            .map(String::toUpperCase)
            .peek(s -> System.out.println("pre-filter1: " + s))
            .filter(s -> s.length() < 4)
            .peek(s -> System.out.println("pre-filter2: " + s))
            .filter(s -> s.contains("E"))
            .findFirst();
  }

  // (*) Produce a single String that is the result of concatenating the
  // uppercase versions of all the Strings.
  // For example, the result should be "HIHELLO...".
  // Hint: use a map operation that turns the words into upper case,
  // followed by a reduce operation that concatenates them.

  public static void question7() {
    List<String> words = getList();
    System.out.println("7:");
    String res = words.stream()
            .map(String::toUpperCase)
            .reduce("", (a,b)->(a+b));
    System.out.println(res);
  }


  // (*) Produce a single String that is the result of concatenating the
  // uppercase versions of all the Strings.
  // For example, the result should be "HIHELLO...".
  // Use a single reduce operation, without using map.

  public static void question8() {
    List<String> words = getList();
    System.out.println("8:");
    String res = words.stream()
            // note that only the second parameter needs to be transformed into upper case
            // the first parameter (the accumulated result) is already upper-case
            .reduce("", (sA, sB) -> sA + sB.toUpperCase());
    System.out.println(res);

  }

  // (*) Produce a String that is all the words concatenated together, but
  // with commas in between. For example, the result should be "hi,hello,...".
  // Note that there is no comma at the beginning, before "hi", and also no comma
  // at the end, after the last word.

  public static void question9() {
    List<String> words = getList();
    System.out.println("9:");
    String res = words.stream()
            .collect(Collectors.joining(","));
    System.out.println(res);
  }

  public static void question10(){
    System.out.println("Q10:");
    List<Dish> dishes = Dish.getMenu();
    List<Dish> result = dishes.stream()
            .filter(dish -> dish.type() == Dish.Type.MEAT)
            .limit(2)
            .collect(Collectors.toList());
    System.out.println(result);
  }

  public static void question11() {
    System.out.println("Q11:");
    long numberOfDishes = Dish.getMenu().stream()
            .mapToInt(dish -> 1)
            .reduce(0, Integer::sum);
    System.out.println(numberOfDishes);
  }

  public static void main(String... args) { // varargs alternative to String[]
    question1();
    question2();
    question3();
    question4();
    question5();
    question6();
    question7();
    question8();
    question9();
  }
}