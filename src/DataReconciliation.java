public class DataReconciliation {

    int expenseOfMonth; // Расход за месяц
    int profitOfMonth; // Доход за месяц
    int totalExpense; // Общий расход
    int totalProfit; // Общий доход
    byte error; // Кол-во ошибок (прибавляется 1, если найдена несостыковка в данных).

    // Метод по поиску суммы расходов за месяц
    public void sumExpenseOfMonth(boolean isExpense, int quantity, int unitPrice) {

        if (isExpense) {
            expenseOfMonth = quantity * unitPrice;
            totalExpense += expenseOfMonth;
        }
    }

    // Метод по поиску суммы доходов за месяц
    public void sumProfitOfMonth(boolean isExpense, int quantity, int unitPrice) {

        if (!isExpense) {
            profitOfMonth = quantity * unitPrice;
            totalProfit += profitOfMonth;
        }
    }

    // Метод для сверки данных
    public void compareData(int amount, boolean isExpense, String nameMonth) {

        if (isExpense) {
            if (totalExpense != amount) {
                System.out.println("Расходы за " + nameMonth + " не совпадают.");
                error ++;
            }
        } else {
            if (totalProfit != amount) {
                System.out.println("Доходы за " + nameMonth + " не совпадают.");
                error ++;
            }
        }
    }
}
