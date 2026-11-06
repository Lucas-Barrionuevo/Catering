package ar.edu.unahur.obj2.catering;

import ar.edu.unahur.obj2.catering.excepciones.PrecioNegativoException;
import ar.edu.unahur.obj2.catering.propuestas.Servicio;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ServicioTest {
    @Test
    void dadoUnServicio_alConsultarSusDatos_devuelvePrecioDescripcionYUnaUnidad() {
        Servicio servicio = new Servicio("Parrillada completa", 240_000);

        assertEquals("Parrillada completa", servicio.getDescripcion());
        assertEquals(240_000, servicio.getPrecio());
        assertEquals(1, servicio.getCantidadServicios());
        assertTrue(servicio.incluyeServicio("Parrillada completa"));
        assertFalse(servicio.incluyeServicio("Mesa dulce"));
    }

    @Test
    void dadoUnServicioConPrecioNegativo_alCrearlo_lanzaPrecioNegativoException() {
        assertThrows(PrecioNegativoException.class,
                () -> new Servicio("Servicio inválido", -1));
    }

}
