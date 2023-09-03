package modelo;

import java.util.Objects;

public class Ingrediente {
	
	//** Atributos
	
	private String nombre;
	private int costoAdicional;
	private int calorias;
	
	//** Constructores
	
	public Ingrediente(String nombre, int costoAdicional, int calorias) 
	{
		this.nombre = nombre;
		this.costoAdicional= costoAdicional;
		this.calorias=calorias;
		
	}
	
	// Métodos para consultar los atributos
	
	public String getNombre()
	{
		return nombre;
	}
	
	public int getCostoAdicional()
	{
		return costoAdicional;
	}
	
	public int getCalorias() 
	{
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

		Ingrediente otroIngrediente = (Ingrediente) obj;
		return nombre == otroIngrediente.getNombre() &&
				this.costoAdicional == otroIngrediente.getCostoAdicional()&&
				this.calorias== otroIngrediente.getCalorias();
	}
				
}
