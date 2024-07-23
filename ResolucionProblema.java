package resolucionproblema;
import java.util.Scanner;
public class ResolucionProblema {
    public static void main(String[] args) {
        Scanner tc = new Scanner(System.in);
        int opcion;
        String cargo;
        ABB bt = new ABB();
        String[] cargos = new String[7];
        int[] posiciones = new int[7];
        cargos[0] = "Gerencia";
        posiciones[0] = 0;
        cargos[1] = "Coordinador Zonal 1";
        posiciones[1] = -2;
        cargos[2] = "Coordinador Zonal 2";
        posiciones[2] = 2;
        cargos[3] = "Asistente 1";
        posiciones[3] = -3;
        cargos[4] = "Asistente 2";
        posiciones[4] = -1;
        cargos[5] = "Asistente 3";
        posiciones[5] = 1;
        cargos[6] = "Asistente 4";
        posiciones[6] = 3;
        for (int i = 0; i < cargos.length; i++) {
            bt.agregar(cargos[i], posiciones[i]);
        }
        do {
            System.out.println(" ");
            System.out.println("Menu:");
            System.out.println("1. Mostrar el arbol");
            System.out.println("2. Buscar un nodo por su cargo");
            System.out.println("3. Salir");
            System.out.print("Elige una opcion: ");
            opcion = tc.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Arbol completo InOrden:\n");
                    bt.recorridoInOrden(bt.raiz);
                    break;
                case 2:
                    tc.nextLine();  
                    System.out.print("Introduce el cargo a buscar: ");
                    cargo = tc.nextLine();
                    Nodo encontrado = bt.buscarPorCargo(cargo);
                    if (encontrado != null) {
                        System.out.println("\nNodo encontrado: " + encontrado.valor + " - posicion: " + encontrado.posicion);
                    } else {
                        System.out.println("\nNodo no encontrado");
                    }
                    break;
                case 3:
                    System.out.println("Saliendo");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 3);
        tc.close();
    }
}
class Nodo {
    String valor;
    int posicion;
    Nodo izquierda;
    Nodo derecha;
    Nodo(String value, int pos) {
        this.valor = value;
        this.posicion = pos;
        this.izquierda = null;
        this.derecha = null;
    }
}
class ABB {
    Nodo raiz;
    public void agregar(String valor, int posicion) {
        raiz = agregarRecursivo(raiz, valor, posicion);
    }
    private Nodo agregarRecursivo(Nodo current, String valor, int posicion) {
        if (current == null) {
            return new Nodo(valor, posicion);
        }
        if (posicion < current.posicion) {
            current.izquierda = agregarRecursivo(current.izquierda, valor, posicion);
        } else if (posicion > current.posicion) {
            current.derecha = agregarRecursivo(current.derecha, valor, posicion);
        }
        return current;
    }
    public void recorridoInOrden(Nodo nodo) {
        if (nodo != null) {
            recorridoInOrden(nodo.izquierda);
            System.out.println(nodo.valor + "|Posicion: " + nodo.posicion + "|");
            recorridoInOrden(nodo.derecha);
        }
    }
    public Nodo buscarPorCargo(String cargo) {
        return buscarPorCargoRecursivo(raiz, cargo);
    }
    private Nodo buscarPorCargoRecursivo(Nodo current, String cargo) {
        if (current == null) {
            return null;
        }
        if (cargo.equals(current.valor)) {
            return current;
        }
        Nodo encontradoIzquierda = buscarPorCargoRecursivo(current.izquierda, cargo);
        if (encontradoIzquierda != null) {
            return encontradoIzquierda;
        }
        Nodo encontradoDerecha = buscarPorCargoRecursivo(current.derecha, cargo);
        return encontradoDerecha;
    }
}