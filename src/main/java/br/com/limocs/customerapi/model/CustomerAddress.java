package br.com.limocs.customerapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "customersAddress")
public class CustomerAddress implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 50, nullable = false)
	@NotEmpty(message = "The Street cannot be empty")
	private String street;
	@Column(length = 50, nullable = false)
	@NotEmpty(message = "The Number cannot be empty")
	private String number;
	@Column(length = 150, nullable = false)
	@NotEmpty(message = "The City cannot be empty")
	private String city;
	@Column(length = 50, nullable = false)
	@NotEmpty(message = "The State cannot be empty")
	private String state;
	@Column(length = 20, nullable = false)
	@NotEmpty(message = "The ZipCode cannot be empty")
	private String zipCode;
	@Column(length = 150, nullable = false)
	@NotEmpty(message = "The Country cannot be empty")
	private String Country;

	public CustomerAddress() {
	}

	public CustomerAddress(Long id, String street, String number, String city, String state, String zipCode,
			String country) {
		this.id = id;
		this.street = street;
		this.number = number;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		Country = country;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		CustomerAddress other = (CustomerAddress) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}
