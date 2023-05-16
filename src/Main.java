/*
Привет! Я хотел сделать супер логичное приложение, но кажется, я только все усложнил.
Класс ReportEngine, как мне кажется, перегружен.
Я оставил комментарии, надеюсь они помогут быстрее разобраться в коде.
 */

import java.util.Scanner;
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ReportEngine reportEngine = new ReportEngine(); // Экземпляр класса ReportEngine

        System.out.println("\n" + "Вас приветствует БУХГАЛТЕР 3000" +
               "\n\n" + "Чтобы начать работать с данными, их необходимо загрузить в приложение.");

        // Вывод меню и обработка запросов
        while (true) {
            printMenu();

            int command = scanner.nextInt();

            switch (command) {
                case 1 :
                    reportEngine.readingMonthlyData();
                    break;
                case 2 :
                    reportEngine.readingYearlyData();
                    break;
                case  3 :
                    reportEngine.dataReconciliation();
                    break;
                case 4 :
                    reportEngine.parsingMonthlyData();
                    break;
                case 5 :
                    reportEngine.parsingYearlyData();
                    break;
                case 0 :
                    System.out.println("Завершение приложения...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Такой команды нет.");
            }
        }
    }

    // Меню
    static void printMenu() {
        System.out.print("\n" + "Выберете нужную команду:" + "\n" +
                "1-Считать месячные отчёты." + "\n" +
                "2-Считать годовые отчёты." + "\n" +
                "3-Сверить месячные и годовые отчёты." + "\n" +
                "4-Вывести информацию обо всех месячных отчётах."+ "\n" +
                "5-Вывести информацию о годовом отчёте." + "\n" +
                "0-Завершить работу." + "\n");
    }
}
