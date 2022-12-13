package ecommerceBackend.exception;

public class ReviewNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ReviewNotFoundException(Long id) {
        super("Could not find review " + id);
    }
}
