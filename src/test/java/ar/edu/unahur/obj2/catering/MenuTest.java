package ar.edu.unahur.obj2.catering;

import ar.edu.unahur.obj2.catering.catalogo.Catalogo;
import ar.edu.unahur.obj2.catering.excepciones.DescuentoInvalidoException;
import ar.edu.unahur.obj2.catering.propuestas.Menu;
import ar.edu.unahur.obj2.catering.propuestas.Propuesta;
import ar.edu.unahur.obj2.catering.propuestas.Servicio;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class MenuTest {
    @Test
    void dadoUnMenuConDescuentoMayorACien_alCrearlo_lanzaDescuentoInvalidoException() {
        assertThrows(DescuentoInvalidoException.class,
                () -> new Menu("Descuento excesivo", 101));
    }

    @Test
    void dadoUnMenuConServicios_alConsultarPrecioCantidadEInclusion_aplicaDescuento() {
        Menu menu = new Menu("Menú Parrillero", 10);
        menu.agregar(new Servicio("Mesa de entradas", 80_000));
        menu.agregar(new Servicio("Parrillada completa", 240_000));
        menu.agregar(new Servicio("Mesa dulce", 60_000));

        assertEquals("Menú Parrillero", menu.getDescripcion());
        assertEquals(342_000, menu.getPrecio());
        assertEquals(3, menu.getCantidadServicios());
        assertTrue(menu.incluyeServicio("Parrillada completa"));
        assertFalse(menu.incluyeServicio("DJ y sonido"));
    }

    @Test
    void dadoUnCatalogoInicial_alConsultarMenus_aplicaPreciosYOperaConMenusAnidados() {
        Catalogo catalogo = Catalogo.inicial();
        Propuesta packFiesta = buscarEnCatalogo(catalogo, "Pack Fiesta");
        assertEquals(256_500, packFiesta.getPrecio());
        assertEquals(2, packFiesta.getCantidadServicios());

        Propuesta eventoCompleto = buscarEnCatalogo(catalogo, "Evento Completo");
        assertEquals(518_800, eventoCompleto.getPrecio());
        assertEquals(6, eventoCompleto.getCantidadServicios());
        assertTrue(eventoCompleto.incluyeServicio("DJ y sonido"));
        assertFalse(eventoCompleto.incluyeServicio("Fotógrafo"));
    }

    private Propuesta buscarEnCatalogo(Catalogo catalogo, String descripcion) {
        return catalogo.getPropuestas().stream()
                .filter(propuesta -> propuesta.getDescripcion().equals(descripcion))
                .findFirst()
                .orElseThrow();
    }
}
