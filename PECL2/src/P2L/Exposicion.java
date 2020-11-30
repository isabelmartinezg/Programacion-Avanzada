package P2L;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

public class Exposicion
{
    int aforo;
    boolean parar=false;
    Lock cerrojo= new ReentrantLock();
    Condition stop = cerrojo.newCondition();
    ListaThreads colaEspera, dentro;
    Semaphore semaforo;
    
    public Exposicion(int aforo, JTextField tfEsperan, JTextField tfDentro)
    {
        this.aforo=aforo;
        semaforo=new Semaphore(aforo,true);
        colaEspera=new ListaThreads(tfEsperan);
        dentro=new ListaThreads(tfDentro);
    }
    
    public void entrar(Visitante v)
    {
        colaEspera.meter(v);
        mirarSiParar();
        try
        {
            semaforo.acquire();
        } catch(InterruptedException e){ }
        mirarSiParar();
        colaEspera.sacar(v);
        dentro.meter(v);
    }

    public void salir(Visitante v)
    {
        mirarSiParar();
        dentro.sacar(v);
        mirarSiParar();
        semaforo.release();
    }
    public void parar()
    {
        parar=true;
    }
    public void mirarSiParar()
    {
        cerrojo.lock();
        try {
            if(parar)
            {
                try {
                    stop.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Exposicion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } finally {
            cerrojo.unlock();
        }
    }
    public void reanudar()
    {
        cerrojo.lock();
        try {
            parar=false;
            stop.signalAll();
        } finally {
            cerrojo.unlock();
        }
    }
    public void mirar(Visitante v)
    {
        mirarSiParar();
        try
        {
            Thread.sleep(2000+(int)(3000*Math.random()));
        } catch (InterruptedException e){ }
    }
}
