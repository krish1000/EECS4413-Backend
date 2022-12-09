package ecommerceBackend.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// On local connection; get url is: http://localhost:8080/demo/items

@Entity
public class Address {
	
	private @Id @GeneratedValue Long id;
//    private String bid;
    private Long userID;
    private String street;
    private String province;
    private String country;
    private String zip;
    private String phone;
    
    public Address() {
    }
    
	public Address(Long userID, String street, String province, String country, String zip, String phone) {
		//super();
		//not including id of address
		/**maybe we should also include orderid? OR order will contain addressID **/
		this.userID = userID;
		this.street = street;
		this.province = province;
		this.country = country;
		this.zip = zip;
		this.phone = phone;
	}

	// 
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	
	@Override
	public int hashCode() {
		return Objects.hash(country, id, phone, province, street, userID, zip);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(country, other.country) && Objects.equals(id, other.id)
				&& Objects.equals(phone, other.phone) && Objects.equals(province, other.province)
				&& Objects.equals(street, other.street) && Objects.equals(userID, other.userID)
				&& Objects.equals(zip, other.zip);
	}
	

//	private @Id @GeneratedValue Long id;
//   //  private String bid;
//  private Long userID;
//  private String street;
//  private String province;
//  private String country;
//  private String zip;
//  private String phone;
	@Override
    public String toString() {
        return "Address{" 
        		+ "id=" + this.id 
        		+ ", userID='" + this.userID + '\'' 
        		+ ", street='" + this.street + '\''
        		+ ", province='" + this.province + '\''
        		+ ", country='" + this.country + '\''
        		+ ", zip='" + this.zip + '\''
        		+ ", phone='" + this.phone
        		+ '}';
    }

	

}
