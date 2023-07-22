package co.com.ies.pruebas.scheduledistributedtask;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "slow_view")
public class SlowView {
    @Id
    @Column(name = "numero", nullable = false)
    private Long id;

}