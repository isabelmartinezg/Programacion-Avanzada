/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package P2L;

import java.net.ServerSocket;

public class hiloAccept extends Thread{
    
    private ProgPrincipal servidor;
    public hiloAccept(ProgPrincipal s)
    {
        this.servidor=s;
    }
    public void run()
    {
        servidor.aceptarClientes();
    }
}
