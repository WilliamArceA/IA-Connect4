import java.util.Random;
import java.util.ArrayList;

public class Neurona
{
    public ArrayList<Double> pesos;
    public ArrayList<Double> nuevosPesos;
    public double umbral;
    public double nuevoUmbral;
    
    //Inicializa los pesos y umbral con un Random
    public Neurona (Random azar, int totalEntradas){
        pesos=new ArrayList<Double>();
        nuevosPesos=new ArrayList<Double>();
        for (int i=0;i<totalEntradas;i++){
            pesos.add(azar.nextDouble());
            nuevosPesos.add(0.0);
        }
        umbral=azar.nextDouble();
        nuevoUmbral=0;
    }
    
    //Calcula la salida en base a las entradas
    public double calculaSalida(ArrayList<Double> entradas){
        double valor=0.0;
        for (int i=0;i<pesos.size();i++){
            valor+=entradas.get(i)*pesos.get(i);
        }
        valor+=umbral;
        return 1/(1+Math.exp(-valor));
    }
    
    public void Actualiza(){
        for (int i=0;i<pesos.size();i++){
            pesos.set(i, nuevosPesos.get(i));
        }
        umbral=nuevoUmbral;
    }
}
