package ar.edu.unahur.obj2.catering.carta;

import ar.edu.unahur.obj2.catering.propuestas.Servicio;

public class ActualizarPrecioServicio implements Modificacion {
    private final Servicio servicio;
    private final double nuevoPrecio;
    private double precioAnterior;
    private boolean aplicada;

    public ActualizarPrecioServicio(Servicio servicio, double nuevoPrecio) {
        this.servicio = servicio;
        this.nuevoPrecio = nuevoPrecio;
    }

    @Override
    public void execute() {
        precioAnterior = servicio.getPrecio();
        servicio.actualizarPrecio(nuevoPrecio);
        aplicada = true;
    }

    @Override
    public void undo() {
        if (aplicada) {
            servicio.actualizarPrecio(precioAnterior);
            aplicada = false;
        }
    }
}
