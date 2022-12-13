package ecommerceBackend.exception;

public class ShoppingCartItemNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ShoppingCartItemNotFoundException(Long id) {
        super("Could not find shopping cart item" + id);
    }
}
