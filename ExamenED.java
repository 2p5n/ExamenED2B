package examened;
import java.util.Scanner;
public class ExamenED {
    class Nodo {
        String data1;
        Nodo next;
        Link link;
        Nodo(Link link) {
            this.link = link;
            this.data1 = link.getLink();
            this.next = null;
        }
    }
    class Link {
        String link;
        public Link(String link) {
            this.link = link;
        }
        public String getLink() {
            return link;
        }
    }
    class listaSimple {
        public Nodo cabeza = null;
        public Nodo cola = null;
        public void agregar(Link link) {
            Nodo nuevoNodo = new Nodo(link);

            if (cabeza == null) {
                cabeza = nuevoNodo;
                cola = nuevoNodo;
                nuevoNodo.next = cabeza;
            } else {
                cola.next = nuevoNodo;
                cola = nuevoNodo;
                cola.next = cabeza;
            }
        }
        public void imprimir() {
            Nodo actual = cabeza;
            if (cabeza == null) {
                System.out.println("La lista esta vacia");
            } else {
                System.out.println("Nodos: ");
                do {
                    System.out.print("|" + actual.data1 + " | ");
                    actual = actual.next;
                } while (actual != cabeza);
                System.out.println();
            }
        }
        public void eliminar(String key) {
            Nodo actual = cabeza, prev = null;

            if (actual != null && actual.data1.equals(key)) {
                if (cabeza == cola) {
                    cabeza = null;
                    cola = null;
                } else {
                    cabeza = actual.next;
                    cola.next = cabeza;
                }
                System.out.println(key + " encontrado y eliminado");
                return;
            }
            do {
                prev = actual;
                actual = actual.next;
            } while (actual != cabeza && !actual.data1.equals(key));

            if (actual.data1.equals(key)) {
                prev.next = actual.next;
                if (actual == cola) {
                    cola = prev;
                }
                System.out.println(key + " encontrado y eliminado");
            } else {
                System.out.println(key + " no encontrado");
            }
        }
    public void Modificar(String key, String link){
        Nodo actual = cabeza, prev = null;
        do {
                prev = actual;
                actual = actual.next;
            } while (actual != cabeza && !actual.data1.equals(key));

            if (actual.data1.equals(key)) {
                actual.data1 = link;
                System.out.println("Link nuevo ingresado");
            }
        }
    }
    public static void main(String[] args) {
        ExamenED taller = new ExamenED();
        listaSimple lista = taller.new listaSimple();
        Scanner tc = new Scanner(System.in);
        int opcion;
        String dato1, dato2;
        do {
            System.out.println("Opciones:");
            System.out.println("1) Insertar");
            System.out.println("2) Eliminar");
            System.out.println("3) Modificar");
            System.out.println("4) Mostrar lista");
            System.out.println("5) Salir");
            System.out.print("Elige una opcion: ");
            opcion = tc.nextInt();
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el link de la pagina web: ");
                    dato1 = tc.next();
                    Link estudiante = taller.new Link(dato1);
                    lista.agregar(estudiante );
                    break;
                case 2:
                    System.out.print("Ingrese el link a eliminar: ");
                    dato1 = tc.next();
                    lista.eliminar(dato1);
                    break;
                case 3:
                    System.out.print("Ingrese el link a cambiar: ");
                    dato1 = tc.next();
                    System.out.print("Ingrese el nuevo link: ");
                    dato2 = tc.next();
                    lista.Modificar(dato1, dato2);
                    break;
                case 4:
                    lista.imprimir();
                    break;
                case 5:
                    System.out.println("Saliendo");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (opcion != 5);
        tc.close();
    }
}