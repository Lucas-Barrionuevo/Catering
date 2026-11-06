package ar.edu.unahur.obj2.catering.propuestas;

import ar.edu.unahur.obj2.catering.excepciones.PrecioNegativoException;

public class Servicio implements Propuesta {
    private final String nombre;
    private final double precio;

    public Servicio(String nombre, double precio) {
        if (precio < 0) {
            throw new PrecioNegativoException(precio);
        }
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String getDescripcion() {
        return nombre;
    }

    @Override
    public double getPrecio() {
        return precio;
    }

    @Override
    public int getCantidadServicios() {
        return 1;
    }

    @Override
    public boolean incluyeServicio(String nombreServicio) {
        return nombre.equals(nombreServicio);
    }
}
