package cl.valdebenito.usuarios.ev2;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_variantes_aplicadas")
public class DetalleVariantesAplicadas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDetalleVariante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detalle", nullable = false)
    private DetalleCotizacion detalleCotizacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_variante", nullable = false)
    private Variante variante;

    public DetalleVariantesAplicadas() {
    }

    public DetalleVariantesAplicadas(DetalleCotizacion detalleCotizacion, Variante variante) {
        this.detalleCotizacion = detalleCotizacion;
        this.variante = variante;
    }

    public int getIdDetalleVariante() {
        return idDetalleVariante;
    }

    public void setIdDetalleVariante(int idDetalleVariante) {
        this.idDetalleVariante = idDetalleVariante;
    }

    public DetalleCotizacion getDetalleCotizacion() {
        return detalleCotizacion;
    }

    public void setDetalleCotizacion(DetalleCotizacion detalleCotizacion) {
        this.detalleCotizacion = detalleCotizacion;
    }

    public Variante getVariante() {
        return variante;
    }

    public void setVariante(Variante variante) {
        this.variante = variante;
    }
}