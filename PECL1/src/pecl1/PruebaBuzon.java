/*
 * Programa que lanza tres lectores y tres escritores.
 * que se comunican a través de un buzón de mensajes.
 * Debe comprobarse que no se pierden los mensajes ni se leen dos veces
 */
package pecl1;

public class PruebaBuzon
{
    public static void main(String[] s) throws InterruptedException 
    {
        Buzon buzon = new Buzon();
        Productor p1 = new Productor("A",buzon);
        Productor p2 = new Productor("B",buzon);
        Productor p3 = new Productor("C",buzon);
        Consumidor c1 = new Consumidor("Jose",buzon);
        Consumidor c2 = new Consumidor("Ana",buzon);
        Consumidor c3 = new Consumidor("Maria",buzon);
        p1.start();
        p2.start();
        p3.start();
        c1.start();
        c2.start();
        c3.start();
    }
}
