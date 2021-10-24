package org.store.socks.repository.interfaces;

import java.util.List;
import org.store.socks.dto.SocksDto;
import org.store.socks.entity.Socks;
import org.store.socks.exceptions.ExistException;
import org.store.socks.exceptions.QuantityException;


/**
 * The interface Socks repository.
 */
public interface SocksRepository {

  /**
   * Input.
   *
   * @param socks the socks
   * @throws NullPointerException the null pointer exception
   */
  void input(SocksDto socks);

  /**
   * Output.
   *
   * @param socks the socks
   * @throws NullPointerException the null pointer exception
   * @throws QuantityException    the quantity exception
   * @throws ExistException       the exist exception
   */
  void output(SocksDto socks) throws QuantityException, ExistException;

  /**
   * Gets all where more than values.
   *
   * @param cottonPart the cotton part
   * @param color      the color
   * @return the all where more than values
   * @throws ExistException the exist exception
   */
  List<Socks> getAllWhereMoreThanValues(Integer cottonPart, String color) throws ExistException;

  /**
   * Gets all where less than values.
   *
   * @param cottonPart the cotton part
   * @param color      the color
   * @return the all where less than values
   * @throws ExistException the exist exception
   */
  List<Socks> getAllWhereLessThanValues(Integer cottonPart, String color) throws ExistException;

  /**
   * Gets all where equals than values.
   *
   * @param cottonPart the cotton part
   * @param color      the color
   * @return the all where equals than values
   * @throws ExistException the exist exception
   */
  List<Socks> getAllWhereValuesEquals(Integer cottonPart, String color) throws ExistException;

  void deleteAll();
}
