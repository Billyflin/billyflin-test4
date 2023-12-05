package com.example.test4.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
public class Producto {

    @Id
    @Pattern(regexp="^[A-Z]\\d{10}$", message="El código del producto debe comenzar con la inicial de la Marca del automóvil en mayúscula, seguido de la fecha y hora de creación.")
    private String codigoProducto;

    @Size(max=30, message="El nombre debe contener a lo más 30 letras")
    private String nombre;

    @Min(value=0, message="El stock debe ser numérico")
    private int stock;

    @Min(value=0, message="El precio debe ser numérico")
    private double precio;

    @Pattern(regexp="^\\d{1,2}\\.\\d{3}\\.\\d{3}-[1-9kK]$", message="Debe ser un rut válido")
    private String rutProveedor;

    @Email(message="Debe tener un formato válido de mail")
    private String mailProveedor;

}
