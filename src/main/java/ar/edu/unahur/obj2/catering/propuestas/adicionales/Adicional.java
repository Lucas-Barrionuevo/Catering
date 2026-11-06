package ar.edu.unahur.obj2.catering.propuestas.adicionales;

import ar.edu.unahur.obj2.catering.propuestas.Propuesta;

public abstract class Adicional implements Propuesta {
    protected final Propuesta propuesta;

    protected Adicional(Propuesta propuesta) {
        this.propuesta = propuesta;
    }

    protected abstract String getNombreAdicional();

    @Override
    public final String getDescripcion() {
        return propuesta.getDescripcion() + " + " + getNombreAdicional();
    }

    @Override
    public abstract double getPrecio();

    @Override
    public int getCantidadServicios() {
        return propuesta.getCantidadServicios();
    }

    @Override
    public boolean incluyeServicio(String nombreServicio) {
        return propuesta.incluyeServicio(nombreServicio);
    }
}
