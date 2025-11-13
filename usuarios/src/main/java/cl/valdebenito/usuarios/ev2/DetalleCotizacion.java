package cl.valdebenito.usuarios.ev2;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "detalle_cotizacion")
public class DetalleCotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDetalle;

    @Column(nullable = false)
    private int cantidad;

    @Column(name = "precio_unitario_final", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitarioFinal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cotizacion", nullable = false)
    private Cotizacion cotizacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mueble", nullable = false)
    private Mueble mueble;

    @OneToMany(mappedBy = "detalleCotizacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<DetalleVariantesAplicadas> variantesAplicadas;

    public DetalleCotizacion() {
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitarioFinal() {
        return precioUnitarioFinal;
    }

    public void setPrecioUnitarioFinal(BigDecimal precioUnitarioFinal) {
        this.precioUnitarioFinal = precioUnitarioFinal;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    public Mueble getMueble() {
        return mueble;
    }

    public void setMueble(Mueble mueble) {
        this.mueble = mueble;
    }

    public Set<DetalleVariantesAplicadas> getVariantesAplicadas() {
        return variantesAplicadas;
    }

    public void setVariantesAplicadas(Set<DetalleVariantesAplicadas> variantesAplicadas) {
        this.variantesAplicadas = variantesAplicadas;
    }
}