package AsyncWebResources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Path("threadname")
public class GetThreadName {

  @GET
  public void getResponseWithThreadName(@Suspended final AsyncResponse asyncResponse) {
    asyncResponse.setTimeout(5, TimeUnit.SECONDS);
    String requestThreadName = Thread.currentThread().getName();
    new Thread(() -> {
      String responseThreadName = Thread.currentThread().getName();
      try {
        Thread.sleep(3000);
      } catch (InterruptedException iex) {
        iex.printStackTrace();
      }
      Map<String, String> responseBody = new HashMap<>();
      responseBody.put("requestThread", requestThreadName);
      responseBody.put("responseThread", responseThreadName);

      asyncResponse.resume(Response.ok().entity(responseBody).build());

    }).start();
  }
}
