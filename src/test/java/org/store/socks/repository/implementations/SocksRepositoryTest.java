package org.store.socks.repository.implementations;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.store.socks.dto.SocksDto;
import org.store.socks.entity.Socks;
import org.store.socks.exceptions.ExistException;
import org.store.socks.exceptions.QuantityException;
import org.store.socks.repository.interfaces.SocksRepository;

/**
 * The type Socks repository test.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class SocksRepositoryTest {

  @Autowired
  private SocksRepository repository;

  private SocksDto socksDto;

  /**
   * Init.
   */
  @BeforeEach
  public void init() {
    socksDto = new SocksDto("blue", 90, 10);
  }

  /**
   * Delete.
   */
  @AfterEach
  public void delete() {
    repository.deleteAll();
  }

  /**
   * Input in empty record test.
   *
   * @throws ExistException the exist exception
   */
  @Test
  public void inputInEmptyRecordTest() throws ExistException {
    repository.input(socksDto);
    var socks = initSocks(socksDto.getColor(), socksDto.getCottonPart());
    assertEquals(socksDto.getColor(), socks.getColor());
    assertEquals(socksDto.getCottonPart(), socks.getCottonPart());
    assertEquals(socksDto.getQuantity(), socks.getQuantity());
  }

  /**
   * Input in not empty record test.
   *
   * @throws ExistException the exist exception
   */
  @Test
  public void inputInNotEmptyRecordTest() throws ExistException {
    repository.input(socksDto);
    repository.input(socksDto);
    var socks = initSocks(socksDto.getColor(), socksDto.getCottonPart());
    assertEquals(socksDto.getColor(), socks.getColor());
    assertEquals(socksDto.getCottonPart(), socks.getCottonPart());
    assertEquals(socksDto.getQuantity()*2, socks.getQuantity());
  }

  /**
   * Output test.
   *
   * @throws QuantityException the quantity exception
   * @throws ExistException    the exist exception
   */
  @Test
  public void outputTest() throws QuantityException, ExistException {
    repository.input(socksDto);
    repository.input(socksDto);
    repository.output(socksDto);
    var socks = initSocks(socksDto.getColor(), socksDto.getCottonPart());
    assertEquals(socksDto.getColor(), socks.getColor());
    assertEquals(socksDto.getCottonPart(), socks.getCottonPart());
    assertEquals(socksDto.getQuantity(), socks.getQuantity());
  }

  /**
   * Output test should throw ex.
   */
  @Test
  public void outputTestShouldThrowEx() {
    repository.input(socksDto);
    socksDto.setQuantity(socksDto.getQuantity()+5);
    assertThrows(QuantityException.class, () -> repository.output(socksDto));
  }

  /**
   * Gets all where less than values should work correctly.
   *
   * @throws ExistException the exist exception
   */
  @Test
  public void getAllWhereLessThanValuesShouldWorkCorrectly() throws ExistException {
    repository.input(socksDto);
    var socksList = repository.getAllWhereLessThanValues(
        socksDto.getCottonPart()+5,
        socksDto.getColor());
    var socks = socksList.get(0);
    assertEquals(socksDto.getColor(), socks.getColor());
    assertEquals(socksDto.getCottonPart(), socks.getCottonPart());
    assertEquals(socksDto.getQuantity(), socks.getQuantity());
  }

  /**
   * Gets methods should throw ex.
   */
  @Test
  public void getMethodsShouldThrowEx() {
    assertThrows(ExistException.class, () -> repository.getAllWhereLessThanValues(
        socksDto.getCottonPart(),
        socksDto.getColor())
    );
    assertThrows(ExistException.class, () -> repository.getAllWhereMoreThanValues(
        socksDto.getCottonPart(),
        socksDto.getColor())
    );
    assertThrows(ExistException.class, () -> repository.getAllWhereValuesEquals(
        socksDto.getCottonPart(),
        socksDto.getColor())
    );
  }

  /**
   * Gets all where more than values should work correctly.
   *
   * @throws ExistException the exist exception
   */
  @Test
  public void getAllWhereMoreThanValuesShouldWorkCorrectly() throws ExistException {
    repository.input(socksDto);
    var socksList = repository.getAllWhereMoreThanValues(
        socksDto.getCottonPart()-5,
        socksDto.getColor());
    var socks = socksList.get(0);
    assertEquals(socksDto.getColor(), socks.getColor());
    assertEquals(socksDto.getCottonPart(), socks.getCottonPart());
    assertEquals(socksDto.getQuantity(), socks.getQuantity());
  }

  private Socks initSocks(String color, Integer cottonPart) throws ExistException {
    var socksList = repository.getAllWhereValuesEquals(cottonPart, color);
    return socksList.get(0);
  }
}
