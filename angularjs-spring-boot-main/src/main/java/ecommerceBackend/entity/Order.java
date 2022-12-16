package ecommerceBackend.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
public class Order {

    private @Id @GeneratedValue Long id;

//    private String description;
//    private Status status;
    
    private String creditCard;
    
//    @OneToOne(mappedBy = "order")
//    private User userId;
    
    @OneToOne(cascade = CascadeType.ALL)
    private ShoppingCart shoppingCart = new ShoppingCart();
  
	//maybe not needed to map to address table incase user doesn't want address to be saved after
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="addressId")
    private Address shippingAddress;
    
    private String date;
	
    public Order() {
    }

    
    public Order(String creditCard, ShoppingCart shoppingCart, Address shippingAddress) {
		this.creditCard = creditCard;
//		this.userId = userId;
		this.shoppingCart = shoppingCart;
		this.shippingAddress = shippingAddress;
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		this.date = dtf.format(now);
	}
    
    


//  return "Order{" + "id=" + this.id + ", description='" + this.description + '\'' + ", status=" + this.status + '}';

	@Override
	public String toString() {
		return "Order{" + "id=" + this.id + ", creditCard='" + creditCard + ", orderItems="
				+ shoppingCart + ", address=" + shippingAddress + "]";
	}


	public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


	public String getCreditCard() {
		return creditCard;
	}


	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}


	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}


	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}


	public Address getShippingAddress() {
		return shippingAddress;
	}


	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
    
	public String getDate() {
		return this.date;
	}
    

    
// ///////////////////// OLD:
//    @Override
//    public boolean equals(Object o) {
//
//        if (this == o)
//            return true;
//        if (!(o instanceof Order))
//            return false;
//        Order order = (Order) o;
//        return Objects.equals(this.id, order.id) && Objects.equals(this.description, order.description) && this.status == order.status;
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(this.id, this.description, this.status);
//    }

//    @Override
//    public String toString() {
//        return "Order{" + "id=" + this.id + ", description='" + this.description + '\'' + ", status=" + this.status + '}';
//    }
}