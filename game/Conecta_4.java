import java.util.Scanner;

public class Conecta_4 {

    public static void main(String[] args) {

        imprimirPresentacion();

        Scanner valorIngresado = new Scanner(System.in);

        int[][] tablero = cargar();
        int jugadorActual = 1;
        boolean hayGanador = false; 
        boolean juegoCancelado = false;     

        while ((!hayGanador)&&(tableroJugable(tablero)))
        {
            boolean jugadaPosible=false;;
            int columna=-1;
            while ((!jugadaPosible))
            {
                mostrar(tablero);
                System.out.print("Es el turno del jugador: " + jugadorActual + ", por favor ingrese la columna");
                columna = valorIngresado.nextInt();
                if(columna==9)
                {
                    System.out.print("Juego Cancelado");
                    juegoCancelado=true;
                    jugadaPosible = true;
                    hayGanador=true;
                }
                else
                {
                    columna--;
                    jugadaPosible = verificarColumnaExistente(columna,tablero);
                    if(jugadaPosible)
                    {
                        jugadaPosible = verificarColumnaLlena(columna,tablero);
                        if(jugadaPosible)
                        {
                            jugadaPosible = verificarColumnaLlena(columna,tablero);
                        }else
                        {
                            System.out.println("Columna llena");
                            System.out.println();
                        }
                    }else
                    {
                        System.out.println("Esa columna no existe");
                        System.out.println();
                    }
                }
            }
            if(!juegoCancelado)
            {
                tablero = colocar(columna,jugadorActual, tablero);

                hayGanador = buscarGanador(jugadorActual,tablero);

                jugadorActual = cambioDeTurno(jugadorActual); 

                mostrar(tablero);
            }

        }

        if(!juegoCancelado)
        { 
            imprimirResultadoJuego(hayGanador,jugadorActual);
        }

    }

    private static void imprimirResultadoJuego(boolean hayGanador,int jugadorActual)
    {
        if (hayGanador)
        {
            if (jugadorActual==1)
            {
                System.out.println("Felicitaciones jugador número 2, ganó el juego");
            }else{
                System.out.println("Felicitaciones jugador número 1, ganó el juego");
            }
        }else{
            System.out.println("Los jugadores empataron");
        }
    }

    private static int cambioDeTurno(int jugadorActual)
    {
        if (jugadorActual == 1)
        {
            jugadorActual = 2;
        }else{
            jugadorActual = 1;
        }
        return jugadorActual;
    }

    private static int[][] cargar()
    {
        int[][] tablero  = new int[6][7];

        for (int i = 0; i < 6; i++)
        {
            for (int j = 0; j <7; j++)
            {
                tablero[i][j] = 0;
            }
        }

        return tablero;
    }

    private static int[][] colocar(int columna, int jugadorActual, int[][] tablero)
    {

        for (int j = tablero.length-1; j >= 0; j--)
        {
            if(tablero[j][columna] == 0)
            {
                tablero[j][columna] = jugadorActual;
                break;
            }
        }
        return tablero;
    }

    private static void imprimirPresentacion()
    {
        System.out.print("\f");
        System.out.println("Juego de Conecta Cuatro");
        System.out.println("Este juego tiene un tablero de 6x7");
        System.out.println("Para jugar solamente ingrese el número de la colummna donde quiere jugar");
        System.out.println("Si ingresa 9, el juego se cancelará");
        System.out.println();
    }

    private static void mostrar(int[][] tablero){
        System.out.println();
        System.out.println("Estado Actual del tablero: ");
        System.out.println();
        System.out.println();
        System.out.println("_______________");
        for (int i = 0; i < tablero.length; i++)
        {
            System.out.print("|");
            for (int j = 0; j < tablero[0].length; j++)
            {
                System.out.print(tablero[i][j]);
                System.out.print("|");
            }
            System.out.println();
            System.out.println("_______________");
        }
        System.out.println();
        System.out.println(" 1 2 3 4 5 6 7");
        System.out.println();
    }

    private static boolean tableroJugable(int[][] tablero){
        boolean aunJugable=false;
        for (int i = 0; i < tablero.length; i++)
        {

            for (int j = 0; j < tablero[0].length; j++)
            {
                if((tablero[i][j]==0)&&(!aunJugable))
                {
                    aunJugable=true;
                }

            }

        }
        return aunJugable;
    }

    private static boolean verificarColumnaExistente(int j, int[][] tablero)
    {
        boolean respuesta=true;

        if (j < 0 || j > 6)
        {
            respuesta = false;
        }

        return respuesta;
    }

    private static boolean verificarColumnaLlena(int j, int[][] tablero)
    {
        boolean respuesta=true;      

        if (tablero[0][j] != 0)
        {
            respuesta = false;
        }

        return respuesta;
    }

    private static boolean buscarGanador(int jugadorActual, int[][] tablero)
    {

        for(int i = 0; i < tablero.length - 3; i++)
        {
            for(int j = 0; j < tablero[0].length; j++)
            {
                if (
                tablero[i][j] == jugadorActual   && 
                tablero[i+1][j] == jugadorActual &&
                tablero[i+2][j] == jugadorActual &&
                tablero[i+3][j] == jugadorActual){
                    return true;
                }
            }
        }

        for(int i = 0; i < tablero.length - 3; i++)
        {
            for(int j = 0; j < tablero[0].length - 3; j++)
            {
                if (
                tablero[i][j] == jugadorActual   && 
                tablero[i+1][j+1] == jugadorActual &&
                tablero[i+2][j+2] == jugadorActual &&
                tablero[i+3][j+3] == jugadorActual){
                    return true;
                }
            }
        }

        for(int i = 3; i < tablero.length; i++)
        {
            for(int j = 0; j < tablero[0].length - 3; j++)
            {
                if (
                tablero[i][j] == jugadorActual   && 
                tablero[i-1][j+1] == jugadorActual &&
                tablero[i-2][j+2] == jugadorActual &&
                tablero[i-3][j+3] == jugadorActual){
                    return true;
                }
            }
        }

        for(int i = 0; i<tablero.length; i++)
        {
            for (int j = 0;j < tablero[0].length - 3;j++)
            {
                if (
                tablero[i][j] == jugadorActual && 
                tablero[i][j+1] == jugadorActual &&
                tablero[i][j+2] == jugadorActual &&
                tablero[i][j+3] == jugadorActual)
                {
                    return true;
                }
            }           
        }
        return false;
    }
}