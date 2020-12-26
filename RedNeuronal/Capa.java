import java.util.Random;
import java.util.ArrayList;

public class Capa
{
    public ArrayList <Neurona> neuronas;
    public ArrayList<Double> salidas;
    
    public Capa(Random azar, int totalNeuronas, int totalEntradas){
        neuronas=new ArrayList<Neurona>();
        salidas=new ArrayList<Double>();
        //Genera neuronas e inicializa salidas
        for (int i=0;i<totalNeuronas;i++){
            neuronas.add(new Neurona(azar, totalEntradas));
            salidas.add(0.0);
        }
    }
    
    //Calcula salidas de cada neurona de la capa
    public void CalculaCapa(ArrayList<Double> entradas){
        for (int i=0;i<neuronas.size();i++){
            salidas.set(i, neuronas.get(i).calculaSalida(entradas));
        }
    }
    
    //Actualiza pesos y umbrales
    public void Actualiza(){
        for (int i=0;i<neuronas.size();i++){
            neuronas.get(i).Actualiza();
        }
    }
}
