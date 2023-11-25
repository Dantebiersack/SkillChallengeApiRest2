package com.ApiRest.SkillChallengeApiRest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Entity
@NoArgsConstructor
@Table (name = "oferta")
@Data
public class Oferta {
    @Id
    @Column
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idOferta;
    @Column
    @Min(value = 1)
    private float descuento;
    @Column
    private Date fechaValidez;
    @Column
    @Min(value = 1)
    private long producto_id;
}
