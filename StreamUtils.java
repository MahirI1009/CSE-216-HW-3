import java.util.*;
import java.util.stream.*;

public class StreamUtils {

    //1. Capitalized Strings
    public static Collection<String> capitalized(Collection<String> strings) {
        return strings.stream()
                      .filter(Objects::nonNull)
                      .filter(s -> Character.isUpperCase(s.charAt(0)))
                      .collect(Collectors.toList());
    }

    //2. The longest String
    public static String longest(Collection<String> strings, boolean from_start) {
        return strings.stream()
                      .filter(Objects::nonNull)
                      .reduce((s1, s2) -> s1.length() == s2.length() ? from_start ? s1 : s2 : s1.length() > s2.length() ? s1 : s2)
                      .orElse("N/A");
    }

    //3. The least element
    public static <T extends Comparable<T>> T least(Collection<T> items, boolean from_start) {
        return items.stream()
                    .filter(Objects::nonNull)
                    .reduce((i1, i2) -> i1.compareTo(i2) == 0 ? from_start ? i2 : i1 : i1.compareTo(i2) > 0 ? i2 : i1)
                    .orElse(null);
    }

    //4. Flatten a map
    public static <K, V> List<String> flatten(Map<K, V> aMap) {
        return aMap.entrySet()
                   .stream()
                   .filter(Objects::nonNull)
                   .map(entry -> entry.getKey() + " -> " + entry.getValue())
                   .collect(Collectors.toList());
    }
}
