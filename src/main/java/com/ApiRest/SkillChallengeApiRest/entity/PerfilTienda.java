package com.ApiRest.SkillChallengeApiRest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "perfil_tienda")
@Data
@NoArgsConstructor
public class PerfilTienda {
    @Id
    @Column(name = "id")
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idPerfilTienda;
    @Column
    @NotEmpty
    private String nombreTienda;
    @Column
    @NotEmpty
    private String descripcion;
    @Column
    @NotEmpty
    private String telefono;
    @Column
    private int estatus;
    @Column
    private long vendedor_id;
}
