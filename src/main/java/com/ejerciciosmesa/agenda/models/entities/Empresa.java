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
@Table(name="empresa")
public class Empresa implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@NotBlank
@Size(max=50)
@Column(name="nombre")
private String nombre;


@Column(name="personacontacto")
private String personaContacto;


@NotBlank
@Size(max=20)
@Column(name="telefono1")
private String telefono1;


@NotBlank
@Size(max=20)
@Column(name="telefono2")
private String telefono2;


@Size(max=100)
@Column(name="correoelectronico")
private String correoElectronico;



	
	public Empresa() {}


	public Long getId() {
		return id;
	}


	public String getNombre() {
		return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getPersonaContacto() {
		return personaContacto;
}
public void setPersonaContacto(String personaContacto) {
	this.personaContacto = personaContacto;
}
public String getTelefono1() {
		return telefono1;
}
public void setTelefono1(String telefono1) {
	this.telefono1 = telefono1;
}
public String getTelefono2() {
		return telefono2;
}
public void setTelefono2(String telefono2) {
	this.telefono2 = telefono2;
}
public String getCorreoElectronico() {
		return correoElectronico;
}
public void setCorreoElectronico(String correoElectronico) {
	this.correoElectronico = correoElectronico;
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
		Empresa other = (Empresa) obj;
		return Objects.equals(id, other.id);
	}


	private static final long serialVersionUID = 1L;
	
}
