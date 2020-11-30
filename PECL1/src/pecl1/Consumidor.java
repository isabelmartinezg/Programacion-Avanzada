/*
 * La clase Consumidor define hilos que leen mensajes de un buzón de mensajes.
 * El buzón y el nombre, los reciben como parámetros del constructor.
 * Entre lectura y lectura, esperan un tiempo aleatorio entre 0.2 y 0.8 segundos.
 */
package pecl1;
import static java.lang.Thread.sleep;

public class Consumidor extends Thread
{
    private final String nombre;
    private final Buzon miBuzon;
    
    public Consumidor(String nombre, Buzon miBuzon)
    {
        this.nombre=nombre;
        this.miBuzon=miBuzon;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                sleep((int)(200+600*Math.random()));
            } catch(InterruptedException e){ }
            try {
                miBuzon.recibeMensaje(nombre);
            } catch (InterruptedException ex) { }
        }
    }
}
