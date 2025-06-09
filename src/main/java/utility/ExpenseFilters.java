package utility;

import model.Expense;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Stack;
import java.util.function.Predicate;

public class ExpenseFilters {

    public static Predicate<Expense> byCategory(Expense.Category category){
        return e ->e.category() == category;
    }

    public static Predicate<Expense> amountGreaterThan(BigDecimal amount){
        return e -> e.amount().compareTo(amount) > 0;
    }

    public static Predicate<Expense> byTitleContains(String keyWord){
        return e -> e.title().toLowerCase().contains(keyWord.toLowerCase());
    }

    public static Predicate<Expense> byDate(LocalDate date){
        return e ->e.date().equals(date);
    }
}
