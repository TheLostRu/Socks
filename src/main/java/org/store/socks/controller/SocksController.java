package org.store.socks.controller;

import io.swagger.v3.oas.annotations.Hidden;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.store.socks.dto.SocksDto;
import org.store.socks.entity.Socks;
import org.store.socks.exceptions.ExistException;
import org.store.socks.exceptions.IncorrectParameterException;
import org.store.socks.exceptions.QuantityException;
import org.store.socks.service.SocksService;

/**
 * The type Socks controller.
 */
@Slf4j
@RestController
@RequestMapping("/api/socks")
public class SocksController {

  /**
   * The Service.
   */
  @Autowired
  private SocksService service;

  /**
   * Input.
   *
   * @param socksDto the socks dto
   * @throws QuantityException the quantity exception
   */
  @PostMapping("/income")
  public void input(@RequestBody SocksDto socksDto) throws QuantityException {
    service.input(socksDto);
  }

  /**
   * Output.
   *
   * @param socksDto the socks dto
   * @throws QuantityException the quantity exception
   * @throws ExistException    the exist exception
   */
  @PostMapping("/outcome")
  public void output(@RequestBody SocksDto socksDto) throws
      QuantityException, ExistException {
    service.output(socksDto);
  }

  /**
   * Get list.
   *
   * @param cottonPart the cotton part
   * @param color      the color
   * @param operation  the operation
   * @return the list
   * @throws ExistException              the exist exception
   * @throws QuantityException           the quantity exception
   * @throws IncorrectParameterException the incorrect parameter exception
   */
  @GetMapping("")
  public List<Socks> get(@RequestParam Integer cottonPart,
      @RequestParam String color, @RequestParam String operation)
      throws ExistException, IncorrectParameterException, QuantityException {
    return service.get(cottonPart, color, operation);
  }

  /**
   * Gets swagger
   * url:http://localhost:8080/swagger-ui/index.html?configUrl=/api/swagger-config#/socks/input
   * link: /api/socks/swagger
   *
   * @return the swagger
   */
  @Hidden
  @GetMapping("/swagger")
  @ResponseBody
  public FileSystemResource getSwagger() {
    return new FileSystemResource("src/main/resources/swagger/socksSwagger.yaml");
  }
}
