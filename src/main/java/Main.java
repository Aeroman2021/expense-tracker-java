import model.Expense;
import service.ExpenseService;
import service.ExpenseServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ExpenseServiceImpl service = new ExpenseServiceImpl(Expense::fromCSV);

        // افزودن چند Expense نمونه
        service.save(new Expense(1, "Coffee", new BigDecimal("4.50"), LocalDate.now(), Expense.Category.FOOD));
        service.save(new Expense(2, "Metro", new BigDecimal("1.75"), LocalDate.now(), Expense.Category.TRANSPORT));
        service.save(new Expense(3, "Groceries", new BigDecimal("25.00"), LocalDate.now(), Expense.Category.FOOD));

        System.out.println("All Expense is:");
        service.findAll().forEach(System.out::println);

        BigDecimal totalFood = service.totalByCategory(Expense.Category.FOOD);
        System.out.println("💸 Total for FOOD: " + totalFood);

        System.out.println("📊 Totals by Category:");
        Map<Expense.Category, BigDecimal> totals = service.totalByAllCategories();
        totals.forEach((u,v)-> System.out.println(u + "->" + v));

        service.saveToFile("expenses.csv");
        System.out.println("saved to expenses.csv");
    }

}
