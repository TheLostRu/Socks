package org.store.socks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * The type Socks dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SocksDto {

  private String color;
  private Integer cottonPart;
  private Integer quantity;
}
