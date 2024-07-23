import java.util.ArrayList;
import java.util.Scanner;

public class Taller {

    class Nodo {
        String data1;
        String data2;
        String data3;
        double data4;
        Nodo next;
        Persona persona;

        Nodo(Persona persona) {
            this.persona = persona;
            this.data1 = persona.getCI();
            this.data2 = persona.getNombre();
            this.data3 = persona.getApellido();
            this.data4 = persona.getNota();
            this.next = null;
        }
    }

    class Persona {
        String CI;
        String nombre;
        String apellido;
        double nota;

        public Persona(String CI, String nombre, String apellido, double nota) {
            this.CI = CI;
            this.nombre = nombre;
            this.apellido = apellido;
            this.nota = nota;
        }

        public String getCI() {
            return CI;
        }

        public String getNombre() {
            return nombre;
        }

        public String getApellido() {
            return apellido;
        }

        public double getNota() {
            return nota;
        }
    }

    class listaSimple {
        public Nodo head = null;
        public Nodo tail = null;

        public void add(Persona persona) {
            Nodo newNode = new Nodo(persona);

            if (head == null) {
                head = newNode;
                tail = newNode;
                newNode.next = head;
            } else {
                tail.next = newNode;
                tail = newNode;
                tail.next = head;
            }
        }

        public void print() {
            Nodo current = head;
            if (head == null) {
                System.out.println("List is empty");
            } else {
                System.out.println("Nodes of the circular linked list: ");
                do {
                    System.out.print("[" + current.data1 + " | " + current.data2 + " | " + current.data3 + " | " + current.data4 + "] ");
                    current = current.next;
                } while (current != head);
                System.out.println();
            }
            System.out.println("Promedio: " + prom());
        }

        public double prom() {
            double suma = 0;
            int contador = 0;
            Nodo current = head;
            if (head != null) {
                do {
                    suma += current.data4;
                    contador++;
                    current = current.next;
                } while (current != head);
            }
            return contador > 0 ? suma / contador : 0;
        }

        public void delete(String key) {
            Nodo current = head, prev = null;

            if (current != null && current.data1.equals(key)) {
                if (head == tail) {
                    head = null;
                    tail = null;
                } else {
                    head = current.next;
                    tail.next = head;
                }
                System.out.println(key + " found and deleted");
                return;
            }

            do {
                prev = current;
                current = current.next;
            } while (current != head && !current.data1.equals(key));

            if (current.data1.equals(key)) {
                prev.next = current.next;
                if (current == tail) {
                    tail = prev;
                }
                System.out.println(key + " found and deleted");
            } else {
                System.out.println(key + " not found");
            }
        }
    }

    public static void main(String[] args) {
        Taller taller = new Taller();
        listaSimple lista = taller.new listaSimple();
        ArrayList<Persona> p = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int opcion;
        String dato1, dato2, dato3;
        double dato4;

        do {
            System.out.println("Opciones:");
            System.out.println("1) Insertar");
            System.out.println("2) Eliminar");
            System.out.println("3) Mostrar lista");
            System.out.println("4) Salir");
            System.out.print("Elige una opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese cedula: ");
                    dato1 = scanner.next();
                    System.out.print("Ingrese nombre: ");
                    dato2 = scanner.next();
                    System.out.print("Ingrese apellido: ");
                    dato3 = scanner.next();
                    System.out.print("Ingrese nota: ");
                    dato4 = scanner.nextDouble();
                    Persona estudiante = taller.new Persona(dato1, dato2, dato3, dato4);
                    p.add(estudiante);
                    lista.add(estudiante );
                    break;
                case 2:
                    System.out.print("Ingrese la cedula de la persona a eliminar: ");
                    dato1 = scanner.next();
                    lista.delete(dato1);
                    break;
                case 3:
                    lista.print();
                    break;
                case 4:
                    System.out.println("Saliendo");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (opcion != 4);

        scanner.close();
    }
}