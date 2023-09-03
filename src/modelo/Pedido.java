package modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Pedido {

	// Atributos
	
	private static int numeroPedidos = 0;
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<Producto> itemsPedido;
	
	// Constructores
	
	public Pedido (String nombreCliente, String direccionCliente) {
		
		this.nombreCliente= nombreCliente;
		this.direccionCliente = direccionCliente;
		this.idPedido = ++numeroPedidos;
		itemsPedido= new ArrayList<Producto>();
	}
	
	// Otros métodos
	
	public int getIdPedido() {
		
		return this.idPedido;
	}
	
	public void agregarProducto(Producto itemPedido) {
		itemsPedido.add(itemPedido);
	}
	
	private int getPrecioNetoPedido()
	{
		int precioNeto= 0;
		for (Producto item: itemsPedido)
		{
			precioNeto += item.getPrecio();
		}
		return precioNeto;
	}
	
	private int getPrecioTotalPedido()
	{
		double precioNeto = (double)getPrecioNetoPedido();
		double iva = precioNeto*0.19;
		int precioiva = (int) iva;
		return precioiva + getPrecioNetoPedido();
	}
	
	private int getPrecioIVAPedido()
	{
		double precioNeto = (double)getPrecioNetoPedido();
		double iva = precioNeto*0.19;
		int precioiva = (int) iva;
		return precioiva;
	}
	
	private int getCalorias()
	{
		int calorias= 0;
		for (Producto item: itemsPedido)
		{
			calorias += item.getCalorias();
		}
		return calorias;
	}
	
	private String generarTextoFactura()
	{
		String factura= "\n" + nombreCliente + "\n"+ direccionCliente;
		for (Producto item: itemsPedido)
		{
			factura += "\n" + item.generarTextoFactura();
		}
		
		factura += "\n\n Calorías totales " + getCalorias();
		factura += "\n Precio Neto $ " + getPrecioNetoPedido();
		factura += "\n IVA $" + getPrecioIVAPedido();
		factura += "\n Precio Total $ " + getPrecioTotalPedido();
		return factura;
	}
	
	public void guardarFactura(File archivo)
	{
		
		try {
            FileWriter fileWriter = new FileWriter(archivo);
            
            fileWriter.write(generarTextoFactura());
            fileWriter.close();
            
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }

	}
	// Sobreescribir método equals
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		Pedido otroPedido = (Pedido) obj;
		
		boolean respuesta = true;
		for (Producto item: this.itemsPedido)
		{
			for (Producto item2: otroPedido.itemsPedido)
			{
				respuesta = item.equals(item2);
			}
			
			if (!respuesta)
			{
				break;
			}
		}
		
		return respuesta;
	}
	
}
