import java.util.ArrayList;
import java.util.HashMap;

public class Parser {

    HashMap<String, ArrayList> months = new HashMap<>(); // Хеш-таблица месяцев
    HashMap<Integer, ArrayList> years = new HashMap<>(); // Хеш-таблица годов
    ArrayList<OperationsPerMonth> operationsPerMonths = new ArrayList<>(); // Список транзакций за месяц
    ArrayList<OperationsPerYear> operationsPerYears = new ArrayList<>(); // Список транзакций за год

    // Парсим данные за месяц
    public void monthlyReport(ArrayList<String> file, String nameOfTheMonth) {
        for (int i = 1; i < file.size(); i++) {
            String line = file.get(i);
            String[] parts = line.split(",");
            String itemName = parts[0];
            boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int unitPrice = Integer.parseInt(parts[3]);

            OperationsPerMonth operationsPerMonth = new OperationsPerMonth(itemName, isExpense, quantity, unitPrice);
            operationsPerMonths.add(operationsPerMonth);
        }

        months.put(nameOfTheMonth, operationsPerMonths);
        operationsPerMonths = new ArrayList<>();
    }

    // Парсим данные за год
    public void yearlyReport(ArrayList<String> file, int yearNumber) {
        for (int i = 1; i < file.size(); i++) {
            String line = file.get(i);
            String[] parts = line.split(","); //item_name,is_expense,quantity,unit_price
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);

            OperationsPerYear operationsPerYear = new OperationsPerYear(month, amount, isExpense);
            operationsPerYears.add(operationsPerYear);
        }

        years.put(yearNumber, operationsPerYears);
        operationsPerYears = new ArrayList<>();
    }
}