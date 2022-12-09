package ecommerceBackend.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class User {
		
	@Column(name = "id")
	private @Id @GeneratedValue Long id;
//    private String bid;
	private String name;
	
	@Column(name ="shopping_cart_id")
	private Long shoppingCartId;
	
	@Column(name ="address_id")
	private Long addressId;
	
	@Column(nullable = false)
    private String firstName;
	
	@Column(nullable = false)
    private String lastName;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    private boolean signedIn;
    
//    @Column(name = "shopping_cart")
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "shopping_cart")
//    private ShoppingCart shoppingCart;
    
    
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "address_id", referencedColumnName = "id")
//    private Address address;
    
    public User() {
    	
    }
    
	public User(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.setName();
//		shoppingCart = new ShoppingCart();
//		this.address = new Address();
	}
	
//	public User(String firstName, String lastName, Address address) {
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.setName();
//		this.shoppingCart = new ShoppingCart(this);
//		this.address = new Address(address);
//	}

	// tbh not sure if we switcharoo to using only Bid, but keeping this temporarily
	public Long getId() {
//		
		return this.id;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getShoppingCartId() {
		return shoppingCartId;
	}

	public void setShoppingCartId(Long shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isSignedIn() {
		return signedIn;
	}

	public void setSignedIn(boolean signedIn) {
		this.signedIn = signedIn;
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
        		+ '}';
    }

	

}
