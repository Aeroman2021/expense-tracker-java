import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ExpenseService service = new ExpenseService();
        Scanner scanner = new Scanner(System.in);

        service.add("Kebab", new BigDecimal("90.5"), LocalDate.now(), Expense.Category.FOOD);
        service.add("Pitza", new BigDecimal("150.5"), LocalDate.now(), Expense.Category.FOOD);
        service.add("Taxi", new BigDecimal("37.0"), LocalDate.now(), Expense.Category.TRANSPORT);
        service.add("Netflix", new BigDecimal("20.0"), LocalDate.now(), Expense.Category.ENTERTAINMENT);

        System.out.println("📋 All Expenses:");
        service.findAll().forEach(System.out::println);

        System.out.println("\n💸 Total on FOOD: " + service.totalByCategory(Expense.Category.FOOD));

        System.out.print("\n🔍 Check expenses by date (yyyy-mm-dd): ");
        LocalDate inputDate = LocalDate.parse(scanner.nextLine());
        service.findByDate(inputDate).forEach(System.out::println);
    }

}
