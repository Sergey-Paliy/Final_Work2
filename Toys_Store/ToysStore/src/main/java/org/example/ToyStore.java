package org.example;

// ToyStore.java - Класс, представляющий магазин игрушек
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ToyStore {
    private List<Toy> toys;

    public ToyStore() {
        this.toys = new ArrayList<>();
    }

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public void loadToysFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int quantity = Integer.parseInt(parts[2]);
                double weight = Double.parseDouble(parts[3]);

                Toy toy = new Toy(id, name, quantity, weight);
                addToy(toy);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateWeight(int toyId, double newWeight) {
        for (Toy toy : toys) {
            if (toy.getId() == toyId) {
                toy.setWeight(newWeight);
                break;
            }
        }
    }

    public Toy drawToy() {
        double random = Math.random() * 100;
        double cumulativeWeight = 0;

        for (Toy toy : toys) {
            cumulativeWeight += toy.getWeight();
            if (random <= cumulativeWeight) {
                Toy drawnToy = new Toy(toy.getId(), toy.getName(), 1, toy.getWeight());
                toy.decreaseQuantity();
                return drawnToy;
            }
        }

        return null;
    }

    // Метод для получения списка всех игрушек
    public List<Toy> getAllToys() {
        return toys;
    }

    // Метод для получения игрушки по её id
    public Toy getToyById(int toyId) {
        for (Toy toy : toys) {
            if (toy.getId() == toyId) {
                return toy;
            }
        }
        return null;
    }

    public void saveToyToFile(Toy toy, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            String line = toy.getId() + "," + toy.getName() + "," + toy.getQuantity() + "," + toy.getWeight();
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
