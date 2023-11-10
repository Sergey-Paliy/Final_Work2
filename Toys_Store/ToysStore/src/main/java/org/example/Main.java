package org.example;

// Main.java - Основной класс программы
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();
        Scanner scanner = new Scanner(System.in);

        // Загрузка игрушек из файла "toys.txt"
        toyStore.loadToysFromFile("toys.txt");

        // Вывод списка всех игрушек перед изменением веса
        System.out.println("Список всех игрушек:");
        List<Toy> allToys = toyStore.getAllToys();
        for (Toy toy : allToys) {
            System.out.println(toy.getId() + ". " + toy.getName());
        }
        System.out.println("Хотите добавить новую игрушку? (yes/no)");
        String answer = scanner.nextLine();

        while (answer.equalsIgnoreCase("yes")) {
            // Запрос данных о новой игрушке
            System.out.println("Введите id игрушки:");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Введите название игрушки:");
            String name = scanner.nextLine();

            System.out.println("Введите количество игрушек:");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Введите вес игрушки:");
            double weight = scanner.nextDouble();
            scanner.nextLine();

            // Создание новой игрушки
            Toy newToy = new Toy(id, name, quantity, weight);

            // Добавление игрушки в магазин
            toyStore.addToy(newToy);

            // Запись новой игрушки в файл
            toyStore.saveToyToFile(newToy, "toys.txt");

            System.out.println("Хотите добавить еще одну игрушку? (yes/no)");
            answer = scanner.nextLine();
        }


        // Возможность обновления веса игрушек с консоли
        System.out.println("Хотите обновить вес игрушки? (yes/no)");
        String answer2 = scanner.nextLine();

        while (answer2.equalsIgnoreCase("yes")) {
            System.out.println("Введите id игрушки для обновления веса:");
            int toyId = scanner.nextInt();
            scanner.nextLine();

            // Проверка, существует ли игрушка с введенным ID
            if (toyStore.getToyById(toyId) != null) {
                System.out.println("Введите новый вес игрушки:");
                double newWeight = scanner.nextDouble();
                scanner.nextLine();

                toyStore.updateWeight(toyId, newWeight);

                System.out.println("Вес игрушки обновлен.");
            } else {
                System.out.println("Игрушка с указанным ID не найдена.");
            }

            System.out.println("Хотите обновить вес еще одной игрушки? (yes/no)");
            answer = scanner.nextLine();
        }


        // Розыгрыш игрушек
        Toy drawnToy = toyStore.drawToy();

        if (drawnToy != null) {
            System.out.println("Выиграна игрушка: " + drawnToy.getName());
            try {
                // Запись выигранной игрушки в файл "winners.txt"
                FileWriter writer = new FileWriter("winners.txt", true);
                writer.write(drawnToy.getName() + "\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Извините, все призы разыграны.");
        }

        scanner.close();
    }
}
