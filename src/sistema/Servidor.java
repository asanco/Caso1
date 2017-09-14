package sistema;

public class Servidor extends Thread
{
	private Buffer buffer;
	
	public Servidor(Buffer pBuffer)
	{
		buffer = pBuffer;
		System.out.println("Servidor creado.");
	}
	
	public void run()
	{
		while(!buffer.acabo())
		{
		Mensaje m = buffer.retirarMensaje();
		while(m==null)
		{
			try 
			{
				buffer.esperar();
				m = buffer.retirarMensaje();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		m.aumentar();
		System.out.println("Mensaje devuelto " + m.getNumero());
		try 
		{
			m.notificar();
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		}
		
	}
}
