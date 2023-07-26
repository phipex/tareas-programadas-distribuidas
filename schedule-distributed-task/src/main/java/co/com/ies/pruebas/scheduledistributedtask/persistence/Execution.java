package co.com.ies.pruebas.scheduledistributedtask.persistence;

import lombok.Data;


import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
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

}