public class MonthlyReport {

    String mostProfitableItemName; // Наименование прибыльного товара
    String mostExpensiveItemName; // Наименование затратного товара
    int sum; // Доход с одного товара
    int expense; // Расход на товар
    int maxProfit; // Максимально доходный товар
    int maxExpense; // Максимально затратный товар

    // Самый прибыльный товар
    public void mostProfitableProduct(String itemName, boolean isExpense, int quantity, int unitPrice) {

        if (!isExpense) {
            sum = quantity * unitPrice;

            if (sum > maxProfit) {
                maxProfit = sum;
                mostProfitableItemName = itemName;
            }
        }
    }

    // Самый затратный товар
    public void mostExpensiveItem(String itemName, boolean isExpense, int quantity, int unitPrice) {

        if (isExpense) {
            expense = quantity * unitPrice;

            if (expense > maxExpense) {
                maxExpense = expense;
                mostExpensiveItemName = itemName;
            }
        }
    }
}
