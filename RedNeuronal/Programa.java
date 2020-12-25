import java.util.Random;
import java.util.ArrayList;

public class Programa
{
    public static void main (String[] args){
        int[][] relojEntrada = {{1,1,1,0,1,1,1},
                                 {0,0,1,0,0,1,0},
                                 {1,0,1,1,1,0,1},
                                 {1,0,1,1,0,1,1},
                                 {0,1,1,1,0,1,0},
                                 {1,1,0,1,0,1,1},
                                 {1,1,0,1,1,1,1},
                                 {1,0,1,0,0,1,0},
                                 {1,1,1,1,1,1,1},
                                 {1,1,1,1,0,1,1}};
        int[][] numeroEsperado = {{0,0,0,0},
                                  {0,0,0,1},
                                  {0,0,1,0},
                                  {0,0,1,1},
                                  {0,1,0,0},
                                  {0,1,0,1},
                                  {0,1,1,0},
                                  {0,1,1,1},
                                  {1,0,0,0},
                                  {1,0,0,1}}; 
        int numEntradas=7;
        int capa0=5;
        int capa1=5;
        int capa2=4;
        Perceptron perceptron=new Perceptron(numEntradas, capa0, capa1, capa2);
        
        //Entradas externas al perceptron
        ArrayList<Double> entradas=new ArrayList<Double>();
        entradas.add(0.0);
        entradas.add(0.0);
        entradas.add(0.0);
        entradas.add(0.0);
        entradas.add(0.0);
        entradas.add(0.0);
        entradas.add(0.0);
        
        //Salidas esperadas externas al perceptron
        ArrayList<Double> salidaEsperada=new ArrayList<Double>();
        salidaEsperada.add(0.0);
        salidaEsperada.add(0.0);
        salidaEsperada.add(0.0);
        salidaEsperada.add(0.0);
        
        //Ciclo que entrena a la red neuronal
        int totalCiclos = 8000; //existen redes neuronales para calcular cuantos cilos son necesarios xd
        for (int ciclo=1;ciclo<=totalCiclos;ciclo++){
            //Por cada ciclo, se entrena el perceptron con todos los numeros
            //Cada 200 ciclos mostramos como progresa el entrenamiento
            if (ciclo%200==0) System.out.println("Ciclo: "+ciclo);
            
            for (int i=0;i<relojEntrada[0].length;i++){
                //Entradas y salidas esperadas
                entradas.set(0, Double.valueOf(relojEntrada[i][0]));
                entradas.set(1, Double.valueOf(relojEntrada[i][1]));
                entradas.set(2, Double.valueOf(relojEntrada[i][2]));
                entradas.set(3, Double.valueOf(relojEntrada[i][3]));
                entradas.set(4, Double.valueOf(relojEntrada[i][4]));
                entradas.set(5, Double.valueOf(relojEntrada[i][5]));
                entradas.set(6, Double.valueOf(relojEntrada[i][6]));
                
                salidaEsperada.set(0, Double.valueOf(numeroEsperado[i][0]));
                salidaEsperada.set(1, Double.valueOf(numeroEsperado[i][1]));
                salidaEsperada.set(2, Double.valueOf(numeroEsperado[i][2]));
                salidaEsperada.set(3, Double.valueOf(numeroEsperado[i][3]));
                
                //Primero calcula la salida del perceptron con esas entradas
                perceptron.calculaSalida(entradas);
                
                //Luego entrena el perceptron para ajustar los pesos y umbrales
                perceptron.Entrena(entradas, salidaEsperada);
                
                //Cada 200 ciclos muestra como progresa el entrenamiento
                if (ciclo%200==0) perceptron.SalidaPerceptron(entradas, salidaEsperada);
            }
        }
        
        System.out.println ("Finaliza");
        
        
    }
}
