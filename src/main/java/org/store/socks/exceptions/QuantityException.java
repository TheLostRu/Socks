package org.store.socks.exceptions;

/**
 * The type Quantity exception.
 */
public class QuantityException extends Exception {

  /**
   * Instantiates a new Quantity exception.
   */
  public QuantityException() {
    super();
  }

  /**
   * Instantiates a new Quantity exception.
   *
   * @param massage the massage
   */
  public QuantityException(String massage) {
    super(massage);
  }

  /**
   * Instantiates a new Quantity exception.
   *
   * @param massage the massage
   * @param cause   the cause
   */
  public QuantityException(String massage, Throwable cause) {
    super(massage, cause);
  }

  /**
   * Instantiates a new Quantity exception.
   *
   * @param cause the cause
   */
  public QuantityException(Throwable cause) {
    super(cause);
  }
}
