package AlgoritmosVoraces;

import java.util.*;
/**
    * Árbol de Expansión Mínima (MST) usando Prim
    * Basado en el enfoque de GeeksForGeeks:
    * https://www.geeksforgeeks.org/dsa/prims-minimum-spanning-tree-mst-greedy-algo-5/
*/
public class RedFibraOptica {

    private static final int INF = Integer.MAX_VALUE;

    public static void primMST(int[][] grafo) {
        int n = grafo.length;

        int[] key = new int[n];        // Costo mínimo para incluir nodo
        int[] parent = new int[n];     // Para reconstruir el árbol
        boolean[] inMST = new boolean[n]; // Nodo ya incluido

        Arrays.fill(key, INF);
        key[0] = 0;       // Empezamos desde el nodo 0
        parent[0] = -1;   // Nodo raíz

        for (int i = 0; i < n - 1; i++) {

            int u = minKey(key, inMST);
            inMST[u] = true;

            for (int v = 0; v < n; v++) {
                if (grafo[u][v] != 0 && !inMST[v] && grafo[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = grafo[u][v];
                }
            }
        }

        imprimirMST(parent, grafo);
    }

    private static int minKey(int[] key, boolean[] inMST) {
        int min = INF, minIndex = -1;

        for (int v = 0; v < key.length; v++) {
            if (!inMST[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    private static void imprimirMST(int[] parent, int[][] grafo) {
        int costoTotal = 0;

        System.out.println("Aristas del MST (Municipio - Municipio : Costo)");

        for (int i = 1; i < grafo.length; i++) {
            System.out.println(parent[i] + " - " + i + " : " + grafo[i][parent[i]]);
            costoTotal += grafo[i][parent[i]];
        }

        System.out.println("Costo total mínimo: " + costoTotal);
    }

    public static void main(String[] args) {

        // Ejemplo de matriz de costos entre municipios
        int[][] grafo = {
                {0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0}
        };

        primMST(grafo);
    }
}