package AlgoritmosVoraces;

import java.util.*;
/**
    * Smaller Elements Count - Divide y Vencerás
    * Basado en el enfoque de GeeksForGeeks:
    * https://www.geeksforgeeks.org/dsa/fractional-knapsack-problem/
*/
class Item {
    int id;
    double peso;
    double valor;
    double ratio; // valor/peso

    public Item(int id, double peso, double valor) {
        this.id = id;
        this.peso = peso;
        this.valor = valor;
        this.ratio = valor / peso;
    }
}

public class MochilaFraccionaria {

    static double capacidad = 520;

    public static void main(String[] args) {

        List<Item> items = Arrays.asList(
                new Item(1, 210, 15),
                new Item(2, 230, 50),
                new Item(3, 150, 20),
                new Item(4, 40, 55),
                new Item(5, 500, 300)
        );

        System.out.println("=== H1: Mayor valor ===");
        resolver(new ArrayList<>(items), "valor");

        System.out.println("\n=== H2: Menor peso ===");
        resolver(new ArrayList<>(items), "peso");

        System.out.println("\n=== H3: Mayor relación valor/peso ===");
        resolver(new ArrayList<>(items), "ratio");
    }

    public static void resolver(List<Item> items, String criterio) {

        // Ordenar según heurística
        switch (criterio) {
            case "valor":
                items.sort((a, b) -> Double.compare(b.valor, a.valor));
                break;
            case "peso":
                items.sort((a, b) -> Double.compare(a.peso, b.peso));
                break;
            case "ratio":
                items.sort((a, b) -> Double.compare(b.ratio, a.ratio));
                break;
        }

        double pesoActual = 0;
        double valorTotal = 0;

        for (Item item : items) {

            if (pesoActual + item.peso <= capacidad) {
                // Tomar completo
                pesoActual += item.peso;
                valorTotal += item.valor;
                System.out.println("Tomar completo objeto " + item.id);
            } else {
                // Tomar fracción
                double restante = capacidad - pesoActual;
                double fraccion = restante / item.peso;
                valorTotal += item.valor * fraccion;

                System.out.println("Tomar " + (fraccion * 100) + "% del objeto " + item.id);
                break;
            }
        }

        System.out.println("Valor total: " + valorTotal);
    }
}