package utility;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;

public class GenericFilter {

    public static <T,R extends Comparable<R>> Predicate<T> fieldCompare(Function<T,R> getter,
                                                                        ComparisonOperator comparisonOperator,
                                                                        R value){
        return t->{
            R fieldValue = getter.apply(t);
            if(fieldValue == null) return false;
            return switch (comparisonOperator){
                case EQUALS -> fieldValue.compareTo(value) == 0;
                case NOT_EQUALS -> fieldValue.compareTo(value) != 0;
                case GREATER_THAN -> fieldValue.compareTo(value) > 0;
                case GREATER_THAN_OR_EQUAL -> fieldValue.compareTo(value) >= 0;
                case LESS_THAN -> fieldValue.compareTo(value) < 0;
                case LESS_THAN_OR_EQUAL -> fieldValue.compareTo(value) <= 0;
            };
        };
    }

    public static <T> Predicate<T> stringCompare(
            Function<T,String> getter,
            StringOperator operator,
            String value
    ){
        return t->{
            String fieldValue = getter.apply(t);
            if (fieldValue == null) return false;
            return switch (operator){
                case EQUALS -> fieldValue.equals(value);
                case NOT_EQUALS -> !fieldValue.equals(value);
                case CONTAINS -> fieldValue.contains(value);
                case STARTS_WITH -> fieldValue.toLowerCase().startsWith(value);
                case ENDS_WITH -> fieldValue.toLowerCase().endsWith(value);
            };
        };
    }


}
