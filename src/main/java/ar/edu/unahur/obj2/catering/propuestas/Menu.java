package ar.edu.unahur.obj2.catering.propuestas;

import ar.edu.unahur.obj2.catering.excepciones.DescuentoInvalidoException;
import ar.edu.unahur.obj2.catering.excepciones.PropuestaNoIncluidaException;
import java.util.ArrayList;
import java.util.List;

public class Menu implements Propuesta {
    private final String nombre;
    private final double descuento;
    private final List<Propuesta> propuestas = new ArrayList<>();

    public Menu(String nombre, double descuento) {
        if (!Double.isFinite(descuento) || descuento < 0 || descuento > 100) {
            throw new DescuentoInvalidoException(descuento);
        }
        this.nombre = nombre;
        this.descuento = descuento;
    }

    public void agregar(Propuesta propuesta) {
        propuestas.add(propuesta);
    }

    public void quitar(Propuesta propuesta) {
        if (!propuestas.remove(propuesta)) {
            throw new PropuestaNoIncluidaException();
        }
    }

    @Override
    public String getDescripcion() {
        return nombre;
    }

    @Override
    public double getPrecio() {
        double precioSinDescuento = 0;
        for (Propuesta propuesta : propuestas) {
            precioSinDescuento += propuesta.getPrecio();
        }
        return precioSinDescuento * (1 - descuento / 100);
    }

    @Override
    public int getCantidadServicios() {
        int cantidadServicios = 0;
        for (Propuesta propuesta : propuestas) {
            cantidadServicios += propuesta.getCantidadServicios();
        }
        return cantidadServicios;
    }

    @Override
    public boolean incluyeServicio(String nombreServicio) {
        for (Propuesta propuesta : propuestas) {
            if (propuesta.incluyeServicio(nombreServicio)) {
                return true;
            }
        }
        return false;
    }
}
