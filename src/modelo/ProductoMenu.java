package modelo;

import java.util.Objects;

public class ProductoMenu implements Producto{
	//** Atributos
	
			private String nombre;
			
			private int precioBase;
			
			private int calorias;
			
		//** Constructor
			
			public ProductoMenu(String nombre, int precioBase, int calorias )
			
			{
				this.nombre=nombre;
				this.precioBase= precioBase;
				this.calorias= calorias;
			}
			
		// Métodos para consultar los atributos
			
			public String getNombre()
			{
				return nombre;
			}
			
			public int getPrecio()
			{
				return precioBase;
			}
			
			public int getCalorias() {
				
				return calorias;
			}
			
		// Otros métodos
			
			public String generarTextoFactura() 
			{
				return "\n" + nombre + "  cal:" + String.valueOf(calorias)+ "  $ " + String.valueOf(precioBase);
			}

		
			// Sobreescribir método equals
			public boolean equals(Object obj) {
		        if (this == obj) {
		            return true;
		        }
		        if (obj == null || getClass() != obj.getClass()) {
		            return false;
		        }

		        ProductoMenu otroProductoMenu = (ProductoMenu) obj;
		        return nombre == otroProductoMenu.getNombre() &&
		               Objects.equals(precioBase, otroProductoMenu.getPrecio())&&
		               Objects.equals(calorias, otroProductoMenu.getCalorias());
		    }

			

		
			
}
