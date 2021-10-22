package org.store.socks.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.store.socks.exceptions.ExistException;
import org.store.socks.exceptions.IncorrectParameterException;
import org.store.socks.exceptions.QuantityException;

/**
 * The type Socks exception handler.
 */
@ControllerAdvice
public class SocksExceptionHandler {

  /**
   * Handler exist exception response entity.
   *
   * @return the response entity
   */
  @ExceptionHandler(ExistException.class)
  public ResponseEntity<String> handlerExistException() {
    return new ResponseEntity<>("Socks with such parameters does not exist",
        HttpStatus.BAD_REQUEST);
  }

  /**
   * Handler quantity exception response entity.
   *
   * @return the response entity
   */
  @ExceptionHandler(QuantityException.class)
  public ResponseEntity<String> handlerQuantityException() {
    return new ResponseEntity<>("Client tried make outcome more socks than storage have",
        HttpStatus.BAD_REQUEST);
  }

  /**
   * Handler incorrect parameter exception response entity.
   *
   * @return the response entity
   */
  @ExceptionHandler(IncorrectParameterException.class)
  public ResponseEntity<String> handlerIncorrectParameterException() {
    return new ResponseEntity<>("Incorrect operation parameter", HttpStatus.BAD_REQUEST);
  }
}
