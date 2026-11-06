package ar.edu.unahur.obj2.catering.propuestas;

public interface Propuesta {
    String getDescripcion();

    double getPrecio();

    int getCantidadServicios();

    boolean incluyeServicio(String nombreServicio);
}
