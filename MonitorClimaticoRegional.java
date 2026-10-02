/*
 * 
 */
package monitorclimaticoregional;

import java.util.Scanner;

/**
 *
 * @author Danny Nicolás Gutiérrez Mejía
 */
public class MonitorClimaticoRegional {

    public static void main(String[] args) {
        double[] rivera = new double[12];
        double[] neiva = new double[12];
        double[] campoalegre = new double[12];
        double[] copia = new double[12];

        rivera = procesarTemperaturas(true, 21, 31);
        neiva = procesarTemperaturas(true, 25, 40);
        campoalegre = procesarTemperaturas(true, 24, 38);

        int resComp = compararSubestaciones(rivera, neiva);/*Rivera entra como el competidor 1 y Neiva como el 2
           aqui sabemos con el metodo compararSubestaciones cual es mas caliente
           si gana el 1 (rivera) sería el mas caliente y si gana el 2 (neiva) sería la mas caliente*/
        System.out.println("compararSubestaciones: " + resComp);
        resComp = compararSubestaciones(neiva, rivera);
        System.out.println("compararSubestaciones: " + resComp);
        resComp = compararSubestaciones(neiva, neiva);
        System.out.println("compararSubestaciones: " + resComp);
        //mostrar arreglos
        copia = rivera;
        copia[1] = 15;
        copia[10] = 35;
        int anomalias[] = detectarAnomalias(copia);
       /* for (int j = 0; j < anomalias.length; j++) {
            if (anomalias[j]!=null)
            System.out.println(anomalias[j]);
            reporteMensual("rivera", rivera);*/

        }

        mostrarArreglo(copia);

    }

    public static double[] procesarTemperaturas(boolean tipoGenerador, double tempMin, double tempMax) {
        double[] temperaturas = new double[12];
        if (tipoGenerador == true) {
            //generación automatica
            for (int i = 0; i < temperaturas.length; i++) {
                temperaturas[i] = (Math.random() * (tempMax + 1 - tempMin) + (tempMin));

            }

        } else {
            for (int i = 0; i < temperaturas.length; i++) {
                System.out.println("digite la temperatura numero: " + i);
                temperaturas[i] = sc.nextDouble();

            }

        }
        return temperaturas;

    }

    public static void mostrarArreglo(double[] estacion) {
        for (int i = 0; i < estacion.length; i++) {
            System.out.println(estacion[i]);

        }

    }

    public static int compararSubestaciones(double[] t1, double[] t2) {
        int masAlta;
        double prom1 = promedioArreglos(t1);
        double prom2 = promedioArreglos(t2);
        if (prom1 > prom2) {
            masAlta = 1;
        } else if (prom1 < prom2) {
            masAlta = 2;
        } else {
            masAlta = 0;
        }
        return masAlta;

    }

    public static double promedioArreglos(double[] arreglo) {
        double promedio, suma = 0;
        for (int i = 0; i < arreglo.length; i++) {
            suma = suma + arreglo[i];
        }
        promedio = suma / arreglo.length;
        return promedio;

    }

    public static int[] detectarAnomalias(double[] temperaturas) {
        int[] anomalias = new int[12];
        int indAnomalias = 0;
        double prom = promedioArreglos(temperaturas);
        for (int i = 0; i < temperaturas.length; i++) {
            if (temperaturas[i] >= (prom * 1.2) || temperaturas[i] <= (prom * 0.8)) {
                anomalias[indAnomalias] = i;
                indAnomalias++;

            }

        }

        return anomalias;
    }

    public static void reporteMensual(String nombre, double[] temperaturas) {
        String[] meses = {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};
        double prom = promedioArreglos(temperaturas);
        System.out.println("informe temperaturas estacion: " + nombre);
        System.out.println("mes     promedio mes");
        for (int k = 0; k < temperaturas.length; k++) {
            System.out.println(meses[k] + "       " + temperaturas[k]);
        }
        System.out.println("mes mas calido: " + meses[mesMayorTemp(temperaturas)]);
        System.out.println("mes menos calido: " + meses[mesMenorTemp(temperaturas)]);
        System.out.println("promedio del año: " + prom);

    }

    public static int mesMayorTemp(double[] estacion) {
        int mesAlto = 0;
        double tempAlta = estacion[0];
        for (int k = 0; k < estacion.length; k++) {
            if (estacion[k] > tempAlta) {
                tempAlta = estacion[k];
                mesAlto = k;
            }
        }

        return mesAlto;
    }

    public static int mesMenorTemp(double[] estacion) {
        int mesBajo = 0;
        double tempBaja = estacion[0];
        for (int k = 0; k < estacion.length; k++) {
            if (estacion[k] < tempBaja) {
                tempBaja = estacion[k];
                mesBajo = k;
            }
        }

        return mesBajo;
    }
}
