
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;

public class Juego extends Frame implements MouseListener {
    JButton Nivel1 = new JButton("Nivel 1");
    JButton Nivel2 = new JButton("Nivel 2");
    JButton Nivel3 = new JButton("Nivel 3");
    JButton Salir = new JButton("Salir");
    int jm, im;
    int Turno;
    int[][] matriz;
    boolean ganador = false, jugable = false, prioridad = false, pr = false, hacer = false;
    int ig, jg;
    Panel PanelPrincipal = new Panel();

    Juego() {
        super("Conecta cuatro");
        matriz = new int[9][10];
        im = 9;
        jm = 10;
        setLayout(new GridLayout(20, 5));
        addMouseListener(this);
        Nivel1.addMouseListener(this);
        Nivel2.addMouseListener(this);
        Nivel3.addMouseListener(this);
        Salir.addMouseListener(this);
        PanelPrincipal.add(Nivel1);
        PanelPrincipal.add(Nivel2);
        PanelPrincipal.add(Nivel3);
        PanelPrincipal.add(Salir);
        add(PanelPrincipal);
        /*addWindowListener(new WindowAdapter() {
            public void cerrarVentana(WindowEvent we) {
                System.exit(0);
            }
        });*/
    }

    public boolean getGanador() {
        return ganador;
    }

    public boolean getPrioridad() {
        return prioridad;
    }

    public void colocar(int fila) {
        Thread tiempo = new Thread();
        Graphics g = getGraphics();

        g.setColor(Color.red);
        int c = 0;
        int r = ThreadLocalRandom.current().nextInt(0, jm);
        if (jugable == true) {
            if (fila < jm) {
                if (ganador == false) {
                    if (matriz[0][fila] == 0) {
                        if (matriz[im - 1][fila] == 0) {
                            matriz[im - 1][fila] = 5;
                            g.fillOval(((fila) * 90) + 20, ((im) * 65) + 100, 60, 60);
                            revision(5, im - 1, fila);
                            int b = encontradaPrioridad(5, im - 1, fila);
                            if (ganador != true) {
                                if (prioridad == false) {
                                    TurnoMaquina(r);
                                } else {
                                    if (hacer == true) {
                                        if (matriz[ig][jg] == 0) {
                                            TurnoMaquina(jg);
                                            hacer = false;
                                            ig = -3;
                                            jg = -3;
                                        } else {
                                            hacer = false;
                                            ig = -3;
                                            jg = -3;
                                            if (b != -2) {
                                                TurnoMaquina(b);
                                            } else {
                                                TurnoMaquina(r);
                                            }
                                        }
                                    } else {
                                        hacer = false;
                                        ig = -3;
                                        jg = -3;
                                        if (b != -2) {
                                            TurnoMaquina(b);
                                        } else {
                                            TurnoMaquina(r);
                                        }
                                    }
                                }
                            } else {
                                Mensaje dig = new Mensaje(this, "ganador persona");

                                dig.setLocation(300, 10);
                                dig.setVisible(true);
                                try {
                                    tiempo.wait(1000);
                                    // tiempo.sleep(1000);
                                } catch (InterruptedException e) {
                                }
                            }
                        } else {
                            while (matriz[c + 1][fila] == 0) {
                                c++;
                            }
                            matriz[c][fila] = 5;
                            g.fillOval((fila * 90) + 20, ((c + 1) * 65) + 100, 60, 60);
                            revision(5, c, fila);
                            int b = encontradaPrioridad(5, c, fila);
                            if (ganador != true) {
                                if (prioridad == false) {
                                    TurnoMaquina(r);
                                } else {
                                    if (hacer == true) {
                                        if (matriz[ig][jg] == 0) {
                                            TurnoMaquina(jg);
                                            ig = -3;
                                            jg = -3;
                                            hacer = false;
                                        } else {
                                            hacer = false;
                                            ig = -3;
                                            jg = -3;
                                            if (b != -2) {
                                                TurnoMaquina(b);
                                            } else {
                                                TurnoMaquina(r);
                                            }
                                        }
                                    } else {
                                        hacer = false;
                                        ig = -3;
                                        jg = -3;
                                        if (b != -2) {
                                            TurnoMaquina(b);
                                        } else {
                                            TurnoMaquina(r);
                                        }
                                    }
                                }
                            } else {
                                Mensaje dig = new Mensaje(this, "ganador persona");

                                dig.setLocation(300, 10);
                                dig.setVisible(true);
                                try {
                                    tiempo.wait(1000);
                                    // tiempo.sleep(1000);
                                } catch (InterruptedException e) {
                                }
                            }
                        }
                    } else {
                        Mensaje dig = new Mensaje(this, "no se puede colocar");

                        dig.setLocation(300, 10);
                        dig.setVisible(true);
                    }
                } else {
                    Mensaje dig = new Mensaje(this, "buen juego");

                    dig.setLocation(300, 10);
                    dig.setVisible(true);
                }
            } else {
                Mensaje dig = new Mensaje(this, "no se puede colocar");

                dig.setLocation(300, 10);
                dig.setVisible(true);
            }
        } else {
            Mensaje dig = new Mensaje(this, "no jugable aun");

            dig.setLocation(300, 10);
            dig.setVisible(true);
        }

    }

    public void TurnoMaquina(int fila) {
        int c = 0;
        Graphics g = getGraphics();
        Thread tiempo = new Thread();
        g.setColor(Color.blue);
        if (matriz[0][fila] == 0) {
            if (matriz[im - 1][fila] == 0) {
                matriz[im - 1][fila] = 4;
                g.fillOval(((fila) * 90) + 20, ((im) * 65) + 100, 60, 60);
                revision(4, im - 1, fila);
                if (encontradaPrioridad(4, im - 1, fila) != -2) {
                    hacer = true;
                    ig = im - 1;
                    jg = encontradaPrioridad(4, im - 1, fila);
                }
                if (ganador == true) {

                    Mensaje dig = new Mensaje(this, "ganador pc");

                    dig.setLocation(300, 10);
                    dig.setVisible(true);
                    try {
                        tiempo.wait(1000);
                        // tiempo.sleep(1000);
                    } catch (InterruptedException e) {
                    }

                }
            } else {
                while (matriz[c + 1][fila] == 0) {
                    c++;
                }
                matriz[c][fila] = 4;
                g.fillOval((fila * 90) + 20, ((c + 1) * 65) + 100, 60, 60);
                revision(4, c, fila);
                if (encontradaPrioridad(4, c, fila) != -2) {
                    hacer = true;
                    ig = c;
                    jg = encontradaPrioridad(4, c, fila);
                }
                if (ganador == true) {
                    Mensaje dig = new Mensaje(this, "ganador pc");

                    dig.setLocation(300, 10);
                    dig.setVisible(true);
                    try {
                        tiempo.wait(1000);
                        // tiempo.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                }
            }
        } else {
            if (fila == im - 1) {
                TurnoMaquina(0);
            } else {
                TurnoMaquina(fila + 1);
            }
        }
    }

    private void revision(int rev, int i, int j) {
        // las variables son solo comprobantes, los que tienen r son de horizontal y
        // vertical y los que tienen D son diagonales.
        int ar = 0, br = 0, cr = 0, dr = 0, aD = 0, bD = 0, cD = 0, dD = 0, aR = 0, bR = 0, cR = 0, dR = 0, eR = 0,
                fR = 0;
        if ((j + 3) < jm) {
            ar = (matriz[i][j + 1] + matriz[i][j + 2] + matriz[i][j + 3]) + rev;
        }

        if ((j - 3) > -1) {
            br = (matriz[i][j - 1] + matriz[i][j - 2] + matriz[i][j - 3]) + rev;
        }

        if ((i + 3) < im) {
            cr = (matriz[i + 1][j] + matriz[i + 2][j] + matriz[i + 3][j]) + rev;
        }

        if (((j - 1) > -1) && ((j + 2) < jm)) {
            aR = matriz[i][j + 1] + matriz[i][j + 2] + matriz[i][j - 1] + rev;
        }

        if (((j - 2) > -1) && ((j + 1) < jm)) {
            bR = matriz[i][j + 1] + matriz[i][j - 2] + matriz[i][j - 1] + rev;
        }

        if (((j - 1) > -1) && ((j + 2) < jm) && ((i + 1) < im) && ((i - 2) > -1)) {
            cR = matriz[i + 1][j - 1] + matriz[i - 1][j + 1] + matriz[i - 2][j + 2] + rev;
        }

        if (((j - 2) > -1) && ((j + 1) < jm) && ((i + 2) < im) && ((i - 1) > -1)) {
            dR = matriz[i - 1][j + 1] + matriz[i + 1][j - 1] + matriz[i + 2][j - 2] + rev;
        }

        if (((j - 1) > -1) && ((j + 2) < jm) && ((i - 1) > -1) && ((i + 2) < im)) {
            eR = matriz[i - 1][j - 1] + matriz[i + 1][j + 1] + matriz[i + 2][j + 2] + rev;
        }

        if (((j - 2) > -1) && ((j + 1) < jm) && ((i - 2) > -1) && ((i + 1) < im)) {
            fR = matriz[i - 1][j - 1] + matriz[i + 1][j + 1] + matriz[i - 2][j - 2] + rev;
        }

        if (((i - 3) > -1) && ((j + 3) < jm)) {
            aD = matriz[i - 1][j + 1] + matriz[i - 2][j + 2] + matriz[i - 3][j + 3] + rev;
        }

        if (((i + 3) < im) && ((j + 3) < jm)) {
            bD = matriz[i + 1][j + 1] + matriz[i + 2][j + 2] + matriz[i + 3][j + 3] + rev;
        }

        if (((i - 3) > -1) && ((j - 3) > -1)) {
            cD = matriz[i - 1][j - 1] + matriz[i - 2][j - 2] + matriz[i - 3][j - 3] + rev;
        }

        if (((i + 3) < im) && ((j - 3) > -1)) {
            dD = matriz[i + 1][j - 1] + matriz[i + 2][j - 2] + matriz[i + 3][j - 3] + rev;
        }
        // como se suman los nùmeros y cada jugador tiene numeros diferente, pregunta si
        // hay un multiplo de 4 de su propio numero
        // si hay entonces ya tiene 4
        if ((((ar) == 4 * rev) == true) || (((br) == 4 * rev) == true) || (((cr) == 4 * rev) == true)
                || (((dr) == 4 * rev) == true) || (((aD) == 4 * rev) == true) || (((bD) == 4 * rev) == true)
                || (((cD) == 4 * rev) == true) || (((dD) == 4 * rev) == true) || (((aR) == 4 * rev) == true)
                || (((bR) == 4 * rev) == true) || (((cR) == 4 * rev) == true) || (((dR) == 4 * rev) == true)
                || (((eR) == 4 * rev) == true) || (((fR) == 4 * rev) == true)) {
            ganador = true;
        } else {
            ganador = false;
        }
    }

    public void paint(Graphics g) {
        int x;
        int y;
        g.setColor(Color.CYAN);
        g.fillRect(1, 1, 950, 790);

        g.setColor(Color.white);
        g.fillRect(1, 1, 950, 55);

        for (x = 0; x < 9; x++)
            for (y = 1; y < 10; y++) {
                g.fillOval((x * 90) + 20, (y * 65) + 100, 60, 60);
                matriz[x][y] = 0;
            }
    }

    public void Nivel1() {
        Graphics g = getGraphics();
        int x;
        int y;

        ig = -3;
        jg = -3;
        hacer = false;
        ganador = false;
        jugable = true;
        prioridad = false;

        System.out.println("Nivel1");
        g.setColor(Color.cyan);
        g.fillRect(1, 1, 950, 790);
        matriz = new int[7][8];
        im = 7;
        jm = 8;
        g.setColor(Color.white);
        g.fillRect(1, 1, 950, 50);
        for (int x1 = 7; x1 > 0; x1--) {
            g.fillOval((7 * 90) + 20, (x1 * 65) + 100, 60, 60);
        }

        for (x = 0; x < 8; x++) {
            for (y = 1; y < 8; y++) {
                g.fillOval((x * 90) + 20, (y * 65) + 100, 60, 60);
                matriz[x][y] = 0;
            }
        }

    }

    public void Nivel2() {
        Graphics g = getGraphics();
        int x;
        int y;
        ig = -3;
        jg = -3;
        hacer = false;
        ganador = false;
        jugable = true;
        prioridad = true;

        System.out.println("Nivel2");
        g.setColor(Color.cyan);
        g.fillRect(1, 1, 950, 790);

        g.setColor(Color.white);
        g.fillRect(1, 1, 950, 50);
        matriz = new int[8][9];
        im = 8;
        jm = 9;
        for (int x1 = 8; x1 > 0; x1--) {
            g.fillOval((8 * 90) + 20, (x1 * 65) + 100, 60, 60);
        }
        for (x = 0; x < 8; x++) {
            for (y = 1; y < 9; y++) {
                g.fillOval((x * 90) + 20, (y * 65) + 100, 60, 60);
                matriz[x][y] = 0;
            }
        }
    }

    public void Nivel3() {
        Graphics g = getGraphics();
        int x;
        int y;
        ig = -3;
        jg = -3;
        hacer = false;
        ganador = false;
        jugable = true;
        prioridad = true;
        System.out.println("Nivel3");
        g.setColor(Color.cyan);
        g.fillRect(1, 1, 950, 690);

        g.setColor(Color.white);
        g.fillRect(1, 1, 950, 50);
        matriz = new int[9][10];
        im = 9;
        jm = 10;
        for (int x1 = 9; x1 > 0; x1--) {
            g.fillOval((9 * 90) + 20, (x1 * 65) + 100, 60, 60);
        }
        for (x = 0; x < 9; x++) {
            for (y = 1; y < 10; y++) {
                g.fillOval((x * 90) + 20, (y * 65) + 100, 60, 60);
                matriz[x][y] = 0;
            }
        }
    }
    public void Salir() {
        
       dispose();
    }
    public void mousePressed(MouseEvent me) {
        int z;
        z = me.getX();
        if (jugable) {
            z = (z - 10) / 90;
            colocar(z);
        }
        if (me.getSource() == Nivel1) {
            Nivel1();
        }
        if (me.getSource() == Nivel2) {
            Nivel2();
        }
        if (me.getSource() == Nivel3) {
            Nivel3();
        }
        if (me.getSource() == Salir) {
            Salir();
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

    protected int encontradaPrioridad(int rev1, int i1, int j1) {
        // las variables son solo comprobantes, los que tienen r son de horizontal y
        // vertical y los que tienen D son diagonales.
        int res = -2;
        boolean encontrado = false;
        if (((j1 + 3) < jm) && (encontrado == false)) {
            if (((matriz[i1][j1 + 1] + matriz[i1][j1 + 2] + rev1) == 3 * rev1) && (encontrado == false)) {
                if (matriz[i1][j1 + 3] == 0) {
                    if ((i1 + 1) < im) {
                        if (matriz[i1 + 1][j1 + 3] != 0) {
                            res = j1 + 3;
                            encontrado = true;
                        }
                    } else {
                        res = j1 + 3;
                        encontrado = true;
                    }
                } else {
                    if ((j1 - 1) > -1) {
                        if (matriz[i1][j1 - 1] == 0) {
                            if ((i1 + 1) < im) {
                                if (matriz[i1 + 1][j1 - 1] != 0) {
                                    res = j1 - 1;
                                    encontrado = true;
                                }
                            } else {
                                res = j1 - 1;
                                encontrado = true;
                            }
                        }
                    }
                }
            }

            if (((matriz[i1][j1 + 1] + matriz[i1][j1 + 3] + rev1) == 3 * rev1) && (encontrado == false)) {
                if (matriz[i1][j1 + 2] == 0) {
                    if ((i1 + 1) < im) {
                        if (matriz[i1 + 1][j1 + 2] != 0) {
                            res = j1 + 2;
                            encontrado = true;
                        }
                    } else {
                        res = j1 + 2;
                        encontrado = true;
                    }
                }
            }

            if (((matriz[i1][j1 + 2] + matriz[i1][j1 + 3] + rev1) == 3 * rev1) && (encontrado == false)) {
                if (matriz[i1][j1 + 1] == 0) {
                    if ((i1 + 1) < im) {
                        if (matriz[i1 + 1][j1 + 1] != 0) {
                            res = j1 + 1;
                            encontrado = true;
                        }
                    } else {
                        res = j1 + 1;
                        encontrado = true;
                    }
                }
            }
        }

        if ((encontrado == false) && (((j1 + 2) < jm) && ((j1 - 1) > -1))) {
            if (((matriz[i1][j1 + 1] + matriz[i1][j1 - 1] + rev1) == 3 * rev1) && (encontrado == false)) {
                if (matriz[i1][j1 + 2] == 0) {
                    if ((i1 + 1) < im) {
                        if (matriz[i1 + 1][j1 + 2] != 0) {
                            res = j1 + 2;
                            encontrado = true;
                        }
                    } else {
                        res = j1 + 2;
                        encontrado = true;
                    }
                } else {
                    if ((j1 - 2) > -1) {
                        if (matriz[i1][j1 - 2] == 0) {
                            if ((i1 + 1) < im) {
                                if (matriz[i1 + 1][j1 - 2] != 0) {
                                    res = j1 - 2;
                                    encontrado = true;
                                }
                            } else {
                                res = j1 - 2;
                                encontrado = true;
                            }
                        }
                    }
                }
            }

            if (((matriz[i1][j1 - 1] + matriz[i1][j1 + 2] + rev1) == 3 * rev1) && (encontrado == false)) {
                if (matriz[i1][j1 + 1] == 0) {
                    if ((i1 + 1) < im) {
                        if (matriz[i1 + 1][j1 + 1] != 0) {
                            res = j1 + 1;
                            encontrado = true;
                        }
                    } else {
                        res = j1 + 1;
                        encontrado = true;
                    }
                }
            }

            if (((matriz[i1][j1 + 1] + matriz[i1][j1 + 2] + rev1) == 3 * rev1) && (encontrado == false)
                    && (matriz[i1][j1 - 1] == 0) && (((j1 + 3) < jm) != true)) {
                if (matriz[i1][j1 + 1] == 0) {
                    if ((i1 + 1) < im) {
                        if (matriz[i1 + 1][j1 + 1] != 0) {
                            res = j1 + 1;
                            encontrado = true;
                        }
                    } else {
                        res = j1 + 1;
                        encontrado = true;
                    }
                }

            }
        }

        if (((j1 - 3) > -1) && (encontrado == false)) {
            if (((matriz[i1][j1 - 1] + matriz[i1][j1 - 2] + rev1) == 3 * rev1) && (encontrado == false)) {
                if (matriz[i1][j1 - 3] == 0) {
                    if ((i1 + 1) < im) {
                        if (matriz[i1 + 1][j1 - 3] != 0) {
                            res = j1 - 3;
                            encontrado = true;
                        }
                    } else {
                        res = j1 - 3;
                        encontrado = true;
                    }
                } else {
                    if ((j1 + 1) < jm) {
                        if (matriz[i1][j1 + 1] == 0) {
                            if ((i1 + 1) < im) {
                                if (matriz[i1 + 1][j1 + 1] != 0) {
                                    res = j1 + 1;
                                    encontrado = true;
                                }
                            } else {
                                res = j1 + 1;
                                encontrado = true;
                            }
                        }
                    }
                }
            }

            if (((matriz[i1][j1 - 1] + matriz[i1][j1 - 3] + rev1) == 3 * rev1) && (encontrado == false)) {
                if (matriz[i1][j1 - 2] == 0) {
                    if ((i1 + 1) < im) {
                        if (matriz[i1 + 1][j1 - 2] != 0) {
                            res = j1 - 2;
                            encontrado = true;
                        }
                    } else {
                        res = j1 - 2;
                        encontrado = true;
                    }
                }
            }

            if (((matriz[i1][j1 - 2] + matriz[i1][j1 - 3] + rev1) == 3 * rev1) && (encontrado == false)) {
                if (matriz[i1][j1 - 1] == 0) {
                    if ((i1 + 1) < im) {
                        if (matriz[i1 + 1][j1 - 1] != 0) {
                            res = j1 - 1;
                            encontrado = true;
                        }
                    } else {
                        res = j1 - 1;
                        encontrado = true;
                    }
                }
            }
        }

        if ((encontrado == false) && (((j1 + 1) < jm) && ((j1 - 2) > -1))) {
            if (((matriz[i1][j1 - 1] + matriz[i1][j1 + 1] + rev1) == 3 * rev1) && (encontrado == false)) {
                if (matriz[i1][j1 - 2] == 0) {
                    if ((i1 + 1) < im) {
                        if (matriz[i1 + 1][j1 - 2] != 0) {
                            res = j1 - 2;
                            encontrado = true;
                        }
                    } else {
                        res = j1 - 2;
                        encontrado = true;
                    }
                } else {
                    if ((j1 + 2) < im) {
                        if (matriz[i1][j1 + 2] == 0) {
                            if ((i1 + 1) < im) {
                                if (matriz[i1 + 1][j1 + 2] != 0) {
                                    res = j1 + 2;
                                    encontrado = true;
                                }
                            } else {
                                res = j1 + 2;
                                encontrado = true;
                            }
                        }
                    }
                }
            }

            if (((matriz[i1][j1 + 1] + matriz[i1][j1 - 2] + rev1) == 3 * rev1) && (encontrado == false)) {
                if (matriz[i1][j1 - 1] == 0) {
                    if ((i1 + 1) < im) {
                        if (matriz[i1 + 1][j1 - 1] != 0) {
                            res = j1 - 1;
                            encontrado = true;
                        }
                    } else {
                        res = j1 - 1;
                        encontrado = true;
                    }
                }
            }

            if (((matriz[i1][j1 - 1] + matriz[i1][j1 - 2] + rev1) == 3 * rev1) && (encontrado == false)
                    && (matriz[i1][j1 + 1] == 0) && (((j1 - 3) > -1) != true)) {
                if (matriz[i1][j1 + 1] == 0) {
                    if ((i1 + 1) < im) {
                        if (matriz[i1 + 1][j1 + 1] != 0) {
                            res = j1 + 1;
                            encontrado = true;
                        }
                    } else {
                        res = j1 + 1;
                        encontrado = true;
                    }
                }
            }
        }

        if ((encontrado == false) && (((i1 + 2) < im) && ((i1 - 1) > -1))) {
            if ((((matriz[i1 + 1][j1] + matriz[i1 + 2][j1] + rev1) == 3 * rev1) && (matriz[i1 - 1][j1] == 0))
                    && (encontrado == false)) {
                res = j1;
                encontrado = true;
            }
        }

        if ((encontrado == false) && (((j1 + 3) < jm) && ((i1 - 3) > -1))) {
            if ((((matriz[i1 - 3][j1 + 3] + matriz[i1 - 2][j1 + 2] + rev1) == 3 * rev1) && (matriz[i1 - 1][j1 + 1] == 0)
                    && (matriz[i1][j1 + 1] != 0)) && (encontrado == false)) {
                res = j1 + 1;
                encontrado = true;
            }

            if ((((matriz[i1 - 3][j1 + 3] + matriz[i1 - 1][j1 + 1] + rev1) == 3 * rev1) && (matriz[i1 - 2][j1 + 2] == 0)
                    && (matriz[i1 - 1][j1 + 2] != 0)) && (encontrado == false)) {
                res = j1 + 2;
                encontrado = true;
            }

            if (((matriz[i1 - 2][j1 + 2] + matriz[i1 - 1][j1 + 1] + rev1) == 3 * rev1) && (encontrado == false)) {
                if (matriz[i1 - 3][j1 + 3] == 0) {
                    if ((i1 - 2) > -1) {
                        if (matriz[i1 - 2][j1 + 3] != 0) {
                            res = j1 + 3;
                            encontrado = true;
                        }
                    } else {
                        res = j1 + 3;
                        encontrado = true;
                    }
                } else {
                    if (((j1 - 1) > -1) && ((i1 + 1) < im)) {
                        if (matriz[i1 + 1][j1 - 1] == 0) {
                            if ((i1 + 2) < im) {
                                if (matriz[i1 + 2][j1 - 1] != 0) {
                                    res = j1 - 1;
                                    encontrado = true;
                                }
                            } else {
                                res = j1 - 1;
                                encontrado = true;
                            }
                        }
                    }
                }
            }
        }

        if ((encontrado == false) && ((((j1 + 2) < jm) && ((i1 - 2) > -1)) && (((j1 - 1) > -1) && ((i1 + 1) < im)))) {
            if (((matriz[i1 + 1][j1 - 1] + matriz[i1 - 1][j1 + 1] + rev1) == 3 * rev1) && (encontrado == false)) {
                if (matriz[i1 - 2][j1 + 2] == 0) {
                    if ((i1 - 1) > -1) {
                        if (matriz[i1 - 1][j1 + 2] != 0) {
                            res = j1 + 2;
                            encontrado = true;
                        }
                    } else {
                        res = j1 + 2;
                        encontrado = true;
                    }
                } else {
                    if (((j1 - 2) > -1) && ((i1 + 2) < im)) {
                        if (matriz[i1 + 2][j1 - 2] == 0) {
                            if ((i1 + 3) < im) {
                                if (matriz[i1 + 3][j1 - 2] != 0) {
                                    res = j1 - 2;
                                    encontrado = true;
                                }
                            } else {
                                res = j1 - 2;
                                encontrado = true;
                            }
                        }
                    }
                }
            }

            if (((matriz[i1 + 1][j1 - 1] + matriz[i1 - 2][j1 + 2] + rev1) == 3 * rev1) && (encontrado == false)) {
                if (matriz[i1 - 1][j1 + 1] == 0) {
                    if ((i1 - 1) > -1) {
                        if (matriz[i1][j1 + 1] != 0) {
                            res = j1 + 1;
                            encontrado = true;
                        }
                    }
                }
            }

            if (((matriz[i1 - 1][j1 + 1] + matriz[i1 - 2][j1 + 2] + rev1) == 3 * rev1) && (encontrado == false)) {
                if (matriz[i1 + 1][j1 - 1] == 0) {
                    if ((i1 + 2) < im) {
                        if (matriz[i1 + 2][j1 - 1] != 0) {
                            res = j1 - 1;
                            encontrado = true;
                        }
                    } else {
                        res = j1 - 1;
                        encontrado = true;
                    }
                } else {
                    if (((j1 + 3) < jm) && ((i1 - 3) > -1)) {
                        if (matriz[i1 - 3][j1 + 3] == 0) {
                            if ((i1 - 2) > -1) {
                                if (matriz[i1 - 2][j1 + 3] != 0) {
                                    res = j1 + 3;
                                    encontrado = true;
                                }
                            } else {
                                res = j1 + 3;
                                encontrado = true;
                            }
                        }
                    }
                }
            }
        }

        if ((encontrado == false) && (((j1 + 3) < jm) && ((i1 + 3) < im))) {
            if ((((matriz[i1 + 3][j1 + 3] + matriz[i1 + 2][j1 + 2] + rev1) == 3 * rev1) && (matriz[i1 + 1][j1 + 1] == 0)
                    && (matriz[i1 + 2][j1 + 1] != 0)) && (encontrado == false)) {
                res = j1 + 1;
                encontrado = true;
            }

            if ((((matriz[i1 + 3][j1 + 3] + matriz[i1 + 1][j1 + 1] + rev1) == 3 * rev1) && (matriz[i1 + 2][j1 + 2] == 0)
                    && (matriz[i1 + 3][j1 + 2] != 0)) && (encontrado == false)) {
                res = j1 + 2;
                encontrado = true;
            }

            if (((matriz[i1 + 2][j1 + 2] + matriz[i1 + 1][j1 + 1] + rev1) == 3 * rev1) && (encontrado == false)) {
                if (matriz[i1 + 3][j1 + 3] == 0) {
                    if ((i1 + 4) < im) {
                        if (matriz[i1 + 4][j1 + 3] != 0) {
                            res = j1 + 3;
                            encontrado = true;
                        }
                    } else {
                        res = j1 + 3;
                        encontrado = true;
                    }
                } else {
                    if (((i1 - 1) > -1) && ((j1 - 1) > -1)) {
                        if (matriz[i1 - 1][j1 - 1] == 0) {
                            if ((i1 - 1) > -1) {
                                if (matriz[i1][j1 - 1] != 0) {
                                    res = j1 - 1;
                                    encontrado = true;
                                }
                            } else {
                                res = j1 - 1;
                                encontrado = true;
                            }
                        }
                    }
                }
            }
        }

        if ((encontrado == false) && ((((j1 + 2) < jm) && ((i1 + 2) < im)) && (((j1 - 1) > -1) && ((i1 - 1) > -1)))) {
            if (((matriz[i1 + 1][j1 + 1] + matriz[i1 - 1][j1 - 1] + rev1) == 3 * rev1) && (encontrado == false)) {
                if (matriz[i1 + 2][j1 + 2] == 0) {
                    if ((i1 + 3) < im) {
                        if (matriz[i1 + 1][j1 + 2] != 0) {
                            res = j1 + 2;
                            encontrado = true;
                        }
                    } else {
                        res = j1 + 2;
                        encontrado = true;
                    }
                } else {
                    if (((i1 - 2) > -1) && ((j1 - 2) > -1)) {
                        if (matriz[i1 - 2][j1 - 2] == 0) {
                            if ((i1 - 1) < im) {
                                if (matriz[i1 - 1][j1 - 2] != 0) {
                                    res = j1 - 2;
                                    encontrado = true;
                                }
                            }
                        }
                    }
                }
            }

            if (((matriz[i1 - 1][j1 - 1] + matriz[i1 + 2][j1 + 2] + rev1) == 3 * rev1) && (encontrado == false)) {
                if (matriz[i1 + 1][j1 + 1] == 0) {
                    if ((i1 + 2) < im) {
                        if (matriz[i1 + 2][j1 + 1] != 0) {
                            res = j1 + 1;
                            encontrado = true;
                        }
                    }
                }
            }

            if (((matriz[i1 + 1][j1 + 1] + matriz[i1 + 2][j1 + 2] + rev1) == 3 * rev1) && (encontrado == false)) {
                if (matriz[i1 - 1][j1 - 1] == 0) {
                    if ((i1) < im) {
                        if (matriz[i1][j1 - 1] != 0) {
                            res = j1 - 1;
                            encontrado = true;
                        }
                    }
                } else {
                    if (((j1 + 3) < jm) && ((i1 + 3) < im)) {
                        if (matriz[i1 + 3][j1 + 3] == 0) {
                            if ((i1 + 4) < im) {
                                if (matriz[i1 + 4][j1 + 3] != 0) {
                                    res = j1 + 3;
                                    encontrado = true;
                                }
                            } else {
                                res = j1 + 3;
                                encontrado = true;
                            }
                        }
                    }
                }
            }
        }

        if ((encontrado == false) && (((j1 - 3) > -1) && ((i1 + 3) < im))) {
            if ((((matriz[i1 + 3][j1 - 3] + matriz[i1 + 2][j1 - 2] + rev1) == 3 * rev1) && (matriz[i1 + 1][j1 - 1] == 0)
                    && (matriz[i1 + 2][j1 - 1] != 0)) && (encontrado == false)) {
                res = j1 - 1;
                encontrado = true;
            }

            if ((((matriz[i1 + 3][j1 - 3] + matriz[i1 + 1][j1 - 1] + rev1) == 3 * rev1) && (matriz[i1 + 2][j1 - 2] == 0)
                    && (matriz[i1 + 3][j1 - 2] != 0)) && (encontrado == false)) {
                res = j1 - 2;
                encontrado = true;
            }

            if (((matriz[i1 + 2][j1 - 2] + matriz[i1 + 1][j1 - 1] + rev1) == 3 * rev1) && (encontrado == false)) {
                if (matriz[i1 + 3][j1 - 3] == 0) {
                    if ((i1 + 4) < im) {
                        if (matriz[i1 + 4][j1 - 3] != 0) {
                            res = j1 - 3;
                            encontrado = true;
                        }
                    } else {
                        res = j1 - 3;
                        encontrado = true;
                    }
                } else {
                    if (((i1 - 1) > -1) && ((j1 + 1) < jm)) {
                        if (matriz[i1 - 1][j1 + 1] == 0) {
                            if ((i1) < im) {
                                if (matriz[i1][j1 + 1] != 0) {
                                    res = j1 + 1;
                                    encontrado = true;
                                }
                            } else {
                                res = j1 + 1;
                                encontrado = true;
                            }
                        }
                    }
                }
            }
        }

        if ((encontrado == false) && ((((i1 + 2) < im) && ((j1 - 2) > -1)) && (((i1 - 1) > -1) && ((j1 + 1) < jm)))) {
            if (((matriz[i1 - 1][j1 + 1] + matriz[i1 + 1][j1 - 1] + rev1) == 3 * rev1) && (encontrado == false)) {
                if (matriz[i1 + 2][j1 - 2] == 0) {
                    if ((i1 + 3) < im) {
                        if (matriz[i1 + 3][j1 - 2] != 0) {
                            res = j1 - 2;
                            encontrado = true;
                        }
                    } else {
                        res = j1 - 2;
                        encontrado = true;
                    }
                } else {
                    if (((i1 - 2) > -1) && ((j1 + 2) < jm)) {
                        if (matriz[i1 - 2][j1 + 2] == 0) {
                            if ((i1 - 1) < im) {
                                if (matriz[i1 - 1][j1 + 2] != 0) {
                                    res = j1 + 2;
                                    encontrado = true;
                                }
                            } else {
                                res = j1 + 2;
                                encontrado = true;
                            }
                        }
                    }
                }
            }

            if (((matriz[i1 - 1][j1 + 1] + matriz[i1 + 2][j1 - 2] + rev1) == 3 * rev1) && (encontrado == false)) {
                if (matriz[i1 + 1][j1 - 1] == 0) {
                    if ((i1 + 2) < im) {
                        if (matriz[i1 + 2][j1 - 1] != 0) {
                            res = j1 - 1;
                            encontrado = true;
                        }
                    }
                }
            }

            if (((matriz[i1 + 1][j1 - 1] + matriz[i1 + 2][j1 - 2] + rev1) == 3 * rev1) && (encontrado == false)) {
                if (matriz[i1 - 1][j1 + 1] == 0) {
                    if ((i1) < im) {
                        if (matriz[i1][j1 + 1] != 0) {
                            res = j1 + 1;
                            encontrado = true;
                        }
                    } else {
                        res = j1 + 1;
                        encontrado = true;
                    }
                } else {
                    if (((i1 + 3) < im) && ((j1 - 3) > -1)) {
                        if (matriz[i1 + 3][j1 - 3] == 0) {
                            if ((i1 + 4) < im) {
                                if (matriz[i1 + 4][j1 - 3] != 0) {
                                    res = j1 - 3;
                                    encontrado = true;
                                }
                            } else {
                                res = j1 - 3;
                                encontrado = true;
                            }
                        }
                    }
                }
            }
        }

        if ((encontrado == false) && (((j1 - 3) > -1) && ((i1 - 3) > -1))) {
            if ((((matriz[i1 - 3][j1 - 3] + matriz[i1 - 2][j1 - 2] + rev1) == 3 * rev1) && (matriz[i1 - 1][j1 - 1] == 0)
                    && (matriz[i1][j1 - 1] != 0)) && (encontrado == false)) {
                res = j1 - 1;
                encontrado = true;
            }

            if ((((matriz[i1 - 3][j1 - 3] + matriz[i1 - 1][j1 - 1] + rev1) == 3 * rev1) && (matriz[i1 - 2][j1 - 2] == 0)
                    && (matriz[i1 - 1][j1 - 2] != 0)) && (encontrado == false)) {
                res = j1 - 2;
                encontrado = true;
            }

            if (((matriz[i1 - 2][j1 - 2] + matriz[i1 - 1][j1 - 1] + rev1) == 3 * rev1) && (encontrado == false)) {
                if (matriz[i1 - 3][j1 - 3] == 0) {
                    if ((i1 - 2) < im) {
                        if (matriz[i1 - 2][j1 - 3] != 0) {
                            res = j1 - 3;
                            encontrado = true;
                        }
                    } else {
                        res = j1 - 3;
                        encontrado = true;
                    }
                } else {
                    if (((i1 + 1) < im) && ((j1 + 1) < jm)) {
                        if (matriz[i1 + 1][j1 + 1] == 0) {
                            if ((i1 + 2) < im) {
                                if (matriz[i1 + 2][j1 + 1] != 0) {
                                    res = j1 + 1;
                                    encontrado = true;
                                }
                            } else {
                                res = j1 + 1;
                                encontrado = true;
                            }
                        }
                    }
                }
            }
        }

        if ((encontrado == false) && ((((j1 - 2) > -1) && ((i1 - 2) > -1)) && (((j1 + 1) < jm) && ((i1 + 1) < im)))) {
            if (((matriz[i1 - 1][j1 - 1] + matriz[i1 + 1][j1 + 1] + rev1) == 3 * rev1) && (encontrado == false)) {
                if (matriz[i1 - 2][j1 - 2] == 0) {
                    if ((i1 - 1) > -1) {
                        if (matriz[i1 - 1][j1 - 2] != 0) {
                            res = j1 - 2;
                            encontrado = true;
                        }
                    } else {
                        res = j1 - 2;
                        encontrado = true;
                    }
                } else {
                    if (((j1 + 2) < jm) && ((i1 + 2) < im)) {
                        if (matriz[i1 + 2][j1 + 2] == 0) {
                            if ((i1 + 3) < im) {
                                if (matriz[i1 + 3][j1 + 2] != 0) {
                                    res = j1 + 2;
                                    encontrado = true;
                                }
                            } else {
                                res = j1 + 2;
                                encontrado = true;
                            }
                        }
                    }
                }
            }

            if (((matriz[i1 + 1][j1 + 1] + matriz[i1 - 2][j1 - 2] + rev1) == 3 * rev1) && (encontrado == false)) {
                if (matriz[i1 - 1][j1 - 1] == 0) {
                    if (matriz[i1][j1 - 1] != 0) {
                        res = j1 - 1;
                        encontrado = true;
                    }
                }
            }

            if (((matriz[i1 - 1][j1 - 1] + matriz[i1 - 2][j1 - 2] + rev1) == 3 * rev1) && (encontrado == false)) {
                if (matriz[i1 + 1][j1 + 1] == 0) {
                    if ((i1 + 2) < im) {
                        if (matriz[i1 + 2][j1 + 1] != 0) {
                            res = j1 + 1;
                            encontrado = true;
                        }
                    } else {
                        res = j1 + 1;
                        encontrado = true;
                    }
                } else {
                    if (((j1 - 3) > -1) && ((i1 - 3) > -1)) {
                        if (matriz[i1 - 3][j1 - 3] == 0) {
                            if ((i1 - 2) > -1) {
                                if (matriz[i1 - 2][j1 - 3] != 0) {
                                    res = j1 - 3;
                                    encontrado = true;
                                }
                            } else {
                                res = j1 - 3;
                                encontrado = true;
                            }
                        }
                    }
                }
            }
        }

        if (((j1 + 3) < jm) && ((i1 - 1) > -1)) {
            if ((matriz[i1 - 1][j1 + 1] == rev1) && (matriz[i1 - 1][j1 + 2] == rev1)
                    && (matriz[i1 - 1][j1 + 3] == rev1)) {
                res = j1;
                encontrado = true;
            }
        }

        if (((j1 + 3) < jm) && ((i1 - 4) > -1)) {
            if ((matriz[i1 - 2][j1 + 1] == rev1) && (matriz[i1 - 3][j1 + 2] == rev1)
                    && (matriz[i1 - 4][j1 + 3] == rev1)) {
                res = j1;
                encontrado = true;
            }
        }

        if (((j1 + 3) < jm) && ((i1 + 2) < im)) {
            if ((matriz[i1][j1 + 1] == rev1) && (matriz[i1 + 1][j1 + 2] == rev1) && (matriz[i1 + 2][j1 + 3] == rev1)) {
                res = j1;
                encontrado = true;
            }
        }

        if (((j1 - 3) > -1) && ((i1 - 1) > -1)) {
            if ((matriz[i1 - 1][j1 - 1] == rev1) && (matriz[i1 - 1][j1 - 2] == rev1)
                    && (matriz[i1 - 1][j1 - 3] == rev1)) {
                res = j1;
                encontrado = true;
            }
        }

        if (((j1 - 3) > -1) && ((i1 - 4) > -1)) {
            if ((matriz[i1 - 2][j1 - 1] == rev1) && (matriz[i1 - 3][j1 - 2] == rev1)
                    && (matriz[i1 - 4][j1 - 3] == rev1)) {
                res = j1;
                encontrado = true;
            }
        }

        if (((j1 - 3) > -1) && ((i1 + 2) < im)) {
            if ((matriz[i1][j1 - 1] == rev1) && (matriz[i1 + 1][j1 - 2] == rev1) && (matriz[i1 + 2][j1 - 3] == rev1)) {
                res = j1;
                encontrado = true;
            }
        }

        if (((j1 + 2) < jm) && ((j1 - 1) > -1) && ((i1 - 1) > -1)) {
            if ((matriz[i1 - 1][j1 + 1] == rev1) && (matriz[i1 - 1][j1 + 2] == rev1)
                    && (matriz[i1 - 1][j1 - 1] == rev1)) {
                res = j1;
                encontrado = true;
            }
        }

        if (((j1 + 1) < jm) && ((j1 - 2) > -1) && ((i1 - 1) > -1)) {
            if ((matriz[i1 - 1][j1 - 1] == rev1) && (matriz[i1 - 1][j1 - 2] == rev1)
                    && (matriz[i1 - 1][j1 + 1] == rev1)) {
                res = j1;
                encontrado = true;
            }
        }

        if (((j1 + 2) < jm) && ((i1 - 3) > -1) && ((j1 - 1) > -1) && ((i1) < im)) {
            if ((matriz[i1 - 2][j1 + 1] == rev1) && (matriz[i1 - 3][j1 + 2] == rev1) && (matriz[i1][j1 - 1] == rev1)) {
                res = j1;
                encontrado = true;
            }
        }

        if (((j1 + 2) < jm) && ((i1 - 2) > -1) && ((j1 - 1) > -1) && ((i1 + 1) < im)) {
            if ((matriz[i1][j1 + 1] == rev1) && (matriz[i1 + 1][j1 + 2] == rev1) && (matriz[i1 - 2][j1 - 1] == rev1)) {
                res = j1;
                encontrado = true;
            }
        }

        if (((j1 - 2) > -1) && ((i1 - 3) > -1) && ((j1 + 1) < jm) && ((i1) < im)) {
            if ((matriz[i1 - 2][j1 - 1] == rev1) && (matriz[i1 - 3][j1 - 2] == rev1) && (matriz[i1][j1 + 1] == rev1)) {
                res = j1;
                encontrado = true;
            }
        }

        if (((j1 - 2) > -1) && ((i1 + 1) < im) && ((j1 + 1) < jm) && ((i1 + 1) < im)) {
            if ((matriz[i1][j1 - 1] == rev1) && (matriz[i1 + 1][j1 - 2] == rev1) && (matriz[i1 + 1][j1 + 1] == rev1)) {
                res = j1;
                encontrado = true;
            }
        }
        return res;
    }

}
