package models;

import Exceptions.StateException;
import utils.ProductsReader;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Store {
    ProductsReader pr = new ProductsReader("products");
    Product[] products = pr.getGoods();

    private void printStore() {
        System.out.println("""
                ╔════════════════════════════════════════════════════╗
                ║               ИНФОРМАЦИЯ О ТОВАРАХ                 ║
                ╠═════╦════════════╦═════════════════════════════════╣
                ║  #  ║     ID     ║               ИМЯ               ║
                ║═════╬════════════╬═════════════════════════════════║""");

        for (int i = 0; i < products.length; i++) {
            products[i].initializeState();
            System.out.printf("║  %-2d ║  ", (i + 1));
            System.out.println(products[i].getShortInfo());
        }

        System.out.println("╚═════╩════════════╩═════════════════════════════════╝");
    }

    private void printItemInfo(int itemNum){
        System.out.println("""
                
                ╔══════════════════════════════════════════════════════════════════════════════════════╗
                ║                                 ИНФОРМАЦИЯ О ТОВАРЕ                                  ║
                ╠══════════╦═════════════════════════════════╦═══════════╦════════════╦════════════════╣
                ║    ID    ║               ИМЯ               ║   ЦЕНА    ║  СОСТОЯНИЕ ║  ПОЧЕТНЫЙ КОД  ║
                ║══════════╬═════════════════════════════════╬═══════════╬════════════╬════════════════║""");
        System.out.println(products[itemNum-1]);
        System.out.println("╚══════════╩═════════════════════════════════╩═══════════╩════════════╩════════════════╝");
    }



    public class ConsoleManager {
        private final int numberOfItems;
        private static final Scanner sc = new Scanner(System.in);

        public ConsoleManager() {
            this.numberOfItems = products.length;
        }

        public void startInteraction() throws StateException {
            printStore();
            int item = getNumber(numberOfItems, "Введите порядковый номер товара: ");
            do{
                printItemInfo(item);
                int action = getNumber(6, showActionMenu());
                processChoice(item, action);
            } while(true);
        }

        private String showActionMenu() {
            return """
                    Меню действий:
                    
                    1. Выставить на аукцион
                    2. Поднять цену
                    3. Выдать победителю
                    4. Снять с торгов
                    5. Отобразить информацию о товаре
                    6. Вернуться в список товаров
                    
                    Введите действие:""";
        }

        private void processChoice(int productNum, int choice) throws StateException {
            int item = productNum - 1;
            Product product = products[item];
            switch (choice) {
                case 1:
                    product.startSale();
                    break;
                case 2:
                    product.raisePrice();
                    break;
                case 3:
                    product.giveToTheWinner();
                    break;
                case 4:
                    product.withdraw();
                    break;
                case 5:
                    break;
                case 6:
                    startInteraction();
                    break;
                default:
                    System.out.println("Что-то пошло не так...");
            }
            pr.writeFile(products);
        }

        private int getNumber(int max, String sentence) {
            System.out.print(sentence);
            try {
                String input = sc.nextLine().strip();
                if(input.isBlank()){
                    throw new IllegalArgumentException("Число не может быть пустым!");
                }

                int num;
                try {
                    num = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Введите только цифры!");
                }

                if (num < 1 || num > max) {
                    throw new IllegalArgumentException("Число вне допустимого диапазона!!");
                }
                return num;

            } catch (InputMismatchException | IllegalArgumentException e) {
                System.out.println("Неверный ввод!!!" + e.getMessage());
                return getNumber(max, sentence);
            }
        }
    }
}
