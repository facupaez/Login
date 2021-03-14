package com.solosoftware.Login.entidad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idUsuario;

    @NotEmpty
    String nombre;

    @NotEmpty
    String apellido;

    @NotEmpty
    String email;

    @NotEmpty
    String password;

    @Transient
    String confirmaPassword;

    @OneToMany
    @JoinColumn(name = "idUsuario")
    List<Rol> roles;

}
