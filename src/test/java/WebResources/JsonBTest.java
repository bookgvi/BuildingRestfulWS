package WebResources;

import POJO.PlainPojo;
import POJO.PojoAnnotatedJson;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.BinaryDataStrategy;
import javax.json.bind.config.PropertyNamingStrategy;
import javax.json.bind.config.PropertyOrderStrategy;
import java.util.Locale;

@RunWith(Arquillian.class)
public class JsonBTest {
  @Deployment
  public static JavaArchive createDeployment() {
    return ShrinkWrap.create(JavaArchive.class)
      .addClass(PojoAnnotatedJson.class)
      .addClass(PlainPojo.class)
      .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
  }

  private Jsonb jsonb;

  @Before
  public void prepareJsonBConfig() {
    JsonbConfig jsonbConfig = new JsonbConfig()
      .withPropertyOrderStrategy(PropertyOrderStrategy.LEXICOGRAPHICAL)
      .withNullValues(true)
      .withPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE_WITH_DASHES)
      .withBinaryDataStrategy(BinaryDataStrategy.BASE_64)
      .withDateFormat("dd/MM/yyyy", Locale.US);
    jsonb = JsonbBuilder.create(jsonbConfig);
  }

  @Test
  public void compareJsonB_with_AnnotatedPOJO() {
    PojoAnnotatedJson pojoAnnotatedJson = new PojoAnnotatedJson();
    PlainPojo plainPojo = new PlainPojo();

    Jsonb jsonb = JsonbBuilder.create();
    String annotatedPojoJson = jsonb.toJson(pojoAnnotatedJson);
    String plainPojoJson = this.jsonb.toJson(plainPojo);

    Assert.assertEquals(plainPojoJson, plainPojoJson, annotatedPojoJson);
    Assert.assertEquals(annotatedPojoJson, annotatedPojoJson, plainPojoJson);
  }
}
