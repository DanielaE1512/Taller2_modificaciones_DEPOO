package modelo;

import java.util.ArrayList;
import java.util.Objects;

public class ProductoAjustado implements Producto {

	//Atributos
	
	private Producto base;
	private ArrayList<Ingrediente> agregados;
	private ArrayList<Ingrediente> eliminados;
	
	// Constructor
	
	public ProductoAjustado (Producto producto)
	{
		this.base= producto;
		agregados= new ArrayList<Ingrediente>();
		eliminados= new ArrayList<Ingrediente>();
	}
	
	// Otros métodos
	
	public void agregarIngrediente(Ingrediente ingrediente)
	{
		agregados.add(ingrediente);
	}
	
	public void eliminarIngrediente(Ingrediente ingrediente)
	{
		eliminados.add(ingrediente);
	}
	
	public int getPrecio() {
		
		int precio = base.getPrecio();
		
		for (Ingrediente ingrediente: agregados)
		{
			precio += ingrediente.getCostoAdicional();
		}
		
		
	
		return precio;
	}
	
	public int getCalorias()
	{
		int calorias = base.getCalorias();
		for (Ingrediente ingrediente: agregados)
		{
			calorias += ingrediente.getCalorias();
		}
		for (Ingrediente ingrediente: eliminados)
		{
			calorias -= ingrediente.getCalorias();
		}
		
		return calorias;
	}

	@Override
	public String getNombre() {
		
		String nombre = base.getNombre();
		
	
		return nombre;
	}

	
	public String generarTextoFactura() {
		
		String factura = "\n"+base.getNombre() + "  cal: "+ base.getCalorias()+ "  $" + String.valueOf(base.getPrecio());
		int costo = 0;
		
		for (Ingrediente ingrediente: agregados) 
		{
		
				factura += "\n +" + ingrediente.getNombre()+ "  cal: " + ingrediente.getCalorias()+ "  $ " + String.valueOf(ingrediente.getCostoAdicional());
				costo +=ingrediente.getCostoAdicional();
		}
		
		for (Ingrediente ingrediente: eliminados) 
		{
		
				factura += "\n -" + ingrediente.getNombre()+ "  cal:" +ingrediente.getCalorias();
		
		}
		
		factura += "\n Calorías totales de " +  base.getNombre() +": "+ String.valueOf(getCalorias());
		factura += "\n Costo total de " +  base.getNombre()+ " ajustado: " + "$ " + String.valueOf(getPrecio());
		return factura;
	}
	
	// Sobreescribir método equals
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null || getClass() != obj.getClass()) {
				return false;
			}

			ProductoAjustado otroProductoAjustado = (ProductoAjustado) obj;
			
			boolean listaAgregados = true;
			for (Ingrediente agregado: this.agregados)
			{
				
				for (Ingrediente agregado2 : otroProductoAjustado.agregados)
				{
				
					listaAgregados = agregado.equals(agregado2);
					if (!listaAgregados)
					{
						break;
					}
				}
				if (!listaAgregados)
				{
					break;
				}
			}
			
			boolean listaEliminados = true;
			if (listaAgregados)	
			{
				for (Ingrediente eliminado: this.eliminados)
				{

					for (Ingrediente eliminado2 : otroProductoAjustado.eliminados)
					{

						listaEliminados = eliminado.equals(eliminado2);
		
					}
					if (!listaEliminados)
					{
						break;
					}
				}
			}
			return getNombre() == otroProductoAjustado.getNombre() && listaAgregados && listaEliminados;
		}

}
