package store.locator.storelocator.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "storeUID" }))
public class Store {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer storeId;

	private String storeUID;

	private String name;

	private String address;

	private Integer zipcode;

	private Double longitute;

	private Double latitude;

	@Transient
	private Double distance;

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getZipcode() {
		return zipcode;
	}

	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}

	public Double getLongitute() {
		return longitute;
	}

	public void setLongitute(Double longitute) {
		this.longitute = longitute;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getStoreUID() {
		return storeUID;
	}

	public void setStoreUID(String storeUID) {
		this.storeUID = storeUID;
	}

}
