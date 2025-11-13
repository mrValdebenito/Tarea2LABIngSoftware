package cl.valdebenito.usuarios.ev2.repository;

import cl.valdebenito.usuarios.ev2.Variante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VarianteRepository extends JpaRepository<Variante, Integer> {
}