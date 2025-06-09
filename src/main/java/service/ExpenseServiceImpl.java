package service;

import core.GenericServiceImpl;
import model.Expense;
import service.ExpenseService;

import java.math.BigDecimal;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ExpenseServiceImpl extends GenericServiceImpl<Expense,Integer> implements ExpenseService {

    public ExpenseServiceImpl(Function<String, Expense> fromCSVMapper) {
        super(fromCSVMapper);
    }

    @Override
    public BigDecimal totalByCategory(Expense.Category category) {
        return store.values().stream()
                .filter(e->e.category() == category)
                .map(Expense::amount)
                .reduce(BigDecimal.ZERO,BigDecimal::add);
    }

    @Override
    public Map<Expense.Category, BigDecimal> totalByAllCategories() {
        return store.values().stream()
                .collect(Collectors
                        .groupingBy(Expense::category,
                                Collectors.reducing(BigDecimal.ZERO,Expense::amount,BigDecimal::add)));
    }
}
