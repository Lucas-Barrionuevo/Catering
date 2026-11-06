package ar.edu.unahur.obj2.catering.carta;

import java.util.ArrayList;
import java.util.List;

public class LoteModificaciones implements Modificacion {
    private final List<Modificacion> modificaciones;
    private final List<Modificacion> modificacionesAplicadas = new ArrayList<>();

    public LoteModificaciones(List<Modificacion> modificaciones) {
        this.modificaciones = List.copyOf(modificaciones);
    }

    @Override
    public void execute() {
        modificacionesAplicadas.clear();
        try {
            for (Modificacion modificacion : modificaciones) {
                modificacion.execute();
                modificacionesAplicadas.add(modificacion);
            }
        } catch (RuntimeException excepcion) {
            undo();
            throw excepcion;
        }
    }

    @Override
    public void undo() {
        for (int indice = modificacionesAplicadas.size() - 1; indice >= 0; indice--) {
            modificacionesAplicadas.get(indice).undo();
        }
        modificacionesAplicadas.clear();
    }

    @Override
    public List<Modificacion> modificacionesParaHistorial() {
        List<Modificacion> modificacionesDelHistorial = new ArrayList<>();
        for (Modificacion modificacion : modificaciones) {
            modificacionesDelHistorial.addAll(modificacion.modificacionesParaHistorial());
        }
        return List.copyOf(modificacionesDelHistorial);
    }
}
