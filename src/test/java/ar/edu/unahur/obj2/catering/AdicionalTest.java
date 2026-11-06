package ar.edu.unahur.obj2.catering;

import ar.edu.unahur.obj2.catering.catalogo.Catalogo;
import ar.edu.unahur.obj2.catering.propuestas.Propuesta;
import ar.edu.unahur.obj2.catering.propuestas.Servicio;
import ar.edu.unahur.obj2.catering.propuestas.adicionales.MozosAdicionales;
import ar.edu.unahur.obj2.catering.propuestas.adicionales.ServicioNocturno;
import ar.edu.unahur.obj2.catering.propuestas.adicionales.VajillaPremium;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AdicionalTest {
    @Test
    void dadoCadaAdicional_alAplicarloCalculaSuPrecioYDescripcion() {
        Propuesta servicio = new Servicio("Brindis de bienvenida", 50_000);

        Propuesta conMozos = new MozosAdicionales(servicio);
        assertEquals(68_000, conMozos.getPrecio());
        assertEquals("Brindis de bienvenida + Mozos adicionales", conMozos.getDescripcion());

        Propuesta conVajilla = new VajillaPremium(servicio);
        assertEquals(62_000, conVajilla.getPrecio());
        assertEquals("Brindis de bienvenida + Vajilla premium", conVajilla.getDescripcion());

        Propuesta nocturno = new ServicioNocturno(servicio);
        assertEquals(60_000, nocturno.getPrecio());
        assertEquals("Brindis de bienvenida + Servicio nocturno", nocturno.getDescripcion());
    }

    @Test
    void dadoUnMenuParrillero_conMozosYServicioNocturno_enDistintoOrdenCalculaPreciosDistintos() {
        Propuesta menu = buscarEnCatalogo("Menú Parrillero");
        Propuesta mozosLuegoNocturno = new ServicioNocturno(new MozosAdicionales(menu));
        Propuesta nocturnoLuegoMozos = new MozosAdicionales(new ServicioNocturno(menu));

        assertEquals(432_000, mozosLuegoNocturno.getPrecio());
        assertEquals("Menú Parrillero + Mozos adicionales + Servicio nocturno",
                mozosLuegoNocturno.getDescripcion());
        assertEquals(428_400, nocturnoLuegoMozos.getPrecio());
        assertEquals("Menú Parrillero + Servicio nocturno + Mozos adicionales",
                nocturnoLuegoMozos.getDescripcion());
    }

    @Test
    void dadoUnAdicionalRepetido_alAplicarloDosVeces_sumaAmbasVeces() {
        Propuesta brindis = new Servicio("Brindis de bienvenida", 50_000);
        Propuesta vajillaRepetida = new VajillaPremium(new VajillaPremium(brindis));
        Propuesta nocturnoRepetido = new ServicioNocturno(
                new ServicioNocturno(new Servicio("Mesa dulce", 60_000)));

        assertEquals(74_000, vajillaRepetida.getPrecio());
        assertEquals("Brindis de bienvenida + Vajilla premium + Vajilla premium",
                vajillaRepetida.getDescripcion());
        assertEquals(86_400, nocturnoRepetido.getPrecio());
        assertEquals("Mesa dulce + Servicio nocturno + Servicio nocturno",
                nocturnoRepetido.getDescripcion());
    }

    @Test
    void dadoUnAdicionalSobreUnMenu_alConsultarMantieneCantidadEInclusion() {
        Propuesta evento = buscarEnCatalogo("Evento Completo");
        Propuesta conVajilla = new VajillaPremium(evento);

        assertEquals(530_800, conVajilla.getPrecio());
        assertEquals(6, conVajilla.getCantidadServicios());
        assertTrue(conVajilla.incluyeServicio("DJ y sonido"));
        assertFalse(conVajilla.incluyeServicio("Fotógrafo"));
    }

    @Test
    void dadoUnAdicional_alAplicarlo_noModificaLaPropuestaOriginal() {
        Propuesta menu = buscarEnCatalogo("Menú Parrillero");
        new MozosAdicionales(menu);

        assertEquals(342_000, menu.getPrecio());
        assertEquals("Menú Parrillero", menu.getDescripcion());
    }

    private Propuesta buscarEnCatalogo(String descripcion) {
        return Catalogo.inicial().getPropuestas().stream()
                .filter(propuesta -> propuesta.getDescripcion().equals(descripcion))
                .findFirst()
                .orElseThrow();
    }
}
