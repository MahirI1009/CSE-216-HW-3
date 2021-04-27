import java.util.function.BiFunction;
import java.util.*;
import java.util.function.Function;
import java.util.stream.*;
import static java.util.stream.IntStream.range;

public class HigherOrderUtils {

    //5. NamedBiFunction, static nested interface
    static interface NamedBiFunction <T, U, R> extends BiFunction <T, U, R> {
        String name();
    }

    //6. public static NamedBiFunction instances

    //(a) add, with the name "add", to perform addition of two Doubles.
    public static NamedBiFunction<Double, Double, Double> add = new NamedBiFunction<Double, Double, Double>() {
        @Override
        public String name() {return "add";}

        @Override
        public Double apply(Double aDouble, Double aDouble2) {
            return aDouble + aDouble2;
        }
    };

    //(b) subtract, with the name "diff", to perform subtraction of one Double from another.
    public static NamedBiFunction<Double, Double, Double> subtract = new NamedBiFunction<Double, Double, Double>() {
        @Override
        public String name() {return "diff";}

        @Override
        public Double apply(Double aDouble, Double aDouble2) {
            return aDouble - aDouble2;
        }
    };

    //(c) multiply, with the name "mult", to perform multiplication of two Doubles.
    public static NamedBiFunction<Double, Double, Double> multiply = new NamedBiFunction<Double, Double, Double>() {
        @Override
        public String name() {return "mult";}

        @Override
        public Double apply(Double aDouble, Double aDouble2) {
            return aDouble * aDouble2;
        }
    };

    //(d) divide, with the name "div", to divide one Double by another. This operation should throw a
    //java.lang.ArithmeticException if there is a division by zero being attempted.
    public static NamedBiFunction<Double, Double, Double> divide = new NamedBiFunction<Double, Double, Double>() {
        @Override
        public String name() {return "div";}

        @Override
        public Double apply(Double aDouble, Double aDouble2) throws ArithmeticException {
            try {
                if (aDouble2 == 0)
                    throw new ArithmeticException();
            } catch (ArithmeticException e) {
                System.out.println("Can't divide by 0!");
            }
            return Math.floor(aDouble/aDouble2);
        }
    };

    //7. zip function
    public static <T> T zip(List<T> args, List<NamedBiFunction<T, T, T>> bifunctions) throws IllegalArgumentException {
        if (args.size() < bifunctions.size()-1)
            throw new IllegalArgumentException("args.size() must be greater than or equal to bifunctions.size()");

        if (bifunctions.isEmpty())
            throw new IllegalArgumentException("bifunctions is an empty list");

        if (args.isEmpty())
            return null;

        IntStream intStream = range(1, bifunctions.size()+1);

        intStream.forEach(i -> args.set(i, bifunctions.get(i-1).apply(args.get(i-1), args.get(i))));

        return args.get(args.size()-1);
    }

    //8. static inner class FunctionComposition
    static class FunctionComposition <T, U, R> {
        BiFunction<Function<T, U>, Function<U, R>, Function<T, R>> composition = (f, g) -> g.compose(f);
    }
}