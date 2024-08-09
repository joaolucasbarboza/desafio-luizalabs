package com.luizalabs.wishlist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    private ResponseEntity<RestErrorMessage> productNotFoundHandler(ProductNotFoundException exception) {
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(ExistsProductOnWishlistExeception.class)
    private ResponseEntity<RestErrorMessage> productNotFoundHandler(ExistsProductOnWishlistExeception exception) {
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorMessage);
    }

    @ExceptionHandler(ProductNotExistsOnWishlistException.class)
    private ResponseEntity<RestErrorMessage> productNotFoundHandler(ProductNotExistsOnWishlistException exception) {
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorMessage);
    }

    @ExceptionHandler(WishlistNotFoundException.class)
    private ResponseEntity<RestErrorMessage> WishlistFullSizeHandler(WishlistNotFoundException exception) {
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorMessage);
    }

    @ExceptionHandler(WishlistFullSizeException.class)
    private ResponseEntity<RestErrorMessage> WishlistFullSizeHandler(WishlistFullSizeException exception) {
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorMessage);
    }
}
