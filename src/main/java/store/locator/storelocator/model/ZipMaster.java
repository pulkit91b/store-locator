package store.locator.storelocator.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ZipMaster {

	@Id
	private Integer zipcode;

	private Double latitude;

	private Double longitude;

	public Integer getZipcode() {
		return zipcode;
	}

	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
}
