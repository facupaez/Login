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
    
    private String idTipoUnidad;
    
    private String descripcion;
    
    private String idEstadoUnidad;
}
