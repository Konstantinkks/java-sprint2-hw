public class OperationsPerMonth {
    String itemName; // Наименование товара
    boolean isExpense; // Расход || доход
    int quantity; // Кол-во товара
    int unitPrice; // Цена за один товар

    public OperationsPerMonth(String itemName, boolean isExpense, int quantity, int unitPrice) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
}
