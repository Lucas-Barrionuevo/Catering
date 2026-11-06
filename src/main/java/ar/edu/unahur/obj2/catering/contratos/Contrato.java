package ar.edu.unahur.obj2.catering.contratos;

import ar.edu.unahur.obj2.catering.propuestas.Propuesta;

public class Contrato {
    private final String nombreCliente;
    private final double importe;
    private final String descripcion;

    public Contrato(String nombreCliente, Propuesta propuesta) {
        this.nombreCliente = nombreCliente;
        this.importe = propuesta.getPrecio();
        this.descripcion = propuesta.getDescripcion();
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public double getImporte() {
        return importe;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
