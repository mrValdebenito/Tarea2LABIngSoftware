package cl.valdebenito.usuarios.ev2.repository;

import cl.valdebenito.usuarios.ev2.DetalleVariantesAplicadas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleVariantesAplicadasRepository extends JpaRepository<DetalleVariantesAplicadas, Integer> {
    List<DetalleVariantesAplicadas> findByDetalleCotizacionIdDetalle(int idDetalle);
}