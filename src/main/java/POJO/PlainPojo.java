package POJO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PlainPojo {
  public String name;

  public String message;

  public LocalDate date;

  public Integer num;

  public BigDecimal invisible = BigDecimal.TEN;
}
