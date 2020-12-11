import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.event.*;
import java.awt.Frame;
//import java.awt.Graphics;
import java.awt.Label;

public class Mensaje extends Dialog implements MouseListener {

    private static final long serialVersionUID = 1L;
    Frame f;
    Button aceptar = new Button("OK");
    Label texto = new Label();
    String cad;
    Mensaje(Frame f, String s) {

        super(f, "informacion");
        setSize(400, 100);
        setResizable(false);
        addMouseListener(this);

        aceptar.addMouseListener(this);
        setBackground(Color.white);
        texto.setText(s);
        add("North", texto);
        add("South", aceptar);
    }

    /*
     * public void paint (Graphics g){ g.setColor (Color.white); g.drawString
     * (cad,50,50); }
     */

    /*public boolean action(Event evt, Object obj) {
        if (evt.target == aceptar) {
            dispose();
            return true;
        }
        return false;
    }*/
    public void mousePressed(MouseEvent me) {
        
        if (me.getSource() == aceptar) {
            dispose();
        }
    }
    public void mouseClicked(MouseEvent me) {
        // Vacio
    }

    public void mouseEntered(MouseEvent me) {
        // Vacio
    }

    public void mouseExited(MouseEvent me) {
        // Vacio
    }

    public void mouseReleased(MouseEvent me) {
        // Vacio
    }
}
