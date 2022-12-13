package ecommerceBackend.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Review {
		
	private @Id @GeneratedValue Long id;
//    private String bid;
	
//	private String accountName;	
	
	private int rating;
	
	private String title;
	
	private String comment;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;
    
    public Review() {
    	
    }
    
	public Review(int rating, String title, String comment) {
		this.rating = rating;
		this.title = title;
		this.comment = comment;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	// tbh not sure if we switcharoo to using only Bid, but keeping this temporarily
	public Long getId() {
//		
		return this.id;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}	
	
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
        return Objects.hash(this.id, this.rating, this.title, this.comment);
//		return super.hashCode();
	}

	@Override
	public boolean equals(Object o) {
//		return super.equals(obj);
		 if (this == o)
	            return true;
	        if (!(o instanceof Review))
	            return false;
	        Review review = (Review) o;
	        return Objects.equals(this.id, review.id) && Objects.equals(this.rating, review.rating) 
        		&& Objects.equals(this.title, review.title) && Objects.equals(this.comment, review.comment);
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
        return "Review{" 
        		+ "id=" + this.id 
        		+ ", rating='" + this.rating
        		+ ", title='" + this.title
        		+ ", comment='" + this.comment
        		+ '}';
    }
}
