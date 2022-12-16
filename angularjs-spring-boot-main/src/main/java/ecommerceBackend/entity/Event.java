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
public class Event {
		
	private @Id @GeneratedValue Long id;
	
	private String description;
	
	private String quantityOfItems;
	
	private int totalValueOfItems;
	
//	private String ipAddress;
    
    public Event() {
    	
    }
    
    public Event(String description) {
    	this.description = description;
    }
	public Event(String description, String quantityOfItems, int totalValueOfItems) {
		this.description = description;
		this.quantityOfItems = quantityOfItems;
		this.setTotalValueOfItems(totalValueOfItems);
//		this.ipAddress = ipAddress;
	}

	// tbh not sure if we switcharoo to using only Bid, but keeping this temporarily
	public Long getId() {
//		
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public String getIpAddress() {
//		return ipAddress;
//	}
//
//	public void setIpAddress(String ipAddress) {
//		this.ipAddress = ipAddress;
//	}
	
	
//	public String getBid() {
//		return bid;
//	}
//
//	public void setBid(String bid) {
//		this.bid = bid;
//	}

	
	
	
	// do equals method
	@Override
	public int hashCode() {
        return Objects.hash(this.id);
//		return super.hashCode();
	}

	@Override
	public boolean equals(Object o) {
//		return super.equals(obj);
		 if (this == o)
	            return true;
	        if (!(o instanceof Event))
	            return false;
	        Event event = (Event) o;
	        return Objects.equals(this.id, event.id);
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
        return "Event{" 
        		+ "id=" + this.id
        		+ '}';
    }

	public String getQuantityOfItems() {
		return quantityOfItems;
	}

	public void setQuantityOfItems(String quantityOfItems) {
		this.quantityOfItems = quantityOfItems;
	}

	public int getTotalValueOfItems() {
		return totalValueOfItems;
	}

	public void setTotalValueOfItems(int totalValueOfItems) {
		this.totalValueOfItems = totalValueOfItems;
	}


}
