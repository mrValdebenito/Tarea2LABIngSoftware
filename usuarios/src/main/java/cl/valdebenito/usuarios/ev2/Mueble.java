package cl.valdebenito.usuarios.ev2;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "mueble")
public class Mueble {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMueble;

    @Column(name = "nombre_mueble", nullable = false, length = 100)
    private String nombreMueble;

    @Column(nullable = false, length = 50)
    private String tipo;

    @Column(name = "precio_base", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioBase;

    @Column(nullable = false)
    private int stock;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoMueble estado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TamañoMueble tamaño;

    @Column(nullable = false, length = 50)
    private String material;

    public enum EstadoMueble {
        activo,
        inactivo
    }

    public enum TamañoMueble {
        Grande,
        Mediano,
        Pequeño
    }

    public Mueble() {
    }

    public Mueble(String nombreMueble, String tipo, BigDecimal precioBase, int stock, EstadoMueble estado, TamañoMueble tamaño, String material) {
        this.nombreMueble = nombreMueble;
        this.tipo = tipo;
        this.precioBase = precioBase;
        this.stock = stock;
        this.estado = estado;
        this.tamaño = tamaño;
        this.material = material;
    }

    public int getIdMueble() {
        return idMueble;
    }

    public void setIdMueble(int idMueble) {
        this.idMueble = idMueble;
    }

    public String getNombreMueble() {
        return nombreMueble;
    }

    public void setNombreMueble(String nombreMueble) {
        this.nombreMueble = nombreMueble;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(BigDecimal precioBase) {
        this.precioBase = precioBase;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public EstadoMueble getEstado() {
        return estado;
    }

    public void setEstado(EstadoMueble estado) {
        this.estado = estado;
    }

    public TamañoMueble getTamaño() {
        return tamaño;
    }

    public void setTamaño(TamañoMueble tamaño) {
        this.tamaño = tamaño;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}