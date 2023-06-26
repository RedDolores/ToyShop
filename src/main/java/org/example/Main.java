package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        String inputLine = "10, Airplane, 0.1";
        String[] partsInputLine = inputLine.split(",");
        int id = Integer.parseInt(partsInputLine[0]);
        String name = partsInputLine[1].trim();
        double weight = Double.parseDouble(partsInputLine[2]);

        List<Integer> ids = new ArrayList();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        ids.add(4);
        ids.add(5);
        ids.add(id);

        List<String> names = new ArrayList<>();
        names.add("Doll");
        names.add("Boll");
        names.add("Car");
        names.add("Constructor");
        names.add("Bear");
        names.add(name);
        List<Double> weights = new ArrayList<>();
        weights.add(0.2);
        weights.add(0.3);
        weights.add(0.1);
        weights.add(0.1);
        weights.add(0.2);
        weights.add(weight);

        PriorityQueue<Toy> toysQueue = new PriorityQueue<>((t1, t2) ->
                Double.compare(t2.getWeight(), t1.getWeight()));
        int count = 0;
        while (count <= 3){
            for (int i = 0; i < ids.size(); i++) {
                Toy toy = new Toy(ids.get(i), names.get(i), weights.get(i));
                toysQueue.add(toy);
            }
            count++;
        }

        PriorityQueue<Toy> totalToysQueue = new PriorityQueue<>((t1, t2) ->
                Double.compare(t2.getWeight(), t1.getWeight()));
        totalToysQueue.addAll(toysQueue);

        try (PrintWriter writer = new PrintWriter
                (new FileWriter("toys.txt"))) {
            for (int i = 0; i < 10; i++) {
                Toy toy = totalToysQueue.poll();
                writer.printf("%d. %s (%.2f)\n", (i + 1), toy.getName(),
                        toy.getWeight());
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}