package cl.valdebenito.usuarios.ev2;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "variante")
public class Variante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVariante;

    @Column(name = "nombre_variante", nullable = false, length = 100)
    private String nombreVariante;

    @Column(name = "precio_adicional", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioAdicional;

    public Variante() {
    }

    public Variante(String nombreVariante, BigDecimal precioAdicional) {
        this.nombreVariante = nombreVariante;
        this.precioAdicional = precioAdicional;
    }

    public int getIdVariante() {
        return idVariante;
    }

    public void setIdVariante(int idVariante) {
        this.idVariante = idVariante;
    }

    public String getNombreVariante() {
        return nombreVariante;
    }

    public void setNombreVariante(String nombreVariante) {
        this.nombreVariante = nombreVariante;
    }

    public BigDecimal getPrecioAdicional() {
        return precioAdicional;
    }

    public void setPrecioAdicional(BigDecimal precioAdicional) {
        this.precioAdicional = precioAdicional;
    }
}