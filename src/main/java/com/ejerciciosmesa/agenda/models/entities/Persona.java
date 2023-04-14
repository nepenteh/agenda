package com.ejerciciosmesa.agenda.models.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;

import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;



@Entity
@Table(name="persona")
public class Persona implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@NotBlank
@Size(max=20)
@Column(name="nombre")
private String nombre;


@NotBlank
@Size(max=20)
@Column(name="apellido1")
private String apellido1;


@NotBlank
@Size(max=20)
@Column(name="apellido2")
private String apellido2;


@NotBlank
@Size(max=20)
@Column(name="telefono")
private String telefono;


@Size(max=100)
@Column(name="correoelectronico")
private String correoElectronico;


@Column(name="foto")
private String foto;



	
	public Persona() {}


	public Long getId() {
		return id;
	}


	public String getNombre() {
		return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getApellido1() {
		return apellido1;
}
public void setApellido1(String apellido1) {
	this.apellido1 = apellido1;
}
public String getApellido2() {
		return apellido2;
}
public void setApellido2(String apellido2) {
	this.apellido2 = apellido2;
}
public String getTelefono() {
		return telefono;
}
public void setTelefono(String telefono) {
	this.telefono = telefono;
}
public String getCorreoElectronico() {
		return correoElectronico;
}
public void setCorreoElectronico(String correoElectronico) {
	this.correoElectronico = correoElectronico;
}
public String getFoto() {
		return foto;
}
public void setFoto(String foto) {
	this.foto = foto;
}

	

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(id, other.id);
	}


	private static final long serialVersionUID = 1L;
	
}
