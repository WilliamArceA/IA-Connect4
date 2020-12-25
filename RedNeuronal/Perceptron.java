import java.util.Random;
import java.util.ArrayList;

public class Perceptron
{
    public ArrayList<Capa> capas;
    
    //Imprime los datos de las diferentes capas
    public void SalidaPerceptron(ArrayList<Double> entradas, ArrayList<Double> salidaEsperada){
        for (int i=0;i<entradas.size();i++){
            System.out.print(String.format("%.0f", entradas.get(i))+" ");
        }
        System.out.print("Esperada: ");
        for (int i=0;i<salidaEsperada.size();i++){
            System.out.print(String.format("%.0f", salidaEsperada.get(i))+" ");
        }
        System.out.print("Calculada: ");
        for (int i=0;i<capas.get(2).salidas.size();i++){
            if (capas.get(2).salidas.get(i)>=0.5) System.out.print(" 1 ");
            else System.out.print(" 0 ");
        }
        System.out.println(" ");
    }
    
    //Crea las capas
    public Perceptron(int numEntrada, int capa0, int capa1, int capa2){
        Random azar=new Random();
        capas=new ArrayList<Capa>();
        capas.add(new Capa(azar, capa0, numEntrada)); //Crea capa 0
        capas.add(new Capa(azar, capa1, capa0)); //Crea capa 1
        capas.add(new Capa(azar, capa2, capa1)); //Crea capa 2
    }
    
    public void calculaSalida(ArrayList<Double> entradas){
        capas.get(0).CalculaCapa(entradas);
        capas.get(1).CalculaCapa(capas.get(0).salidas);
        capas.get(2).CalculaCapa(capas.get(1).salidas);
    }
    
    public void Entrena(ArrayList<Double> entradas, ArrayList<Double> salidaEsperada){
        int capa0=capas.get(0).neuronas.size();
        int capa1=capas.get(1).neuronas.size();
        int capa2=capas.get(2).neuronas.size();
        
        //Factor de aprendizaje
        double alpha=0.4;
        
        //Procesa pesos capa 2
        for (int j=0;j<capa1;j++){
            for (int i=0;i<capa2;i++){
                double Yi=capas.get(2).salidas.get(i);
                double Si=salidaEsperada.get(i);
                double a1j=capas.get(1).salidas.get(j);
                double dE2=a1j*(Yi-Si)*Yi*(1-Yi);
                capas.get(2).neuronas.get(i).nuevosPesos.set(j, capas.get(2).neuronas.get(i).pesos.get(j)-(alpha*dE2));
            }
        }
        
        //Procesa pesos capa 1
        for (int j=0;j<capa0;j++){
            for (int k=0;k<capa1;k++){
                double acum=0;
                for (int i=0;i<capa2;i++){
                    double Yi=capas.get(2).salidas.get(i);
                    double Si=salidaEsperada.get(i);
                    double W2ki=capas.get(2).neuronas.get(i).pesos.get(k);
                    acum+=W2ki*(Yi-Si)*Yi*(1-Yi);
                }
                double a0j=capas.get(0).salidas.get(j);
                double a1k=capas.get(1).salidas.get(k);
                double dE1=a0j*a1k*(1-a1k)*acum;
                capas.get(1).neuronas.get(k).nuevosPesos.set(j, capas.get(1).neuronas.get(k).pesos.get(j)-(alpha*dE1));
            }
        }
        
        //Procesa pesos capa 0
        for (int j=0;j<entradas.size();j++){
            for (int k=0;k<capa0;k++){
                double acum1=0;
                for(int p=0;p<capa1;p++){
                    double acum2=0;
                    for (int i=0;i<capa2;i++){
                        double Yi=capas.get(2).salidas.get(i);
                        double Si=salidaEsperada.get(i);
                        double W2pi=capas.get(2).neuronas.get(i).pesos.get(p);
                        acum2+=W2pi*(Yi-Si)*Yi*(1-Yi);
                    }
                    double W1kp=capas.get(1).neuronas.get(p).pesos.get(k);
                    double a1p=capas.get(1).salidas.get(p);
                    acum1+=W1kp*a1p*(1-a1p)*acum2;
                }
                double xj=entradas.get(j);
                double a0k=capas.get(0).salidas.get(k);
                double dE0=xj*a0k*(1-a0k)*acum1;
                double W0jk=capas.get(0).neuronas.get(k).pesos.get(j);
                capas.get(0).neuronas.get(k).nuevosPesos.set(j, W0jk-(alpha*dE0));
            }
        }
        
        //Procesa umbrales capa 2
        for (int i=0;i<capa2;i++){
            double Yi=capas.get(2).salidas.get(i);
            double Si=salidaEsperada.get(i);
            double dE2=(Yi-Si)*Yi*(1-Yi);
            capas.get(2).neuronas.get(i).nuevoUmbral=capas.get(2).neuronas.get(i).umbral-(alpha*dE2);
        }
        
        //Procesa umbrales capa 1
        for (int k=0;k<capa1;k++){
            double acum=0;
            for (int i=0;i<capa2;i++){
                double Yi=capas.get(2).salidas.get(i);
                double Si=salidaEsperada.get(i);
                double W2ki=capas.get(2).neuronas.get(i).pesos.get(k);
                acum+=W2ki*(Yi-Si)*Yi*(1-Yi);
            }
            double a1k=capas.get(1).salidas.get(k);
            double dE1=a1k*(1-a1k)*acum;
            capas.get(1).neuronas.get(k).nuevoUmbral=capas.get(1).neuronas.get(k).umbral-(alpha*dE1);
        }
        
        //Procesa umbrales capa 0
        for (int k=0;k<capa0;k++){
            double acum1=0;
            for(int p=0;p<capa1;p++){
                double acum2=0;
                for (int i=0;i<capa2;i++){
                    double Yi=capas.get(2).salidas.get(i);
                    double Si=salidaEsperada.get(i);
                    double W2pi=capas.get(2).neuronas.get(i).pesos.get(p);
                    acum2+=W2pi*(Yi-Si)*Yi*(1-Yi);
                }
                double W1kp=capas.get(1).neuronas.get(p).pesos.get(k);
                double a1p=capas.get(1).salidas.get(p);
                acum1+=W1kp*a1p*(1-a1p)*acum2;
            }
            double a0k=capas.get(0).salidas.get(k);
            double dE0=a0k*(1-a0k)*acum1;
            capas.get(0).neuronas.get(k).nuevoUmbral=capas.get(0).neuronas.get(k).umbral-(alpha*dE0);
        }
        
        //Actualizar pesos
        capas.get(0).Actualiza();
        capas.get(1).Actualiza();
        capas.get(2).Actualiza();
    }
}
