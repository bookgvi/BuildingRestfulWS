package WebResources.Filters;

import javax.ws.rs.container.*;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.logging.Logger;

@Provider
@PreMatching
public class LoggerFilter implements ContainerRequestFilter, ContainerResponseFilter {
  @Override
  public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
    Logger.getLogger("CONTAINER_RESPONSE_FILTER").info(containerRequestContext.getUriInfo().getAbsolutePath().toString());
  }

  public void filter(ContainerRequestContext containerRequestContext) throws IOException {
    Logger.getLogger("CONTAINER_REQUEST_FILTER").info(containerRequestContext.getUriInfo().toString());
  }
}
