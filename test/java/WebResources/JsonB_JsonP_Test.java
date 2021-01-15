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

import javax.json.*;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.BinaryDataStrategy;
import javax.json.bind.config.PropertyNamingStrategy;
import javax.json.bind.config.PropertyOrderStrategy;
import java.io.StringReader;
import java.util.Locale;

@RunWith(Arquillian.class)
public class JsonB_JsonP_Test {
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

  @Test
  public void jsonP_features() {
    JsonObject source = Json.createObjectBuilder()
      .add("aString", "QQQ")
      .add("aNumber", 1)
      .build();
    JsonObject target = Json.createObjectBuilder()
      .add("aString", "QQQ")
      .add("aNumber", 1)
      .add("anArray", "[1, 2, 3]")
      .build();

    JsonReader jsonReader = Json.createReader(new StringReader("{\"aString\": \"Hello Json-P\",\"arrayOfInt\":[1,2,3]}"));
    JsonObject jsonObject1 = jsonReader.readObject();
    JsonArray jsonArray1 = jsonObject1.getJsonArray("arrayOfInt");

    JsonPointer jsonPointer = Json.createPointer("/arrayOfInt/1");
    JsonValue num = jsonPointer.getValue(jsonObject1);
    Assert.assertEquals(num, jsonArray1.get(1));

    JsonPatch patch = Json.createPatchBuilder()
      .remove("/arrayOfInt/1").build();
    jsonObject1 = patch.apply(jsonObject1);
    Assert.assertEquals(2, jsonObject1.getJsonArray("arrayOfInt").size());

    JsonPatch diff = Json.createDiff(source, target);
    Assert.assertNotEquals(source, target);
    source = diff.apply(source);
    Assert.assertEquals(source, target);
  }
}
