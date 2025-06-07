import java.math.BigDecimal;
import java.time.LocalDate;

public record Expense (
  int id,
  String title,
  BigDecimal amount,
  LocalDate date,
  Category category
) implements Identifiable<Integer> ,IdentifiableCSVEntity<Integer>{
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String toCSV() {
        return String.format("%d,%s,%s,%s,%s", id, title, amount, date, category);
    }

    public static Expense fromCSV(String line){
        String[] parts = line.split(",");
        return new Expense(
                Integer.parseInt(parts[0]),
                parts[1],
                new BigDecimal(parts[2]),
                LocalDate.parse(parts[3]),
                Category.valueOf(parts[4])
        );
    }

    public enum Category{
        FOOD, TRANSPORT,ENTERTAINMENT,UTILITIES, OTHER
    }

    @Override
    public String toString() {
        return String.format("[%d] %s | %s | %s | %s", id, title, amount, date, category);
    }
}

