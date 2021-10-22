package org.store.socks.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * The type Socks.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Socks {


  private Integer id;
  private String color;
  private Integer cottonPart;
  private Integer quantity;
}
