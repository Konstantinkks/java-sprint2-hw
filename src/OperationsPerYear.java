public class OperationsPerYear {
    int month; // Порядковый номер месяца
    int amount; // Сумма дохода/расхода за один месяц
    boolean isExpense; // Расход || доход

    public OperationsPerYear(int month, int amount, boolean isExpense) {
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
    }
}
