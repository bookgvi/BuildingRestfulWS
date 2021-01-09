package AsyncWebResources;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@RequestScoped
@Path("threadname")
public class GetThreadName {

  @Inject
  AppDefenitions appDefenitions;

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

  @GET
  @Path("queue")
  public void lock(@Suspended final AsyncResponse asyncResponse) throws InterruptedException {
    appDefenitions.responses.put(asyncResponse);
  }

  @DELETE
  @Path("queue")
  public Response unlock() {
    String currentThread = Thread.currentThread().getName();
    AsyncResponse asyncResponse = appDefenitions.responses.poll();
    if(asyncResponse != null) {
      Logger.getLogger("QQQ(unlock)").info(currentThread);
      Map<String, String> responseBody = new HashMap<>();
      responseBody.put("unlockThread", currentThread);

      asyncResponse.resume(Response.ok().entity(responseBody).build());
    }
    return Response.status(218).build();
  }
}
