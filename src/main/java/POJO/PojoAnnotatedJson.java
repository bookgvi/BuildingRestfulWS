package POJO;

import javax.json.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Date;

@JsonbPropertyOrder(value = {"message", "name", "date", "num"})
public class PojoAnnotatedJson {
  @JsonbProperty(value = "Name", nillable = false)
  public String name = "this is name";

  @JsonbProperty(value = "QQQ", nillable = true)
  public String message = "some message";

  @JsonbDateFormat("dd.MM.yyyy")
  public Date date = new Date();

  @JsonbNumberFormat("##,###.00")
  public Integer num = 1234;

  @JsonbTransient
  public BigDecimal invisible = BigDecimal.TEN;
}
