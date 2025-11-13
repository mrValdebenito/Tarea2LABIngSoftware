package cl.valdebenito.usuarios.ev2;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "cotizacion")
public class Cotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCotizacion;

    @Column(name = "fecha", nullable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCotizacion estado;

    @OneToMany(mappedBy = "cotizacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<DetalleCotizacion> detalles;

    public enum EstadoCotizacion {
        Cotizado,
        Vendido
    }

    public Cotizacion() {
        this.estado = EstadoCotizacion.Cotizado;
        this.fecha = LocalDateTime.now();
    }

    public int getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(int idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public EstadoCotizacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoCotizacion estado) {
        this.estado = estado;
    }

    public Set<DetalleCotizacion> getDetalles() {
        return detalles;
    }

    public void setDetalles(Set<DetalleCotizacion> detalles) {
        this.detalles = detalles;
    }
}