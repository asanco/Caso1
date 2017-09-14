package sistema;

import java.util.ArrayList;

public class Buffer 
{
	private ArrayList<Mensaje> mensajes;
	private int espaciosDisponibles;
	private int tamBuffer;
	private int numClientes;

	public Buffer(int pTamanioBuffer, int pNumClientes)
	{
		numClientes = pNumClientes-1;
		mensajes = new ArrayList<Mensaje>();
		espaciosDisponibles = pTamanioBuffer;
		tamBuffer = pTamanioBuffer;
	}

	public synchronized boolean agregarMensaje(Mensaje pMensaje)
	{
		if(espaciosDisponibles > 0)
		{
			mensajes.add(pMensaje);
			System.out.println("Mensaje con número " + pMensaje.getNumero() + " agregado.");
			espaciosDisponibles--;
			notifyAll();
			return true;
		}
		else
		{
			return false;
		}
	}


	public synchronized void clienteRetirado()
	{
		espaciosDisponibles++;
		numClientes--;
	}
	
	public int darEspaciosDisponibles()
	{
		return espaciosDisponibles;
	}
	
	public Mensaje darMensaje()
	{
		return mensajes.get(0);
	}

	public synchronized Mensaje retirarMensaje()
	{
		Mensaje m = null;
		if(espaciosDisponibles<tamBuffer)
		{			
			m = mensajes.get(0);
			mensajes.remove(0);
			System.out.println("Leyendo mensaje..."); 
			espaciosDisponibles++;
		}
		notifyAll();
		return m;
	}
	
	public synchronized void esperar() throws InterruptedException
	{
		wait();
	}
	
	public synchronized boolean acabo()
	{
		System.out.println("numero clientes restantes: "+numClientes);
		return numClientes<=1;
	}

}
