/*
 * La clase Productor define hilos que envían mensajes a un buzón de mensajes.
 * El nombre y el buzón se reciben como parámetros en el constructor.
 * Entre mensaje y mensaje, esperan un tiempo aleatorio entre 0.3 y 0.7 segundos.
 */
package pecl1;
import static java.lang.Thread.sleep;

public class Productor extends Thread
{
    private final String nombre;
    private final Buzon miBuzon;
    private int numero;
 
    public Productor(String nombre, Buzon buzon)
    {
        this.nombre=nombre;
        miBuzon=buzon;
    }
 
    public void run()
    {
        for(int i=1; i<=70; i++)
        {
            try
            {
                sleep((int)(300+400*Math.random()));
            } catch(InterruptedException ex){ }
            try {
                numero=(int)(Math.random()*20);
                miBuzon.enviaMensaje(nombre,numero);
            } catch(InterruptedException ex){  }
        }
    }
}
