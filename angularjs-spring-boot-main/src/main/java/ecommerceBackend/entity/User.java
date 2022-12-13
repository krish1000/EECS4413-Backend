package ecommerceBackend.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class User {
		
	private @Id @GeneratedValue Long id;
//    private String bid;
	private String name;
	
//	private Long shoppingCartId;
	
	@Column(nullable = false)
    private String firstName;
	
	@Column(nullable = false)
    private String lastName;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false, unique = true)
    private String username;

	@Column(nullable = false)
    private String password;
    
    @OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="address_id")
	private List<Address> addresses = new ArrayList<Address>();
    
//    @JoinColumn(name = "shopping_cart_id", insertable = false, updatable = false)
    @OneToOne(cascade = CascadeType.ALL)
    private ShoppingCart shoppingCart = new ShoppingCart();
    //targetEntity = ShoppingCart.class, fetch = FetchType.LAZY
//    @JsonIgnore
    
//    @Column(name = "shopping_cart_id")
//    private Long shoppingCartId;
    
    public User() {
    	
    }
    
	public User(String firstName, String lastName, String email, String password, String username) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.username = username;
		this.setName();
	}

	// tbh not sure if we switcharoo to using only Bid, but keeping this temporarily
	public Long getId() {
//		
		return this.id;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
	
//	public Long getShoppingCartId() {
//		return shoppingCartId;
//	}
//
//	public void setShoppingCartId(Long shoppingCartId) {
//		this.shoppingCartId = shoppingCartId;
//	}
	
//	public void addAddress

//	public Long getAddressId() {
//		return addressId;
//	}
//
//	public void setAddressId(Long addressId) {
//		this.addressId = addressId;
//	}
	
//	public String getBid() {
//		return bid;
//	}
//
//	public void setBid(String bid) {
//		this.bid = bid;
//	}

	public String getName() {
		return this.name;
	}
	
	// used to set full name after every first name change or/and last name change
	private void setName() {
		this.name = this.firstName + " " + this.lastName;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
		this.setName();
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
		this.setName();
	}
	
//	public Address getAddress() {
//		return this.address;
//	}
//	
//	public ShoppingCart getShoppingCart() {
//		return this.shoppingCart;
//	}
//	
//	public ShoppingCart setShoppingCart(ShoppingCart sc) {
//		return this.shoppingCart = sc;
//	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	
	
	// do equals method
	@Override
	public int hashCode() {
        return Objects.hash(this.id, this.firstName, this.lastName);
//		return super.hashCode();
	}

	@Override
	public boolean equals(Object o) {
//		return super.equals(obj);
		 if (this == o)
	            return true;
	        if (!(o instanceof User))
	            return false;
	        User user = (User) o;
	        return Objects.equals(this.id, user.id) && Objects.equals(this.firstName, user.firstName) && Objects.equals(this.lastName, user.lastName);
	        //FINISH EQUALS
	}
	//hascode
	
//    private String bid;
//    private String name;
//    private String description;
//    private String type;
//    private String brand;
//    private int quantity;
//    private int price;
	@Override
    public String toString() {
        return "User{" 
        		+ "id=" + this.id 
        		+ ", name='" + this.name
        		+ ", firstName='" + this.firstName
        		+ ", lastName='" + this.lastName
        		+ ", email='" + this.email
        		+ ", username='" + this.username
        		+ ", password='" + this.password
        		+ '}';
    }


}
