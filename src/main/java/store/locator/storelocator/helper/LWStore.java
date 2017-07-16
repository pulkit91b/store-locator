package store.locator.storelocator.helper;

public class LWStore {

	private String storeUID;

	private String name;

	private String address;

	private Integer zipcode;

	private Double longitute;

	private Double latitude;

	// distance of the store from the given Zipcode
	private Double distance;

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public String getStoreUID() {
		return storeUID;
	}

	public void setStoreUID(String storeUID) {
		this.storeUID = storeUID;
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

}
