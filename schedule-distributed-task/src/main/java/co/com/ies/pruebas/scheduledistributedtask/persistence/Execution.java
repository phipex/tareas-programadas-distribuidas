package co.com.ies.pruebas.scheduledistributedtask.persistence;

import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "execution")
public class Execution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "called")
    private ZonedDateTime called;

    @Column(name = "start")
    private ZonedDateTime start;

    @Column(name = "finish")
    private ZonedDateTime finish;

    @Column(name = "resolve")
    private String resolve;

    @Column(name = "launch")
    private String launch;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Execution execution = (Execution) o;
        return id != null && Objects.equals(id, execution.id);
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}