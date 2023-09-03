package modelo;

import java.util.ArrayList;
import java.util.Objects;

public class Combo implements Producto{
	//** Atributos
	
			private double descuento;
			
			private String nombreCombo;
			
			private ArrayList<Producto> itemsCombo;
			
	//** Constructores
			
			public Combo(double descuento, String nombreCombo) 
			{
				this.descuento = descuento;
				this.nombreCombo= nombreCombo;
				itemsCombo= new ArrayList<Producto>();
			}
			
	// Otros Métodos
			
			public void agregarItemACombo(Producto itemCombo)
			{
				itemsCombo.add(itemCombo);
			}
			
			
			public int getPrecio()
			{
				int precio = 0;
				
				for (Producto producto: itemsCombo) 
				{
					precio += producto.getPrecio()- producto.getPrecio()* descuento;
					
				}
				return precio;	
			}
			
			public String generarTextoFactura()
			{
				String factura = "\n"+ this.nombreCombo + ":" ;
				
				for (Producto producto: itemsCombo) 
				{
					
						factura += "\n" + producto.getNombre()+ "cal: "+ producto.getCalorias()+" $ " + String.valueOf(producto.getPrecio()-producto.getPrecio()* descuento);
					
						
				}
				
				factura += "\n calorías totales: "+ getCalorias()+"\n precio total combo"+ getPrecio();
					
				
				return factura;
			}
			
			public String getNombre()
			{
				String nombre= this.nombreCombo + ":" ;
				for (Producto producto: itemsCombo) 
				{
					nombre += "\n "+producto.getNombre();
						
				}
				return nombre;
			}

			
			public int getCalorias() {
				int calorias = 0;
				for (Producto producto:itemsCombo)
				{
					calorias += producto.getCalorias();
				}
				return calorias;
			}
			
			// Sobreescribir método equals
			public boolean equals(Object obj) {
				if (this == obj) {
					return true;
				}
				if (obj == null || getClass() != obj.getClass()) {
					return false;
				}

				Combo otroCombo = (Combo) obj;
				return this.nombreCombo == otroCombo.nombreCombo &&
						this.descuento == otroCombo.descuento &&
						Objects.equals(this.itemsCombo, otroCombo.itemsCombo);
			}

}
