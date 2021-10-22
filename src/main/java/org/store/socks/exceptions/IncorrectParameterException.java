package org.store.socks.exceptions;

/**
 * The type Incorrect parameter exception.
 */
public class IncorrectParameterException extends Exception {

  /**
   * Instantiates a new Incorrect parameter exception.
   */
  public IncorrectParameterException() {
    super();
  }

  /**
   * Instantiates a new Incorrect parameter exception.
   *
   * @param massage the massage
   */
  public IncorrectParameterException(String massage) {
    super(massage);
  }

  /**
   * Instantiates a new Incorrect parameter exception.
   *
   * @param massage the massage
   * @param cause   the cause
   */
  public IncorrectParameterException(String massage, Throwable cause) {
    super(massage, cause);
  }

  /**
   * Instantiates a new Incorrect parameter exception.
   *
   * @param cause the cause
   */
  public IncorrectParameterException(Throwable cause) {
    super(cause);
  }
}
