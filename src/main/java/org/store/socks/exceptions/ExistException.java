package org.store.socks.exceptions;

/**
 * The type Exist exception.
 */
public class ExistException extends Exception{

  /**
   * Instantiates a new Exist exception.
   */
  public ExistException() {
    super();
  }

  /**
   * Instantiates a new Exist exception.
   *
   * @param massage the massage
   */
  public ExistException(String massage) {
    super(massage);
  }

  /**
   * Instantiates a new Exist exception.
   *
   * @param massage the massage
   * @param cause   the cause
   */
  public ExistException(String massage, Throwable cause) {
    super(massage, cause);
  }

  /**
   * Instantiates a new Exist exception.
   *
   * @param cause the cause
   */
  public ExistException(Throwable cause) {
    super(cause);
  }
}
