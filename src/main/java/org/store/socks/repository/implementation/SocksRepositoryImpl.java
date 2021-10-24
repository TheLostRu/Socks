package org.store.socks.repository.implementation;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.store.socks.dto.SocksDto;
import org.store.socks.entity.Socks;
import org.store.socks.exceptions.ExistException;
import org.store.socks.exceptions.QuantityException;
import org.store.socks.repository.interfaces.SocksRepository;

/**
 * The type Socks repository.
 */
@Slf4j
@Repository
public class SocksRepositoryImpl implements SocksRepository {

  private final RowMapper<Socks> socksRowMapper = ((resultSet, i) -> {
    var socks = new Socks();
    socks.setId(resultSet.getInt("id"));
    socks.setColor(resultSet.getString("color"));
    socks.setCottonPart(resultSet.getInt("cotton_part"));
    socks.setQuantity(resultSet.getInt("quantity"));
    return socks;
  });

  private final RowMapper<Boolean> existsRowMapper = ((resultSet, i) ->
    resultSet.getBoolean("exists"));

  private final RowMapper<Integer> quantityRowMapper = ((resultSet, i) ->
      resultSet.getInt("quantity"));

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public void input(SocksDto socks) {
    if (isExistInBD(socks)) {
      jdbcTemplate.update("UPDATE socks SET quantity=quantity+? WHERE color=? AND cotton_part=?",
          socks.getQuantity(),
          socks.getColor(),
          socks.getCottonPart());
    } else {
      jdbcTemplate.update("INSERT INTO socks (color, cotton_part, quantity) VALUES(?,?,?)",
          socks.getColor(),
          socks.getCottonPart(),
          socks.getQuantity());
    }
  }

  @Override
  public void output(SocksDto socks) throws QuantityException, ExistException {
    if (isExistInBD(socks)) {
      var quantity = getQuantity(socks);
      if (quantity > 0) {
        jdbcTemplate.update("UPDATE socks SET quantity=? WHERE color=? AND cotton_part=?",
            quantity,
            socks.getColor(),
            socks.getCottonPart());
      } else if (quantity == 0) {
        jdbcTemplate.update("DELETE FROM socks WHERE color=? AND cotton_part=?",
            socks.getColor(),
            socks.getCottonPart());
      } else {
        throw new QuantityException("Output amount more than storage have");
      }
    } else {
      throw new ExistException("Socks does not exist in storage");
    }
  }

  @Override
  public List<Socks> getAllWhereMoreThanValues(Integer cottonPart, String color)
      throws ExistException {
    var result = jdbcTemplate.query(
        "SELECT * FROM socks WHERE color=? AND cotton_part>?",
        new Object[]{color, cottonPart},
        socksRowMapper);
    throwExistExceptionIfEmpty(result);
    return result;
  }

  @Override
  public List<Socks> getAllWhereLessThanValues(Integer cottonPart, String color)
      throws ExistException {
    var result = jdbcTemplate.query(
        "SELECT * FROM socks WHERE color=? AND cotton_part<?",
        new Object[]{color, cottonPart},
        socksRowMapper);
    throwExistExceptionIfEmpty(result);
    return result;
  }

  @Override
  public List<Socks> getAllWhereValuesEquals(Integer cottonPart, String color)
      throws ExistException {
    var result = jdbcTemplate.query(
        "SELECT * FROM socks WHERE color=? AND cotton_part=?",
        new Object[]{color, cottonPart},
        socksRowMapper);
    throwExistExceptionIfEmpty(result);
    return result;
  }

  @Override
  public void deleteAll() {
    jdbcTemplate.update("TRUNCATE TABLE socks");
  }

  private Boolean isExistInBD(SocksDto socks) {
    var exists = jdbcTemplate.queryForObject(
        "SELECT exists(SELECT id FROM socks WHERE color=? AND cotton_part=?) AS exists",
        new Object[]{
            socks.getColor(),
            socks.getCottonPart()
        },
        existsRowMapper);
    return Boolean.TRUE.equals(exists);
  }

  private Integer getQuantity(SocksDto socks) {
    var quantity = jdbcTemplate.queryForObject(
        "SELECT quantity FROM socks WHERE color=? AND cotton_part=?",
        new Object[]{
            socks.getColor(),
            socks.getCottonPart()
        },
        quantityRowMapper);
    quantity -= socks.getQuantity();
    return quantity;
  }

  private void throwExistExceptionIfEmpty(List<Socks> list) throws ExistException{
    if (list.isEmpty()) {
      throw new ExistException();
    }
  }
}
