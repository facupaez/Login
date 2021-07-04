package com.solosoftware.Login.entidad;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "estadounidad")
public class EstadoUnidad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstadoUnidad;

    private String nombre;

    private String descripcion;
}
