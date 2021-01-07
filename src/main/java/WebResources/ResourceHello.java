package WebResources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Map;

@Path("/hello")
public class ResourceHello {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response hello() {
    String helloMsg = "Hello, world of JavaEE";
    Map<String, String> body = Collections.singletonMap("message", helloMsg);
    return Response.ok().entity(body).build();
  }
}
