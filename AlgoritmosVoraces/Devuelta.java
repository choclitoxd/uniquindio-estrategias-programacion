package AlgoritmosVoraces;

import java.util.*;

public class Devuelta {

    public static void main(String[] args) {
        int[] denominaciones = {10000, 20000, 50000, 100000};
        int[] disponibilidad = {3, 2, 1, 5};
        long monto = 180000;

        resolverCambio(denominaciones, disponibilidad, monto);
    }

    public static void resolverCambio(int[] denoms, int[] disp, long monto) {
        // Estructura para mantener la relación denominación-disponibilidad
        int n = denoms.length;
        Integer[][] billetes = new Integer[n][2];

        for (int i = 0; i < n; i++) {
            billetes[i][0] = denoms[i];
            billetes[i][1] = disp[i];
        }

        // Ordenar de mayor a menor denominación
        Arrays.sort(billetes, (a, b) -> b[0].compareTo(a[0]));

        Map<Integer, Integer> resultado = new LinkedHashMap<>();
        long restante = monto;

        // Proceso Voraz: O(d)
        for (int i = 0; i < n; i++) {
            int valor = billetes[i][0];
            int cantidadDisponible = billetes[i][1];

            if (restante >= valor) {
                int cantidadNecesaria = (int) (restante / valor);
                int cantidadAEntregar = Math.min(cantidadNecesaria, cantidadDisponible);

                if (cantidadAEntregar > 0) {
                    resultado.put(valor, cantidadAEntregar);
                    restante -= (long) cantidadAEntregar * valor;
                }
            }
        }

        // Verificación de éxito
        if (restante == 0) {
            System.out.println("Cambio entregado para $" + monto + ":");
            resultado.forEach((k, v) -> System.out.println(v + " billete(s) de $" + k));
        } else {
            System.out.println("No es posible entregar el monto exacto con los billetes disponibles.");
            System.out.println("Faltante: $" + restante);
        }
    }
}