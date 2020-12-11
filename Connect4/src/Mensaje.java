import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Event;
import java.awt.Frame;
//import java.awt.Graphics;
import java.awt.Label;

public class Mensaje extends Dialog{
    Frame f;
    Button aceptar = new Button ("OK");
    Label texto = new Label ();
    String cad;
    Mensaje(Frame f, String s){
                super (f,"informacion");
                setSize (400,100);
                setResizable(false);
                setBackground(Color.white);
                texto.setText(s);
                add("North",texto);
                add("South",aceptar);
    }
    
    /*public void paint (Graphics g){
                g.setColor (Color.white);
                g.drawString (cad,50,50);
    }*/
    
    public boolean action (Event evt, Object obj){
        if(evt.target == aceptar){
            dispose();
            return true;
        }
        return false;
    }
}
