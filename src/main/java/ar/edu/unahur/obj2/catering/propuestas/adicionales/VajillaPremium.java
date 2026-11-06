package ar.edu.unahur.obj2.catering.propuestas.adicionales;

import ar.edu.unahur.obj2.catering.propuestas.Propuesta;

public class VajillaPremium extends Adicional {
    private static final double PRECIO = 12_000;

    public VajillaPremium(Propuesta propuesta) {
        super(propuesta);
    }

    @Override
    protected String getNombreAdicional() {
        return "Vajilla premium";
    }

    @Override
    public double getPrecio() {
        return propuesta.getPrecio() + PRECIO;
    }
}
