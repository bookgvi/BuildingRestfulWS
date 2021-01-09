package WebResources;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.runner.RunWith;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class ResourceHelloTest {

  private WebTarget target;

  @Before
  public void before() throws Exception {
    target = ClientBuilder.newClient().target("http://localhost:8080").path("api");
  }

  @Deployment
  public static WebArchive createDeployment() {
    return ShrinkWrap.create(WebArchive.class)
      .addClass(ResourceHello.class)
      .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
  }

  @org.junit.Test
  @RunAsClient
  public void hello() {
    Response response = target.path("hello").request(MediaType.APPLICATION_JSON_TYPE).get();
    assertEquals("HTTP 200: ", Response.Status.OK.getStatusCode(), response.getStatus());

    String content = response.readEntity(String.class);
    assertEquals("Content: ", "{\"message\":\"Hello, world of JavaEE\"}", content);
  }
}
