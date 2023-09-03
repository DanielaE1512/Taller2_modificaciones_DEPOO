package modelo;

import java.util.Objects;

public class Bebida implements Producto {
	
	// Atributos
	
	private String nombre;
	private int precioBase;
	private int calorias;
	
	// Constructores
	
	public Bebida(String nombre,int precioBase,int calorias)
	{
		this.nombre = nombre;
		this.precioBase= precioBase;
		this.calorias= calorias;
	}

    // Métodos para consultar los atributos
	public int getPrecio() {
		
		return this.precioBase;
	}

	
	public String getNombre() {
		
		return this.nombre;
	}
	
	
	public int getCalorias() {
		
		return this.calorias;
	}


	//Otros métodos
	public String generarTextoFactura() {
		
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

        Bebida otraBebida = (Bebida) obj;
        return nombre == otraBebida.getNombre() &&
               Objects.equals(precioBase, otraBebida.getPrecio())&&
               Objects.equals(calorias, otraBebida.getCalorias());
    }

	
}
