package ar.edu.unahur.obj2.catering.propuestas;

import ar.edu.unahur.obj2.catering.excepciones.DescuentoInvalidoException;
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

    @Override
    public String getDescripcion() {
        return nombre;
    }

    @Override
    public double getPrecio() {
        double precioSinDescuento = propuestas.stream()
                .mapToDouble(Propuesta::getPrecio)
                .sum();
        return precioSinDescuento * (1 - descuento / 100);
    }

    @Override
    public int getCantidadServicios() {
        return propuestas.stream()
                .mapToInt(Propuesta::getCantidadServicios)
                .sum();
    }

    @Override
    public boolean incluyeServicio(String nombreServicio) {
        return propuestas.stream()
                .anyMatch(propuesta -> propuesta.incluyeServicio(nombreServicio));
    }
}
