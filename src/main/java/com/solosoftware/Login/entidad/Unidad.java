package com.solosoftware.Login.entidad;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "unidad")
public class Unidad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUnidad;

    private String dominio;

    @ManyToOne
    @JoinColumn(name = "idTipoUnidad")
    private TipoUnidad tipoUnidad;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "idEstadoUnidad")
    private EstadoUnidad estadoUnidad;
}
