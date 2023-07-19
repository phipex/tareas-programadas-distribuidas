package co.com.ies.pruebas.scheduledistributedtask;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VistaLentaRepository extends JpaRepository<VistaLenta, Long>, JpaSpecificationExecutor<VistaLenta> {
    Optional<VistaLenta> findFirstByIdNotNull();
}