import java.util.ArrayList;

public class ReportEngine {

    // Экземпляры классов
    FileReader fileReader = new FileReader(); // Экземпляр FileReader
    Parser parser = new Parser(); // Экземпляр Parser
    MonthlyReport monthlyReport = new MonthlyReport(); // Экземпляр MonthlyReport
    YearlyReport yearlyReport = new YearlyReport(); // Экземпляр YearlyReport
    DataReconciliation dataReconciliation = new DataReconciliation(); // Экземпляр DataReconciliation

    // Списки
    ArrayList<String> month = new ArrayList<>(); // Месячный файл в виде списка
    ArrayList<String> year = new ArrayList<>(); // Годовой файл в виде списка
    ArrayList<OperationsPerMonth> operationsPerMonths = new ArrayList<>(); // Транзакции за месяц
    ArrayList<OperationsPerYear> operationsPerYears = new ArrayList<>(); // Транзакции за год

    // Массивы
    String[] filesOfYears = {"y.2021.csv"}; // Файл за год
    String[] filesOfMonths = {"m.202101.csv", "m.202102.csv", "m.202103.csv"}; // Файлы за 3 месяца
    Integer[] yearNumbers = {2021}; // Номер года
    String[] nameOfTheMonth = {"Январь", "Февраль", "Март"}; // Наименование месяцев

    // Переменные
    String itemName; // Наименование товара
    boolean isExpense; // Расход || доход
    int quantity; // Кол-во товара
    int unitPrice; // Цена за один товар
    int nameMonth; // Наименование месяца
    int amount; // Сумма дохода/расхода за один месяц


    // Загрузка данных за месяц
    public void readingMonthlyData() {
        for (int i = 0; i < filesOfMonths.length; i++) {
            month = fileReader.readFileContents(filesOfMonths[i]);
            parser.monthlyReport(month, nameOfTheMonth[i]);
        }
        System.out.println("Месячные данные загружены.");
    }

    // Получаем данные из месячных файлов
    public void parsingMonthlyData() {
        try {
            for (int i = 0; i < nameOfTheMonth.length; i++) {
                operationsPerMonths = parser.months.get(nameOfTheMonth[i]);

                for (int j = 0; j < operationsPerMonths.size(); j++) {
                    itemName = operationsPerMonths.get(j).itemName;
                    isExpense = operationsPerMonths.get(j).isExpense;
                    quantity = operationsPerMonths.get(j).quantity;
                    unitPrice = operationsPerMonths.get(j).unitPrice;
                    monthlyReport.mostProfitableProduct(itemName, isExpense, quantity, unitPrice);
                    monthlyReport.mostExpensiveItem(itemName, isExpense, quantity, unitPrice);

                }

                System.out.println("\n" + "Данные за " + nameOfTheMonth[i] + ":");
                System.out.println("Самый прибыльный товар: " + monthlyReport.mostProfitableItemName + "." +
                        "\n" + "Доход составил: " + monthlyReport.maxProfit + " руб.\n");
                System.out.println("Самый затратный товар: " + monthlyReport.mostExpensiveItemName + "." +
                        "\n" + "Расход составил: " + monthlyReport.maxExpense + " руб.");

                monthlyReport.expense = 0;
                monthlyReport.sum = 0;
                monthlyReport.maxExpense = 0;
                monthlyReport.maxProfit = 0;
                monthlyReport.mostExpensiveItemName = null;
                monthlyReport.mostProfitableItemName = null;

            }
        } catch (NullPointerException e) {
            System.out.println("Невозможно выполнить действие! Для начала необходимо считать файлы!");
        }
    }

    // Загрузка данных за год
    public void readingYearlyData() {
        for (int i = 0; i < filesOfYears.length; i++) {
            year = fileReader.readFileContents(filesOfYears[i]);
            parser.yearlyReport(year, yearNumbers[i]);
        }
        System.out.println("Годовые данные загружены.");
    }

    // Получаем данные из годовых файлов
    public void parsingYearlyData() {

        for (int i = 0; i < yearNumbers.length; i++) {
            operationsPerYears = parser.years.get(yearNumbers[i]);

            if (operationsPerYears != null) {

                System.out.println("Информация за " + yearNumbers[i] + " год: " + "\n");

                int j = 0;
                for (; j < operationsPerYears.size(); j++) {
                    nameMonth = operationsPerYears.get(j).month;
                    amount = operationsPerYears.get(j).amount;
                    isExpense = operationsPerYears.get(j).isExpense;
                    yearlyReport.getIncome(amount, isExpense);
                    yearlyReport.averageConsumption(amount, isExpense);
                    yearlyReport.averageIncome(amount, isExpense);
                }
                System.out.println("Средний расход за год: " + yearlyReport.totalExpense / (j / 2) + " руб.");
                System.out.println("Средний доход за год: " + yearlyReport.totalProfit / (j / 2) + " руб.");
                yearlyReport.monthNumber = 0;
                yearlyReport.totalExpense = 0;
                yearlyReport.totalProfit = 0;
            } else {
                System.out.println("Невозможно выполнить действие! Для начала необходимо считать файлы!");
            }
        }
    }

    // Метод для сверки данных
    public void dataReconciliation() {
        int j = 0;
        int count = 1;

        for (int i = 0; i < nameOfTheMonth.length; i++) {
            operationsPerMonths = parser.months.get(nameOfTheMonth[i]);

            // Проверка, если operationsPerMonths не null то выполняем действие
            if (operationsPerMonths != null){
                for (int d = 0; d < operationsPerMonths.size(); d++) {
                    isExpense = operationsPerMonths.get(d).isExpense;
                    quantity = operationsPerMonths.get(d).quantity;
                    unitPrice = operationsPerMonths.get(d).unitPrice;
                    dataReconciliation.sumExpenseOfMonth(isExpense, quantity, unitPrice);
                    dataReconciliation.sumProfitOfMonth(isExpense, quantity, unitPrice);
                }

                for (int k = 0; k < yearNumbers.length; k++) {
                    operationsPerYears = parser.years.get(yearNumbers[k]);

                    // Проверка, если operationsPerYears не null то выполняем действие
                    if(operationsPerYears != null) {

                        for (; j < operationsPerYears.size(); j++) {
                            nameMonth = operationsPerYears.get(j).month;
                            amount = operationsPerYears.get(j).amount;
                            isExpense = operationsPerYears.get(j).isExpense;
                            dataReconciliation.compareData(amount, isExpense,nameOfTheMonth[i]);

                            if (j == count) {
                                count = count + 2;
                                j ++;
                                dataReconciliation.totalExpense = 0;
                                dataReconciliation.totalProfit = 0;
                                break;
                            }
                        }
                    } else {
                        System.out.println("Невозможно выполнить действие! Для начала необходимо считать файлы!");
                        /*
                        В случае, если месячные данные считались, а годовые нет, то при запросе сверки данных
                        переменные totalProfit и totalExpense активны, так как цикл
                        for (int d = 0; d < operationsPerMonths.size(); d++) активен.
                        Для этого добавил обнуление этих переменных
                         */
                        dataReconciliation.totalProfit = 0;
                        dataReconciliation.totalExpense = 0;
                        return;
                    }
                }
            } else {
                System.out.println("Невозможно выполнить действие! Для начала необходимо считать файлы!");
                return;
            }
        }
        if (dataReconciliation.error == 0) {
            System.out.println("Сверка данных прошла успешно.");
        }
    }
}

