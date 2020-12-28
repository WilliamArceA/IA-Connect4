
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import java.util.ArrayList;

public class Juego extends Frame implements MouseListener {
    private static final long serialVersionUID = 1L;
    JButton Empezar = new JButton("Empezar");
    JButton Salir = new JButton("Salir");
    int jmaximo, imaximo;
    boolean inicioDelJuego = false;
    int[][] matriz;
    boolean ganador = false, prioridad = false, hacer = false, empate = false;
    ArrayList<Integer> prioridadesEnI = new ArrayList<Integer>();
    ArrayList<Integer> prioridadesEnJ = new ArrayList<Integer>();
    int ig, jg;
    Panel PanelPrincipal = new Panel();

    Juego() {
        super("Conecta cuatro");
        matriz = new int[6][7];
        imaximo = 6;
        jmaximo = 7;
        setLayout(new GridLayout(20, 5));
        Empezar.addMouseListener(this);
        Salir.addMouseListener(this);
        PanelPrincipal.add(Empezar);
        PanelPrincipal.add(Salir);
        add(PanelPrincipal);

    }

    public void colocar(int columna) {
        Thread tiempo = new Thread();
        Graphics g = getGraphics();
        g.setColor(Color.red);
        int c = 0;
        Random aleatorio = new Random();/**/
        int r = aleatorio.nextInt(jmaximo);
        if (columna < jmaximo) {
            if (ganador == false) {
                if (matriz[0][columna] == 0) {
                    if (matriz[imaximo - 1][columna] == 0) {
                        matriz[imaximo - 1][columna] = 5;
                        g.fillOval(((columna) * 90) + 20, ((imaximo) * 65) + 100, 60, 60);
                        revision(5, imaximo - 1, columna);

                        int b = encontradaPrioridad(5, imaximo - 1, columna);
                        if (!verificarEmpate()) {
                            mostrarMensaje("Empate");
                            ganador = true;
                        } else {
                            if (ganador != true) {
                                if (prioridad == false) {
                                    TurnoMaquina(r);
                                } else {
                                    if (hacer == true) {
                                        if (matriz[ig][jg] == 0) {
                                            int debesjugar = jg;
                                            ig = -3;
                                            jg = -3;
                                            hacer = false;
                                            TurnoMaquina(debesjugar);

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

                                mostrarMensaje("ganador persona");
                                try {
                                    tiempo.wait(1000);
                                } catch (InterruptedException e) {
                                }
                            }
                        }

                    } else {
                        while (matriz[c + 1][columna] == 0) {
                            c++;
                        }
                        matriz[c][columna] = 5;
                        g.fillOval((columna * 90) + 20, ((c + 1) * 65) + 100, 60, 60);
                        revision(5, c, columna);
                        int b = encontradaPrioridad(5, c, columna);
                        if (!verificarEmpate()) {
                            mostrarMensaje("Empate");

                            ganador = true;
                        } else {
                            if (ganador != true) {
                                if (prioridad == false) {
                                    TurnoMaquina(r);
                                } else {
                                    if (hacer == true) {
                                        if (matriz[ig][jg] == 0) {

                                            int debesjugar = jg;
                                            ig = -3;
                                            jg = -3;
                                            hacer = false;
                                            TurnoMaquina(debesjugar);

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
                                mostrarMensaje("ganador persona");

                                try {
                                    tiempo.wait(1000);
                                } catch (InterruptedException e) {
                                }
                            }
                        }

                    }
                } else {

                    mostrarMensaje("no se puede colocar");
                }
            } else {
                mostrarMensaje("buen juego");

            }
        } else {
            mostrarMensaje("no se puede colocar");
        }

    }

    public void TurnoMaquina(int columna) {
        int c = 0;
        Graphics g = getGraphics();
        Thread tiempo = new Thread();
        g.setColor(Color.blue);
        if (matriz[0][columna] == 0) {
            if (matriz[imaximo - 1][columna] == 0) {
                matriz[imaximo - 1][columna] = 4;
                g.fillOval(((columna) * 90) + 20, ((imaximo) * 65) + 100, 60, 60);
                revision(4, imaximo - 1, columna);
                if (encontradaPrioridad(4, imaximo - 1, columna) != -2) {
                    hacer = true;
                    ig = imaximo - 1;
                    jg = encontradaPrioridad(4, imaximo - 1, columna);
                }

                if (!verificarEmpate()) {
                    mostrarMensaje("Empate");

                    ganador = true;
                } else {
                    if (ganador == true) {

                        mostrarMensaje("ganador pc");

                        try {
                            tiempo.wait(1000);
                        } catch (InterruptedException e) {
                        }

                    }
                }

            } else {
                while (matriz[c + 1][columna] == 0) {
                    c++;
                }
                matriz[c][columna] = 4;
                g.fillOval((columna * 90) + 20, ((c + 1) * 65) + 100, 60, 60);
                revision(4, c, columna);
                if (encontradaPrioridad(4, c, columna) != -2) {

                    hacer = true;
                    ig = c;
                    jg = encontradaPrioridad(4, c, columna);
                }
                if (!verificarEmpate()) {
                    ganador = true;
                    mostrarMensaje("Empate");
                } else {

                    if (ganador == true) {
                        mostrarMensaje("ganador pc");

                        try {
                            tiempo.wait(1000);
                        } catch (InterruptedException e) {
                        }
                    }
                }

            }
        } else {
            if (columna == imaximo - 1) {
                TurnoMaquina(0);
            } else {
                TurnoMaquina(columna + 1);
            }
        }
    }

    private void revision(int rev, int i, int j) {
        // las variables son solo comprobantes, los que tienen r son de horizontal y
        // vertical y los que tienen D son diagonales.
        int ar = 0, br = 0, cr = 0, dr = 0, aD = 0, bD = 0, cD = 0, dD = 0, aR = 0, bR = 0, cR = 0, dR = 0, eR = 0,
                fR = 0;
        if ((j + 3) < jmaximo) {
            ar = (matriz[i][j + 1] + matriz[i][j + 2] + matriz[i][j + 3]) + rev;
        }

        if ((j - 3) > -1) {
            br = (matriz[i][j - 1] + matriz[i][j - 2] + matriz[i][j - 3]) + rev;
        }

        if ((i + 3) < imaximo) {
            cr = (matriz[i + 1][j] + matriz[i + 2][j] + matriz[i + 3][j]) + rev;
        }

        if (((j - 1) > -1) && ((j + 2) < jmaximo)) {
            aR = matriz[i][j + 1] + matriz[i][j + 2] + matriz[i][j - 1] + rev;
        }

        if (((j - 2) > -1) && ((j + 1) < jmaximo)) {
            bR = matriz[i][j + 1] + matriz[i][j - 2] + matriz[i][j - 1] + rev;
        }

        if (((j - 1) > -1) && ((j + 2) < jmaximo) && ((i + 1) < imaximo) && ((i - 2) > -1)) {
            cR = matriz[i + 1][j - 1] + matriz[i - 1][j + 1] + matriz[i - 2][j + 2] + rev;
        }

        if (((j - 2) > -1) && ((j + 1) < jmaximo) && ((i + 2) < imaximo) && ((i - 1) > -1)) {
            dR = matriz[i - 1][j + 1] + matriz[i + 1][j - 1] + matriz[i + 2][j - 2] + rev;
        }

        if (((j - 1) > -1) && ((j + 2) < jmaximo) && ((i - 1) > -1) && ((i + 2) < imaximo)) {
            eR = matriz[i - 1][j - 1] + matriz[i + 1][j + 1] + matriz[i + 2][j + 2] + rev;
        }

        if (((j - 2) > -1) && ((j + 1) < jmaximo) && ((i - 2) > -1) && ((i + 1) < imaximo)) {
            fR = matriz[i - 1][j - 1] + matriz[i + 1][j + 1] + matriz[i - 2][j - 2] + rev;
        }

        if (((i - 3) > -1) && ((j + 3) < jmaximo)) {
            aD = matriz[i - 1][j + 1] + matriz[i - 2][j + 2] + matriz[i - 3][j + 3] + rev;
        }

        if (((i + 3) < imaximo) && ((j + 3) < jmaximo)) {
            bD = matriz[i + 1][j + 1] + matriz[i + 2][j + 2] + matriz[i + 3][j + 3] + rev;
        }

        if (((i - 3) > -1) && ((j - 3) > -1)) {
            cD = matriz[i - 1][j - 1] + matriz[i - 2][j - 2] + matriz[i - 3][j - 3] + rev;
        }

        if (((i + 3) < imaximo) && ((j - 3) > -1)) {
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

        g.setColor(Color.CYAN);
        g.fillRect(1, 1, 640, 680);

        g.setColor(Color.white);
        g.fillRect(1, 1, 640, 50);
        for (int x1 = 6; x1 > 0; x1--) {
            g.fillOval((6 * 90) + 20, (x1 * 65) + 100, 60, 60);
        }
        for (int i = 0; i < imaximo; i++)
            for (int j = 1; j < jmaximo; j++) {
                g.fillOval((i * 90) + 20, (j * 65) + 100, 60, 60);
                matriz[i][j] = 0;
            }
    }

    public void EmpezarTablero() {
        Graphics g = getGraphics();
        int x;
        int y;
        ig = -3;
        jg = -3;
        hacer = false;
        ganador = false;
        prioridad = true;
        empate = false;
        g.setColor(Color.cyan);
        g.fillRect(1, 1, 640, 680);

        g.setColor(Color.white);
        g.fillRect(1, 1, 640, 50);
        matriz = new int[6][7];
        imaximo = 6;
        jmaximo = 7;
        for (int x1 = 6; x1 > 0; x1--) {
            g.fillOval((6 * 90) + 20, (x1 * 65) + 100, 60, 60);
        }
        for (x = 0; x < 6; x++) {
            for (y = 1; y < 7; y++) {
                g.fillOval((x * 90) + 20, (y * 65) + 100, 60, 60);
                matriz[x][y] = 0;
            }
        }
    }

    public void Salir() {

        System.exit(0);
    }

    private boolean verificarEmpate() {
        boolean posicionDisponible = false;
        for (int i = 0; i < imaximo; i++) {
            for (int j = 0; j < jmaximo; j++) {
                if (matriz[i][j] == 0) {
                    posicionDisponible = true;
                }
            }
        }
        return posicionDisponible;
    }

    public void mousePressed(MouseEvent me) {
        int z;
        z = me.getX();
        if ((ganador != true) && (me.getSource() != Salir)) {
            z = (z - 10) / 90;
            colocar(z);
        } else {
            if ((me.getSource() != Empezar) && (me.getSource() != Salir)) {

                mostrarMensaje("Ya hay un ganador para este tablero, inicie otro por favor");

            }

        }

        if (me.getSource() == Empezar) {
            EmpezarTablero();
            if (!inicioDelJuego) {
                addMouseListener(this);
                inicioDelJuego = true;
            }

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

    public void mostrarMensaje(String texto) {
        Mensaje mensaje = new Mensaje(this, texto);
        mensaje.setLocationRelativeTo(null);
        mensaje.setVisible(true);
    }

    protected int encontradaPrioridad(int rev1, int i1, int j1) {
        // las variables son solo comprobantes, los que tienen r son de horizontal y
        // vertical y los que tienen D son diagonales.
        int res = -2;
        boolean encontrado = false;
        if (((j1 + 3) < jmaximo) && (encontrado == false)) {
            if (((matriz[i1][j1 + 1] + matriz[i1][j1 + 2] + rev1) == 3 * rev1) && (encontrado == false)) {
                if (matriz[i1][j1 + 3] == 0) {
                    if ((i1 + 1) < imaximo) {
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
                            if ((i1 + 1) < imaximo) {
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
                    if ((i1 + 1) < imaximo) {
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
                    if ((i1 + 1) < imaximo) {
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

        if ((encontrado == false) && (((j1 + 2) < jmaximo) && ((j1 - 1) > -1))) {
            if (((matriz[i1][j1 + 1] + matriz[i1][j1 - 1] + rev1) == 3 * rev1) && (encontrado == false)) {
                if (matriz[i1][j1 + 2] == 0) {
                    if ((i1 + 1) < imaximo) {
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
                            if ((i1 + 1) < imaximo) {
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
                    if ((i1 + 1) < imaximo) {
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
                    && (matriz[i1][j1 - 1] == 0) && (((j1 + 3) < jmaximo) != true)) {
                if (matriz[i1][j1 + 1] == 0) {
                    if ((i1 + 1) < imaximo) {
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
                    if ((i1 + 1) < imaximo) {
                        if (matriz[i1 + 1][j1 - 3] != 0) {
                            res = j1 - 3;
                            encontrado = true;
                        }
                    } else {
                        res = j1 - 3;
                        encontrado = true;
                    }
                } else {
                    if ((j1 + 1) < jmaximo) {
                        if (matriz[i1][j1 + 1] == 0) {
                            if ((i1 + 1) < imaximo) {
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
                    if ((i1 + 1) < imaximo) {
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
                    if ((i1 + 1) < imaximo) {
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

        if ((encontrado == false) && (((j1 + 1) < jmaximo) && ((j1 - 2) > -1))) {
            if (((matriz[i1][j1 - 1] + matriz[i1][j1 + 1] + rev1) == 3 * rev1) && (encontrado == false)) {
                if (matriz[i1][j1 - 2] == 0) {
                    if ((i1 + 1) < imaximo) {
                        if (matriz[i1 + 1][j1 - 2] != 0) {
                            res = j1 - 2;
                            encontrado = true;
                        }
                    } else {
                        res = j1 - 2;
                        encontrado = true;
                    }
                } else {
                    if ((j1 + 2) < imaximo) {
                        if (matriz[i1][j1 + 2] == 0) {
                            if ((i1 + 1) < imaximo) {
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
                    if ((i1 + 1) < imaximo) {
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
                    if ((i1 + 1) < imaximo) {
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

        if ((encontrado == false) && (((i1 + 2) < imaximo) && ((i1 - 1) > -1))) {
            if ((((matriz[i1 + 1][j1] + matriz[i1 + 2][j1] + rev1) == 3 * rev1) && (matriz[i1 - 1][j1] == 0))
                    && (encontrado == false)) {
                res = j1;
                encontrado = true;
            }
        }

        if ((encontrado == false) && (((j1 + 3) < jmaximo) && ((i1 - 3) > -1))) {
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
                    if (((j1 - 1) > -1) && ((i1 + 1) < imaximo)) {
                        if (matriz[i1 + 1][j1 - 1] == 0) {
                            if ((i1 + 2) < imaximo) {
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

        if ((encontrado == false)
                && ((((j1 + 2) < jmaximo) && ((i1 - 2) > -1)) && (((j1 - 1) > -1) && ((i1 + 1) < imaximo)))) {
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
                    if (((j1 - 2) > -1) && ((i1 + 2) < imaximo)) {
                        if (matriz[i1 + 2][j1 - 2] == 0) {
                            if ((i1 + 3) < imaximo) {
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
                    if ((i1 + 2) < imaximo) {
                        if (matriz[i1 + 2][j1 - 1] != 0) {
                            res = j1 - 1;
                            encontrado = true;
                        }
                    } else {
                        res = j1 - 1;
                        encontrado = true;
                    }
                } else {
                    if (((j1 + 3) < jmaximo) && ((i1 - 3) > -1)) {
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

        if ((encontrado == false) && (((j1 + 3) < jmaximo) && ((i1 + 3) < imaximo))) {
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
                    if ((i1 + 4) < imaximo) {
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

        if ((encontrado == false)
                && ((((j1 + 2) < jmaximo) && ((i1 + 2) < imaximo)) && (((j1 - 1) > -1) && ((i1 - 1) > -1)))) {
            if (((matriz[i1 + 1][j1 + 1] + matriz[i1 - 1][j1 - 1] + rev1) == 3 * rev1) && (encontrado == false)) {
                if (matriz[i1 + 2][j1 + 2] == 0) {
                    if ((i1 + 3) < imaximo) {
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
                            if ((i1 - 1) < imaximo) {
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
                    if ((i1 + 2) < imaximo) {
                        if (matriz[i1 + 2][j1 + 1] != 0) {
                            res = j1 + 1;
                            encontrado = true;
                        }
                    }
                }
            }

            if (((matriz[i1 + 1][j1 + 1] + matriz[i1 + 2][j1 + 2] + rev1) == 3 * rev1) && (encontrado == false)) {
                if (matriz[i1 - 1][j1 - 1] == 0) {
                    if ((i1) < imaximo) {
                        if (matriz[i1][j1 - 1] != 0) {
                            res = j1 - 1;
                            encontrado = true;
                        }
                    }
                } else {
                    if (((j1 + 3) < jmaximo) && ((i1 + 3) < imaximo)) {
                        if (matriz[i1 + 3][j1 + 3] == 0) {
                            if ((i1 + 4) < imaximo) {
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

        if ((encontrado == false) && (((j1 - 3) > -1) && ((i1 + 3) < imaximo))) {
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
                    if ((i1 + 4) < imaximo) {
                        if (matriz[i1 + 4][j1 - 3] != 0) {
                            res = j1 - 3;
                            encontrado = true;
                        }
                    } else {
                        res = j1 - 3;
                        encontrado = true;
                    }
                } else {
                    if (((i1 - 1) > -1) && ((j1 + 1) < jmaximo)) {
                        if (matriz[i1 - 1][j1 + 1] == 0) {
                            if ((i1) < imaximo) {
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

        if ((encontrado == false)
                && ((((i1 + 2) < imaximo) && ((j1 - 2) > -1)) && (((i1 - 1) > -1) && ((j1 + 1) < jmaximo)))) {
            if (((matriz[i1 - 1][j1 + 1] + matriz[i1 + 1][j1 - 1] + rev1) == 3 * rev1) && (encontrado == false)) {
                if (matriz[i1 + 2][j1 - 2] == 0) {
                    if ((i1 + 3) < imaximo) {
                        if (matriz[i1 + 3][j1 - 2] != 0) {
                            res = j1 - 2;
                            encontrado = true;
                        }
                    } else {
                        res = j1 - 2;
                        encontrado = true;
                    }
                } else {
                    if (((i1 - 2) > -1) && ((j1 + 2) < jmaximo)) {
                        if (matriz[i1 - 2][j1 + 2] == 0) {
                            if ((i1 - 1) < imaximo) {
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
                    if ((i1 + 2) < imaximo) {
                        if (matriz[i1 + 2][j1 - 1] != 0) {
                            res = j1 - 1;
                            encontrado = true;
                        }
                    }
                }
            }

            if (((matriz[i1 + 1][j1 - 1] + matriz[i1 + 2][j1 - 2] + rev1) == 3 * rev1) && (encontrado == false)) {
                if (matriz[i1 - 1][j1 + 1] == 0) {
                    if ((i1) < imaximo) {
                        if (matriz[i1][j1 + 1] != 0) {
                            res = j1 + 1;
                            encontrado = true;
                        }
                    } else {
                        res = j1 + 1;
                        encontrado = true;
                    }
                } else {
                    if (((i1 + 3) < imaximo) && ((j1 - 3) > -1)) {
                        if (matriz[i1 + 3][j1 - 3] == 0) {
                            if ((i1 + 4) < imaximo) {
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
                    if ((i1 - 2) < imaximo) {
                        if (matriz[i1 - 2][j1 - 3] != 0) {
                            res = j1 - 3;
                            encontrado = true;
                        }
                    } else {
                        res = j1 - 3;
                        encontrado = true;
                    }
                } else {
                    if (((i1 + 1) < imaximo) && ((j1 + 1) < jmaximo)) {
                        if (matriz[i1 + 1][j1 + 1] == 0) {
                            if ((i1 + 2) < imaximo) {
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

        if ((encontrado == false)
                && ((((j1 - 2) > -1) && ((i1 - 2) > -1)) && (((j1 + 1) < jmaximo) && ((i1 + 1) < imaximo)))) {
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
                    if (((j1 + 2) < jmaximo) && ((i1 + 2) < imaximo)) {
                        if (matriz[i1 + 2][j1 + 2] == 0) {
                            if ((i1 + 3) < imaximo) {
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
                    if ((i1 + 2) < imaximo) {
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

        if (((j1 + 3) < jmaximo) && ((i1 - 1) > -1)) {
            if ((matriz[i1 - 1][j1 + 1] == rev1) && (matriz[i1 - 1][j1 + 2] == rev1)
                    && (matriz[i1 - 1][j1 + 3] == rev1)) {
                res = j1;
                encontrado = true;
            }
        }

        if (((j1 + 3) < jmaximo) && ((i1 - 4) > -1)) {
            if ((matriz[i1 - 2][j1 + 1] == rev1) && (matriz[i1 - 3][j1 + 2] == rev1)
                    && (matriz[i1 - 4][j1 + 3] == rev1)) {
                res = j1;
                encontrado = true;
            }
        }

        if (((j1 + 3) < jmaximo) && ((i1 + 2) < imaximo)) {
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

        if (((j1 - 3) > -1) && ((i1 + 2) < imaximo)) {
            if ((matriz[i1][j1 - 1] == rev1) && (matriz[i1 + 1][j1 - 2] == rev1) && (matriz[i1 + 2][j1 - 3] == rev1)) {
                res = j1;
                encontrado = true;
            }
        }

        if (((j1 + 2) < jmaximo) && ((j1 - 1) > -1) && ((i1 - 1) > -1)) {
            if ((matriz[i1 - 1][j1 + 1] == rev1) && (matriz[i1 - 1][j1 + 2] == rev1)
                    && (matriz[i1 - 1][j1 - 1] == rev1)) {
                res = j1;
                encontrado = true;
            }
        }

        if (((j1 + 1) < jmaximo) && ((j1 - 2) > -1) && ((i1 - 1) > -1)) {
            if ((matriz[i1 - 1][j1 - 1] == rev1) && (matriz[i1 - 1][j1 - 2] == rev1)
                    && (matriz[i1 - 1][j1 + 1] == rev1)) {
                res = j1;
                encontrado = true;
            }
        }

        if (((j1 + 2) < jmaximo) && ((i1 - 3) > -1) && ((j1 - 1) > -1) && ((i1) < imaximo)) {
            if ((matriz[i1 - 2][j1 + 1] == rev1) && (matriz[i1 - 3][j1 + 2] == rev1) && (matriz[i1][j1 - 1] == rev1)) {
                res = j1;
                encontrado = true;
            }
        }

        if (((j1 + 2) < jmaximo) && ((i1 - 2) > -1) && ((j1 - 1) > -1) && ((i1 + 1) < imaximo)) {
            if ((matriz[i1][j1 + 1] == rev1) && (matriz[i1 + 1][j1 + 2] == rev1) && (matriz[i1 - 2][j1 - 1] == rev1)) {
                res = j1;
                encontrado = true;
            }
        }

        if (((j1 - 2) > -1) && ((i1 - 3) > -1) && ((j1 + 1) < jmaximo) && ((i1) < imaximo)) {
            if ((matriz[i1 - 2][j1 - 1] == rev1) && (matriz[i1 - 3][j1 - 2] == rev1) && (matriz[i1][j1 + 1] == rev1)) {
                res = j1;
                encontrado = true;
            }
        }

        if (((j1 - 2) > -1) && ((i1 + 1) < imaximo) && ((j1 + 1) < jmaximo) && ((i1 + 1) < imaximo)) {
            if ((matriz[i1][j1 - 1] == rev1) && (matriz[i1 + 1][j1 - 2] == rev1) && (matriz[i1 + 1][j1 + 1] == rev1)) {
                res = j1;
                encontrado = true;
            }
        }

        if (((j1 + 2) < imaximo) && (!encontrado)) 
        {
            if (((matriz[i1][j1 + 1] + rev1) == 2 * rev1)) {
                if (matriz[i1][j1 + 2] == 0) {
                    if ((i1 + 1) < imaximo) {
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
        
        if (((j1 - 2) > -1) && (!encontrado)) {

            if (((matriz[i1][j1 - 1] + rev1) == 2 * rev1)) {
                if (matriz[i1][j1 - 2] == 0) {
                    if ((i1 + 1) < imaximo) {
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

        return res;
    }

}
