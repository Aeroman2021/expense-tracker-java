import java.math.BigDecimal;
import java.util.Map;

public interface ExpenseService {
    BigDecimal totalByCategory(Expense.Category category);
    Map<Expense.Category, BigDecimal> totalByAllCategories();
}
