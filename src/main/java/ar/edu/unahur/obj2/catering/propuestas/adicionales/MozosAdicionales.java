package ar.edu.unahur.obj2.catering.propuestas.adicionales;

import ar.edu.unahur.obj2.catering.propuestas.Propuesta;

public class MozosAdicionales extends Adicional {
    private static final double PRECIO = 18_000;

    public MozosAdicionales(Propuesta propuesta) {
        super(propuesta);
    }

    @Override
    protected String getNombreAdicional() {
        return "Mozos adicionales";
    }

    @Override
    public double getPrecio() {
        return propuesta.getPrecio() + PRECIO;
    }
}
