package Ejercicios;

 // @author Miguel Angel Franco Molina
import java.util.Scanner;



import java.util.Scanner;

public class Sistema_de_Control {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Arreglos principales (se inicializan al registrar)
        String[] placas = null;
        double[] kilometros = null;
        double[] galones = null;
        
        int opcion = 0;

        do {
            System.out.println("\n===== SISTEMA DE CONTROL DE FLOTA DE VEHÍCULOS =====");
            System.out.println("1. Registrar / Sobrescribir datos de la flota");
            System.out.println("2. Consultar rendimiento promedio general");
            System.out.println("3. Filtrar vehículos ineficientes");
            System.out.println("4. Mostrar vehículo con mayor recorrido");
            System.out.println("5. Ver Estadísticas Generales");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                opcion = 0;
            }

            switch (opcion) {
                case 1:
                    System.out.print("\nIngrese la cantidad de vehículos de la flota: ");
                    int n = Integer.parseInt(scanner.nextLine());

                    placas = new String[n];
                    kilometros = new double[n];
                    galones = new double[n];

                    for (int i = 0; i < n; i++) {
                        System.out.println("\n--- Registro del Vehículo #" + (i + 1) + " ---");
                        System.out.print("Placa: ");
                        placas[i] = scanner.nextLine();
                        kilometros[i] = leerDoublePositivo(scanner, "Kilómetros recorridos: ");
                        galones[i] = leerDoublePositivo(scanner, "Galones consumidos: ");
                    }
                    System.out.println(" Datos registrados correctamente.");
                    break;

                case 2:
                    if (placas == null) {
                        System.out.println(" Primero debe registrar los datos de la flota (Opción 1).");
                    } else {
                        double rendimientoProm = calcularRendimientoPromedio(kilometros, galones);
                        System.out.printf("%nRendimiento Promedio Global: %.2f km/galón%n", rendimientoProm);
                    }
                    break;

                case 3:
                    if (placas == null) {
                        System.out.println(" Primero debe registrar los datos de la flota (Opción 1).");
                    } else {
                        double limite = leerDoublePositivo(scanner, "Ingrese el límite de rendimiento mínimo (km/gal): ");
                        mostrarVehiculosIneficientes(placas, kilometros, galones, limite);
                    }
                    break;

                case 4:
                    if (placas == null) {
                        System.out.println(" Primero debe registrar los datos de la flota (Opción 1).");
                    } else {
                        int pos = buscarVehiculoMasRecorrido(kilometros);
                        System.out.println("\n--- VEHÍCULO CON MAYOR RECORRIDO ---");
                        System.out.printf("Placa: %s | Kilómetros: %.2f km%n", placas[pos], kilometros[pos]);
                    }
                    break;

                case 5:
                    if (placas == null) {
                        System.out.println(" Primero debe registrar los datos de la flota (Opción 1).");
                    } else {
                        generarEstadisticas(placas, kilometros, galones);
                    }
                    break;

                case 6:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (opcion != 6);

        scanner.close();
    }

    // --- MÉTODOS SOLICITADOS EN EL EXAMEN ---

    public static double leerDoublePositivo(Scanner scanner, String mensaje) {
        double valor;
        do {
            System.out.print(mensaje);
            try {
                valor = Double.parseDouble(scanner.nextLine());
            } catch (Exception e) {
                valor = -1;
            }
            if (valor <= 0) {
                System.out.println(" Error: Debe ingresar un número positivo.");
            }
        } while (valor <= 0);
        return valor;
    }

    public static double calcularRendimientoPromedio(double[] kilometros, double[] galones) {
        double totalKm = 0;
        double totalGal = 0;
        for (int i = 0; i < kilometros.length; i++) {
            totalKm += kilometros[i];
            totalGal += galones[i];
        }
        return (totalGal == 0) ? 0 : totalKm / totalGal;
    }

    public static void mostrarVehiculosIneficientes(String[] placas, double[] kilometros, double[] galones, double limiteRendimiento) {
        System.out.println("\n--- VEHÍCULOS INEFICIENTES (< " + limiteRendimiento + " km/gal) ---");
        boolean hayIneficientes = false;
        for (int i = 0; i < placas.length; i++) {
            double rendimiento = kilometros[i] / galones[i];
            if (rendimiento < limiteRendimiento) {
                System.out.printf("Placa: %-8s | Rendimiento: %.2f km/gal%n", placas[i], rendimiento);
                hayIneficientes = true;
            }
        }
        if (!hayIneficientes) {
            System.out.println("No hay vehículos por debajo de ese límite.");
        }
    }

    public static int buscarVehiculoMasRecorrido(double[] kilometros) {
        int posMayor = 0;
        for (int i = 1; i < kilometros.length; i++) {
            if (kilometros[i] > kilometros[posMayor]) {
                posMayor = i;
            }
        }
        return posMayor;
    }

    public static void generarEstadisticas(String[] placas, double[] kilometros, double[] galones) {
        System.out.println("\n================ REPORTES Y ESTADÍSTICAS ================");
        
        System.out.println("\n--- Listado General de Vehículos ---");
        double sumaKm = 0;
        double sumaGal = 0;
        int posMayorConsumo = 0;

        for (int i = 0; i < placas.length; i++) {
            System.out.printf("Placa: %-8s | Recorrido: %8.2f km | Consumo: %6.2f gal%n", 
                              placas[i], kilometros[i], galones[i]);
            sumaKm += kilometros[i];
            sumaGal += galones[i];

            if (galones[i] > galones[posMayorConsumo]) {
                posMayorConsumo = i;
            }
        }

        int posMayorKm = buscarVehiculoMasRecorrido(kilometros);

        System.out.println("\n--- Vehículo con Mayor Recorrido ---");
        System.out.printf("Placa: %s | Recorrido: %.2f km%n", placas[posMayorKm], kilometros[posMayorKm]);

        System.out.println("\n--- Vehículo con Mayor Consumo ---");
        System.out.printf("Placa: %s | Consumo: %.2f gal%n", placas[posMayorConsumo], galones[posMayorConsumo]);

        System.out.println("\n--- Promedios Generales ---");
        System.out.printf("Promedio de Recorrido: %.2f km%n", (sumaKm / placas.length));
        System.out.printf("Promedio de Consumo:   %.2f gal%n", (sumaGal / placas.length));
    }
}