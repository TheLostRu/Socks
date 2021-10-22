package org.store.socks.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.store.socks.dto.SocksDto;
import org.store.socks.entity.Socks;
import org.store.socks.exceptions.ExistException;
import org.store.socks.exceptions.IncorrectParameterException;
import org.store.socks.exceptions.QuantityException;
import org.store.socks.repository.interfaces.SocksRepository;

/**
 * The type Socks service.
 */
@Slf4j
@Service
public class SocksService {

  /**
   * The Repository.
   */
  @Autowired
  SocksRepository repository;

  /**
   * Input.
   *
   * @param socksDto the socks dto
   * @throws NullPointerException the null pointer exception
   */
  public void input(SocksDto socksDto) throws NullPointerException {
    repository.input(socksDto);
  }

  /**
   * Output.
   *
   * @param socksDto the socks dto
   * @throws NullPointerException the null pointer exception
   * @throws QuantityException    the quantity exception
   * @throws ExistException       the exist exception
   */
  public void output(SocksDto socksDto) throws NullPointerException, QuantityException, ExistException {
    repository.output(socksDto);
  }

  /**
   * Get list.
   *
   * @param cottonPart the cotton part
   * @param color      the color
   * @param operation  the operation
   * @return the list
   * @throws ExistException              the exist exception
   * @throws IncorrectParameterException the incorrect parameter exception
   */
  public List<Socks> get(Integer cottonPart, String color, String operation)
      throws ExistException, IncorrectParameterException {
    List<Socks> result;
    switch (operation) {
      case "moreThan" -> result = repository.getAllWhereMoreThanValues(cottonPart, color);
      case "lessThan" -> result = repository.getAllWhereLessThanValues(cottonPart, color);
      case "equal" -> result = repository.getAllWhereValuesEquals(cottonPart, color);
      default -> throw new IncorrectParameterException("Incorrect operation");
    }
    return result;
  }
}
