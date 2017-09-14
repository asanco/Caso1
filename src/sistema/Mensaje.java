package sistema;

public class Mensaje 
{
	private int numero;
	
	public Mensaje (int pNumero)
	{
		numero = pNumero;
	}
	
	public int getNumero()
	{
		return numero;
	}
	
	public void aumentar()
	{
		numero++;
	}
	
	public synchronized void esperarRespuesta() throws InterruptedException
	{
		wait();
	}
	
	public synchronized void notificar() throws InterruptedException
	{
		notify();
	}
	
}
