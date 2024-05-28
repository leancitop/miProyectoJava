package Cubo;
import java.util.ArrayList;
import java.util.List;

import Lectores.LectorArchivos;
import Tabla.Operador;
import Tabla.Tabla;

public class Main {
    public static void main(String[] args) {
        Cubo cubo = new Cubo("cubo");

        String[][] productos_csv = LectorArchivos.leerCSV("datasets/productos.csv");
        Tabla tabla_productos = new Tabla(4);
        tabla_productos.cargarTabla(productos_csv);
        Dimension productos = new Dimension("productos",5, tabla_productos, "id_producto");

        String[][] fechas_csv = LectorArchivos.leerCSV("datasets/fechas.csv");
        Tabla tabla_fechas = new Tabla(6);
        tabla_fechas.cargarTabla(fechas_csv);
        Dimension fechas = new Dimension("fechas",5, tabla_fechas, "id_fecha");

        cubo.agregarDimension(productos);
        cubo.agregarDimension(fechas);

        cubo.setDimensionesProyeccion(null);

        List<List<Object>> listaDimensionesProyeccion = new ArrayList<>();
        
        listaDimensionesProyeccion.add(List.of("fechas", 1));
        listaDimensionesProyeccion.add(List.of("productos", 2));

        cubo.setDimensionesProyeccion(listaDimensionesProyeccion);

        List<List<Object>> listaHechosDimension = new ArrayList<>();
        
        listaHechosDimension.add(List.of("valor_total", "suma"));
        listaHechosDimension.add(List.of("costo", "suma"));

        cubo.setHechosProyeccion(listaHechosDimension);

        cubo.setHecho(new Hecho("datasets/ventas.csv", 7));

        Operador.parsear(cubo.listaDimensiones, cubo.dimensionesProyeccion, cubo.tablaHecho, cubo.hechosProyeccion);
    }
}
