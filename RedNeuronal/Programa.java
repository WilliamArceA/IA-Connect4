    import java.util.Random;
    import java.util.ArrayList;
    import java.util.Scanner;
   
    
    public class Programa
    {
        public static void main (String[] args){
        int[][] relojEntrada = {{0, 0, 0, 0, 0, 0, 0,
                                 0, 0, 0, 0, 0, 0, 0,
                                 0, 0, 0, 0, 0, 0, 0,
                                 0, 0, 0, 0, 0, 0, 0,
                                 0, 0, 0, 0, 0, 0, 0,
                                 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0,
                                 0, 0, 0, 0, 0, 0, 0,
                                 0, 0, 0, 0, 0, 0, 0,
                                 0, 0, 0, 0, 0, 0, 0,
                                 0, 0, 0, 0, 0, 0, 0,
                                 2, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0,
                                 0, 0, 0, 0, 0, 0, 0,
                                 0, 0, 0, 0, 0, 0, 0,
                                 0, 0, 0, 0, 0, 0, 0,
                                 0, 0, 0, 0, 0, 0, 0,
                                 0, 2, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0,
                                 0, 0, 0, 0, 0, 0, 0,
                                 0, 0, 0, 0, 0, 0, 0,
                                 0, 0, 0, 0, 0, 0, 0,
                                 0, 0, 0, 0, 0, 0, 0,
                                 0, 0, 2, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0,
                                 0, 0, 0, 0, 0, 0, 0,
                                 0, 0, 0, 0, 0, 0, 0,
                                 0, 0, 0, 0, 0, 0, 0,
                                 0, 0, 0, 0, 0, 0, 0,
                                 0, 0, 0, 0, 2, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0,
                                 0, 0, 0, 0, 0, 0, 0,
                                 0, 0, 0, 0, 0, 0, 0,
                                 0, 0, 0, 0, 0, 0, 0,
                                 0, 0, 0, 0, 0, 0, 0,
                                 0, 0, 0, 0, 0, 2, 0},
                                {0, 0, 0, 0, 0, 0, 0,
                                 0, 0, 0, 0, 0, 0, 0,
                                 0, 0, 0, 0, 0, 0, 0,
                                 0, 0, 0, 0, 0, 0, 0,
                                 0, 0, 0, 0, 0, 0, 0,
                                 0, 0, 0, 0, 0, 0, 2}};
        int[][] numeroEsperado = {{1, 0, 0},
                                  {1, 0, 0},
                                  {1, 0, 0},
                                  {1, 0, 0},
                                  {1, 0, 0},
                                  {1, 0, 0},
                                  {1, 0, 0}}; //el valor es 4 en binario (coloca en el centro)
        int numEntradas=42;
        int capa0=5;
        int capa1=5;
        int capa2=3;
        int numSalidas=3;
        Perceptron perceptron=new Perceptron(numEntradas, capa0, capa1, capa2);
        
        //Entradas externas al perceptron
        ArrayList<Double> entradas=new ArrayList<Double>();
        for(int i=0;i<numEntradas;i++){
            entradas.add(0.0);
        }
        
        //Salidas esperadas externas al perceptron
        ArrayList<Double> salidaEsperada=new ArrayList<Double>();
        for (int i=0;i<numSalidas;i++){
            salidaEsperada.add(0.0);
        }
        
        //Ciclo que entrena a la red neuronal
        Scanner sc=new Scanner(System.in);
        System.out.println("Introduzca cuantas iteraciones: ");
        int totalCiclos = sc.nextInt(); //existen redes neuronales para calcular cuantos cilos son necesarios xd
        System.out.println("Cada cuantas iteraciones desea revisar el aprendizaje: ");
        int m=sc.nextInt();
        for (int ciclo=1;ciclo<=totalCiclos;ciclo++){
            //Por cada ciclo, se entrena el perceptron con todos los numeros
            //Cada 200 ciclos mostramos como progresa el entrenamiento
            if (ciclo%m==0) System.out.println("Ciclo: "+ciclo);
            
            for (int i=0;i<relojEntrada.length;i++){
                //Entradas y salidas esperadas
                for(int j=0;j<numEntradas;j++){
                    entradas.set(j, Double.valueOf(relojEntrada[i][j]));
                }
                
                for(int j=0;j<numSalidas;j++){
                    salidaEsperada.set(j, Double.valueOf(numeroEsperado[i][j]));
                }
                
                //Primero calcula la salida del perceptron con esas entradas
                perceptron.calculaSalida(entradas);
                
                //Luego entrena el perceptron para ajustar los pesos y umbrales
                perceptron.Entrena(entradas, salidaEsperada);
                
                //Cada 200 ciclos muestra como progresa el entrenamiento
                if (ciclo%m==0) perceptron.SalidaPerceptron(entradas, salidaEsperada);
            }
        }
        
        perceptron.SalidaPerceptron(entradas, salidaEsperada);
        
        for (int i=0;i<perceptron.capas.size();i++){
            System.out.println("Capa "+i+": ");
            for(int j=0;j<perceptron.capas.get(i).neuronas.size();j++){
                System.out.println("    Neurona "+j+": ");
                for(int k=0;k<perceptron.capas.get(i).neuronas.get(j).pesos.size();k++){
                    System.out.println("        Peso "+k+"="+perceptron.capas.get(i).neuronas.get(j).pesos.get(k));
                }
                System.out.println("        Umbral "+j+"="+perceptron.capas.get(i).neuronas.get(j).umbral);
            }
        }
        
        System.out.println ("Finaliza");
        
        
    }
}
