package sistema;

import main.Main;

public class Cliente extends Thread
{
	private int numMensajes;
	private Buffer buffer;

	public Cliente(int pNumMensajes, Buffer pBuffer)
	{
		numMensajes = pNumMensajes;
		buffer=pBuffer;
		System.out.println("Cliente creado con " + pNumMensajes + " mensajes.");
	}

	public void run()
	{
		int i=0;
		int mensajesPendientes = numMensajes;
		Mensaje actual = null;
		while(mensajesPendientes > 0)
		{
			actual = new Mensaje(i);
			while(!buffer.agregarMensaje(actual))
			{
				Thread.yield();
			}
			try 
			{
				actual.esperarRespuesta();
				mensajesPendientes--;
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			i++;
			System.out.println("mensajes pendientes: "+mensajesPendientes);
		}
		
		buffer.clienteRetirado();
	}
}
