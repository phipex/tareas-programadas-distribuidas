package co.com.ies.pruebas.scheduledistributedtask.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SlowViewRepository extends JpaRepository<SlowView, Long>, JpaSpecificationExecutor<SlowView> {
    Optional<SlowView> findFirstByIdNotNull();
}