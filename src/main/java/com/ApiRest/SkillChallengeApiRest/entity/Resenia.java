package com.ApiRest.SkillChallengeApiRest.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "resenia")
@Data
@NoArgsConstructor
public class Resenia {
    @Id
    @Column(name = "id")
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idResenia;
    @Column
    @NotEmpty
    private String contenido;
    @Column
    @Min(value = 1)
    private int puntuacion;
    @Column
    private int estatus;
    @Column
    @Min(value = 1)
    private long comprador_id;
}
