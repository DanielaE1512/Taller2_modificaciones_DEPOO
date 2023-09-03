package consola;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import modelo.Pedido;
import modelo.Restaurante;
import modelo.Producto;
import modelo.Combo;
import modelo.Ingrediente;
import modelo.ProductoAjustado;
import modelo.ProductoMenu;

public class Aplicacion {
	
	private Restaurante restaurante = new Restaurante();

	
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		try {
            Aplicacion consola = new Aplicacion();
            consola.restaurante.cargarInformacionRestaurante(new File("data/ingredientes.txt"),
                                                            new File("data/menu.txt"),
                                                            new File("data/combos.txt"),
                                                            new File ("data/bebidas.txt"));
            consola.ejecutarAplicacion();
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró un archivo: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de lectura/escritura: " + e.getMessage());
        }

	}
	
	
	public void ejecutarAplicacion() throws FileNotFoundException, IOException
	{
		
		
		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1)
					ejecutarMenu();
				else if (opcion_seleccionada == 2 && restaurante != null)
					ejecutarNuevoPedido();
				else if (opcion_seleccionada == 3 && restaurante != null)
					ejecutarAgregarElementoPedido();
				else if (opcion_seleccionada == 4 && restaurante != null)
					ejecutarCerrarYGuardarFactura();
				else if (opcion_seleccionada == 5 && restaurante != null)
					ejecutarConsultarPedido();
				else if (opcion_seleccionada == 6)
				{
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}
	
				else
				{
					System.out.println("Por favor seleccione una opción válida.");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
	}
	
	


	public void mostrarMenu()
	{
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("1. Mostar menú");
		System.out.println("2. Iniciar un nuevo Pedido");
		System.out.println("3. Agregar un elemento a un pedido");
		System.out.println("4. Cerrar un pedio y guardar factura");
		System.out.println("5. Consultar la información de un pedido dado su id");
		System.out.println("6. Salir de la aplicación");
		
	}
	
	private void ejecutarMenu()
	{
		ArrayList<Producto> productosmenu= restaurante.getMenuBase();
		ArrayList<Combo> combos = restaurante.getCombos();
		ArrayList<Ingrediente> ingredientes = restaurante.getIngredientes();
		ArrayList<Producto> bebidas = restaurante.getBebidas();
		
		int i = 1;
		System.out.println("\nProductos menu: \n");
		
		for (Producto producto: productosmenu)
		{
			System.out.println(i +" "+ producto.getNombre()+ " $ "+ producto.getPrecio());
			i += 1;
		}
		
		i= 1;
		System.out.println("\nCombos: \n");
		for (Combo combo: combos)
		{
			System.out.println(i +" "+ combo.getNombre()+ " \n Precio combo:$ "+ combo.getPrecio());
			
			i += 1;
		}
		
		i= 1;
		System.out.println("\nIngredientes: \n");
		for (Ingrediente ingrediente: ingredientes)
		{
			System.out.println(i +" "+ ingrediente.getNombre()+ "   $ "+ ingrediente.getCostoAdicional());
			
			i += 1;
		}
		i= 1;
		System.out.println("\nBebidas: \n");
		for (Producto bebida: bebidas)
		{
			System.out.println(i +" "+ bebida.getNombre()+ "   $ "+ bebida.getPrecio());
			
			i += 1;
		}
		
	}
	private void ejecutarNuevoPedido()
	{
		String nombreCliente = input("Digite su nombre");
		String direccionCliente = input("Digite su dirección");
		
		restaurante.iniciarPedido(nombreCliente,direccionCliente);
		System.out.println("El id de su pedido es: " + restaurante.getPedidoEnCurso().getIdPedido());
		
	}
	
	private void ejecutarAgregarElementoPedido()
	{
		ArrayList<Producto> productosmenu= restaurante.getMenuBase();
		ArrayList<Combo> combos = restaurante.getCombos();
		ArrayList<Ingrediente> ingredientes = restaurante.getIngredientes();
		ArrayList<Producto> bebidas = restaurante.getBebidas();
		Pedido pedido = restaurante.getPedidoEnCurso();
		if (pedido == null)
		{
			System.out.println("Por favor cree un nuevo pedido");
			return;
		}
		int tipo = Integer.parseInt(input("Item deseado: \n 1) combo \n 2) producto \n 3) bebida"));
		
		
		if (tipo == 1) 
		{
			int numeroCombo = Integer.parseInt(input("Ingrese el número del combo deseado"));
			pedido.agregarProducto(combos.get(numeroCombo-1));

		} else if (tipo == 2 | tipo==3) {
			int numeroProducto = 0;
			Producto item = null;
			
			if (tipo==2)
			{
				 numeroProducto = Integer.parseInt(input("Ingrese el número del producto deseado"));
				 item = productosmenu.get(numeroProducto-1);
			}else if(tipo==3){
				
				numeroProducto = Integer.parseInt(input("Ingrese el número de la bebida deseada"));
				item = bebidas.get(numeroProducto-1);
			}
			
			int cambios = Integer.parseInt(input("1) Modificar producto \n2) No modificar producto"));

			if (cambios == 2)
			{
				pedido.agregarProducto(item);
				
			}else if (cambios== 1)
			{
				
				ProductoAjustado productoAjustado= new ProductoAjustado(item);
				pedido.agregarProducto(productoAjustado);
				boolean modificaciones= true;
				while (modificaciones)
				{
					int cambioProducto = Integer.parseInt(input("\n1) Eliminar Ingrediente \n2) Agregar Ingrediente"));

					if (cambioProducto==1)
					{
						int numeroIngrediente = Integer.parseInt(input("Ingrese el número del ingrediente que desea elimianar"));
						productoAjustado.eliminarIngrediente(ingredientes.get(numeroIngrediente-1));
					}else if (cambioProducto==2)
					{
						int numeroIngrediente = Integer.parseInt(input("Ingrese el número del ingrediente que desea agregar"));
						productoAjustado.agregarIngrediente(ingredientes.get(numeroIngrediente-1));
					}else {
						System.out.println("Opción inválida. Por favor, seleccione 1 o 2.");
					}
					modificaciones = Boolean.parseBoolean(input("Deasea seguir haciendo modificaciones (true/false)"));
				}
			}


		} else {
			System.out.println("Opción inválida. Por favor, seleccione 1, 2 o 3.");
		}
				
		
	}
	
	private void ejecutarCerrarYGuardarFactura()
	{
		if (restaurante.getPedidoEnCurso()!= null)
		{
			Pedido nuevoPedido = restaurante.getPedidoEnCurso();
			ArrayList<Pedido> pedidos = restaurante.getPedidos();
			
			boolean verificacion= false;
			for (Pedido pedido: pedidos)
			{
				verificacion= nuevoPedido.equals(pedido);
			}
			
			if (verificacion)
			{
				System.out.println("\n Existe un pedido idéntico");
			} else {
				System.out.println("\n No hay un pedido igual");
			}
			restaurante.cerrarYGuardarPedido();
		}
		
	}
	
	private void ejecutarConsultarPedido()
	{
		
			int IdPedido = Integer.parseInt(input("Digite id del pedido que desea consultar"));
			

			String nombreArchivo = "data/facturas/" + String.valueOf(IdPedido); // Reemplaza con la ruta de tu archivo

	        try {
	            FileReader fileReader = new FileReader(nombreArchivo);
	            BufferedReader bufferedReader = new BufferedReader(fileReader);

	            String linea;
	            while ((linea = bufferedReader.readLine()) != null) {
	                System.out.println(linea);
	            }

	            bufferedReader.close();
	        } catch (IOException e) {
	            System.out.println("Error al leer el archivo: " + e.getMessage());
	        }
	    
	}
	





	

	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
}
