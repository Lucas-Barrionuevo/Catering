package ar.edu.unahur.obj2.catering.propuestas.adicionales;

import ar.edu.unahur.obj2.catering.propuestas.Propuesta;

public class ServicioNocturno extends Adicional {
    private static final double RECARGO = 0.20;

    public ServicioNocturno(Propuesta propuesta) {
        super(propuesta);
    }

    @Override
    protected String getNombreAdicional() {
        return "Servicio nocturno";
    }

    @Override
    public double getPrecio() {
        return propuesta.getPrecio() * (1 + RECARGO);
    }
}
