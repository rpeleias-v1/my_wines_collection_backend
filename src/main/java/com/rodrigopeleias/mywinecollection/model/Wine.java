package com.rodrigopeleias.mywinecollection.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Wine {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	@Enumerated(EnumType.STRING)
	private Grape grape;
	
	@Enumerated(EnumType.STRING)
	private Classification classification;
	
	private int year;
	
	private String manufacturer;
	
	private float alcoholPercent;
	
	private String country;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Grape getGrape() {
		return grape;
	}

	public void setGrape(Grape grape) {
		this.grape = grape;
	}

	public Classification getClassification() {
		return classification;
	}

	public void setClassification(Classification classification) {
		this.classification = classification;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public float getAlcoholPercent() {
		return alcoholPercent;
	}

	public void setAlcoholPercent(float alcoholPercent) {
		this.alcoholPercent = alcoholPercent;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((grape == null) ? 0 : grape.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wine other = (Wine) obj;
		if (grape != other.grape)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (manufacturer == null) {
			if (other.manufacturer != null)
				return false;
		} else if (!manufacturer.equals(other.manufacturer))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Wine [id=" + id + ", name=" + name + ", grape=" + grape + ", classification=" + classification
				+ ", year=" + year + ", manufacturer=" + manufacturer + ", alcoholPercent=" + alcoholPercent
				+ ", country=" + country + "]";
	}
}
