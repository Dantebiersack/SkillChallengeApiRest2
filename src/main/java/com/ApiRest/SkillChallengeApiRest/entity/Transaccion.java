package com.ApiRest.SkillChallengeApiRest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "transaccion")
@Data
@NoArgsConstructor
public class Transaccion {
    @Id
    @Column(name = "id")
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idTransaccion;
    @Column
    @Min(value = 1)
    private float montoTotal;
    @Column
    @NotEmpty
    private String paisEnvio;
    @Column
    @NotEmpty
    private String estadoEnvio;
    @Column
    @NotEmpty
    private String calleEnvio;
    @Column
    @NotEmpty
    private String coloniaEnvio;
    @Column
    @NotEmpty
    private String numEnvio;
    @Column
    private Date fechaEnvio;
    @Column
    @NotEmpty
    private String metodoPago;
    @Column
    @NotEmpty
    private String noTarjeta;
    @Column
    private int estatus;
    @Column
    private long comprador_id;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "transaccion_id")
    Set<DetalleTransaccion> detalles;
}
