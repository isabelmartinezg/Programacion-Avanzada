/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package P2L;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class hiloCliente extends Thread{
    
    ProgPrincipal servidor;
    DataOutputStream salida;
    DataInputStream entrada;
    String mensaje;
    Socket conexion;
    int numero;
    public hiloCliente(ProgPrincipal s, Socket conexion)
    {
        try {
            this.servidor=s;   
            this.conexion=conexion;
            this.salida=new DataOutputStream(conexion.getOutputStream());
            this.entrada=new DataInputStream(conexion.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(hiloCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void run()
    {
        try {
            do
            {
                try {
                    mensaje=entrada.readUTF();
                } catch (IOException ex) {
                    Logger.getLogger(ProgPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (mensaje.equals("parar"))
                {
                    servidor.pararExposicion();
                }else if(mensaje.equals("reanudar"))
                {
                    servidor.reanudarExposicion();
                }
            }while(!mensaje.equals("salir"));
            System.out.println("Salida");
            entrada.close();
            salida.close();
            conexion.close();
        } catch (IOException ex) {
            Logger.getLogger(ProgPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
