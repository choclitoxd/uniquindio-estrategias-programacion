package ProgramacionDinamica;

import java.util.*;

/**
     * Shortest Path in Grid - Programación Dinámica
     * Basado en el enfoque de GeeksForGeeks:
     * https://www.geeksforgeeks.org/dsa/shortest-path-in-grid-with-obstacles/
*/
public class ShortestPathGrid {

    public static int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] dp = new int[n];

        // Inicializar primera celda
        dp[0] = grid[0][0];

        // Primera fila (solo se puede venir de la izquierda)
        for (int j = 1; j < n; j++) {
            dp[j] = dp[j - 1] + grid[0][j];
        }

        // Recorrer filas
        for (int i = 1; i < m; i++) {

            // Primera columna (solo se puede venir de arriba)
            dp[0] = dp[0] + grid[i][0];

            int prevDiagonal = dp[0] - grid[i][0]; 
            // Guarda el valor antiguo de dp[j-1] de la fila anterior

            for (int j = 1; j < n; j++) {

                int temp = dp[j]; // guardar dp[i-1][j] antes de sobrescribir

                dp[j] = grid[i][j] + Math.min(
                        Math.min(dp[j],       // arriba
                                 dp[j - 1]),  // izquierda
                        prevDiagonal          // diagonal
                );

                prevDiagonal = temp;
            }
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {

        int[][] grid = {
                {1, 3, 5},
                {2, 1, 2},
                {4, 3, 1}
        };

        int resultado = minCost(grid);
        System.out.println("Costo mínimo: " + resultado);
    }
}