package co.com.ies.pruebas.scheduledistributedtask;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "slow_view")
public class VistaLenta {
    @Id
    @Column(name = "numero", nullable = false)
    private Long id;

}