/*
 * La clase Buzon tiene que estar protegida con un cerrojo
 * El método enviaMensaje debe esperar si el buzón está lleno
 * El método recibeMensaje debe esperar si el buzón está vacío.
 * Cuando un hilo completa su operación, desbloquea a los que estén esperando
 * para que puedan continuar intentando su acción.
 */
package pecl1;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buzon
{
    private int numero;
    ArrayList<Integer> buzon = new ArrayList<>();
    private int capacidad=buzon.size();
    private int resultado=0;
    private final Lock cerrojo = new ReentrantLock();
    private final Condition buzonLleno = cerrojo.newCondition();
    private final Condition buzonVacio = cerrojo.newCondition();

    
    public void enviaMensaje(String nombre,int n) throws InterruptedException
    {
        cerrojo.lock();
        while (capacidad==20){
            buzonLleno.await();
        }
        try{
            buzon.add(n);
            capacidad++;
            System.out.println(nombre+" genera "+n);
            buzonVacio.signal();
        }finally {
            cerrojo.unlock();
        }
    }

    public int recibeMensaje(String nombre) throws InterruptedException
    {
        cerrojo.lock();
        while (capacidad==0){
            buzonVacio.await();
        }
        try{
            numero = buzon.remove(0);
            capacidad--;
            resultado+=numero;
            System.out.println(nombre+" ha leido "+numero+", resultado: "+resultado);
            buzonLleno.signal();   
            return numero;
        } finally {
            cerrojo.unlock();
        } 
    }
    }