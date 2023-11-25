package com.ApiRest.SkillChallengeApiRest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
public class Producto {
    @Id
    @Column(name = "id")
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idProducto;
    @Column
    @NotEmpty
    private String nombre;
    @Column
    @NotEmpty
    private String descripcion;
    @Column
    @Min(value = 1)
    private float precioUnitario;
    @Column
    @NotEmpty
    private String categoria;
    @Column
    private int estatus;
    @Column
    private String detallesEspecificos;
    @Column
    private long vendedor_id;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private List<Oferta> Ofertas;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    Set<DetalleTransaccion> detalles;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private List<Carrito> carritos;


}
