import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<int[]> elements = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int iArea = sc.nextInt();
        int value = 0;

        // Leer elementos
        for (int i = 0; i < n; i++) {
            int[] e = new int[2]; // [valor/mm2,  maxDisponible]
            e[0] = sc.nextInt();
            e[1] = sc.nextInt();
            elements.add(e);
        }

        // Organizar elementos de mayor a menor, segun la propierdad "valor/mm2" o mejor dicho element[0]

        Collections.sort(elements, Comparator.comparingInt((int[] e) -> e[0]).reversed());

        // Bucle con maximo "elements.size()" iteraciones

        for (int j = 0; j < elements.size(); j++) {
            // Como la lista esta ordenada, en cada iteracion coje el elemento con mas valor/mm2
            int[] element = elements.get(j);

            // Solo puedes añadir todo si hay hueco suficiente, y si no lo hay solo puedes llenar lo que queda
            int countAdded = Math.min(iArea, element[1]);

            // Se quita del area restante lo que acabas de ocupar
            iArea -= countAdded;
            // Valor añadido en este paso
            value += countAdded * element[0];

            // Si no queda area, break porque de los demas elementos se añadiria 0 mm2, al no quedar area disponible
            if (iArea==0) {
                break;
            }
        }

        System.out.println(value);
    }
}