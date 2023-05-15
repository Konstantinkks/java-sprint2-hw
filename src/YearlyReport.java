public class YearlyReport {

    String[] nameOfTheMonth = {"Январь", "Февраль", "Март"}; // Наименование месяцев
    byte monthNumber; // Номер месяца
    byte count = 0; // Счетчик False/True
    int profit; // Доход
    int expense; // Расход
    int income; // Прибыль
    int totalExpense; // Общий расход
    int totalProfit; // Общий доход

    // Прибыль за месяц
    public void getIncome(int amount, boolean isExpense) {

        if (!isExpense) {
            profit = amount;
            count ++;
        } else {
            expense = amount;
            count ++;
        }
        if (count == 2) {
            income = profit - expense;
            System.out.println("Прибыль за " + nameOfTheMonth[monthNumber] + ": " + income + " руб.");
            monthNumber ++;
            count = 0;
        }
    }

    // Средний расход за год
    public void averageConsumption(int amount, boolean isExpense) {

        if (isExpense) {
            totalExpense += amount;
        }
    }

    // Средний доход за год
    public void averageIncome(int amount, boolean isExpense) {

        if (!isExpense) {
            totalProfit += amount;
        }
    }
}
