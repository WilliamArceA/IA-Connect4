public class App {
   
    public static void main (String []Args)
    {
        Juego a=new Juego();
        a.pack();
        a.setSize(640,560);
        a.setResizable(false);
        a.setLocationRelativeTo(null);
        a.setVisible(true);
    }
}
