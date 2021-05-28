package com.solosoftware.Login.entidad;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.Data;

@Entity
@Data
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Size(min = 4, max = 15, message = "El nombre debe tener más de 3 carácteres")
    private String nombre;

    @Size(min = 4, max = 15, message = "El apellido debe tener más de 3 carácteres")
    private String apellido;

    @Email(message = "Email inválido")
    //@NotEmpty(message = "Completa este campo")
    private String email;

    @Size(min = 4, message = "La contraseña debe tener más de 3 carácteres")
    private String password;

    @Size(min = 4, message = "La contraseña debe tener más de 3 carácteres")
    @Transient
    private String confirmaPassword;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_roles",
             joinColumns = @JoinColumn(name = "idUsuario"),
             inverseJoinColumns = @JoinColumn(name = "idRol"))
    private Set roles;

}
