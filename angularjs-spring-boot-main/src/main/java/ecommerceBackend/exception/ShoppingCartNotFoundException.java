package ecommerceBackend.exception;

public class ShoppingCartNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ShoppingCartNotFoundException(Long id) {
        super("Could not find shopping cart " + id);
    }
}
