package service;

import core.GenericService;
import model.Expense;

import java.math.BigDecimal;
import java.util.Map;

public interface ExpenseService extends GenericService<Expense,Integer> {
    BigDecimal totalByCategory(Expense.Category category);
    Map<Expense.Category, BigDecimal> totalByAllCategories();
}
