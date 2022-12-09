package ecommerceBackend.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REVIEW")
public class Review {

	// NEED TO CHANGE EVERYTHING IN HERE
    private @Id @GeneratedValue Long id;

    private String description;
    private Status status;

    public Review() {
    }

    public Review(String description, Status status) {

        this.description = description;
        this.status = status;
    }

    public Long getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Review))
            return false;
        Review review = (Review) o;
        return Objects.equals(this.id, review.id) && Objects.equals(this.description, review.description) && this.status == review.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.description, this.status);
    }

    @Override
    public String toString() {
        return "Review{" + "id=" + this.id + ", description='" + this.description + '\'' + ", status=" + this.status + '}';
    }
}