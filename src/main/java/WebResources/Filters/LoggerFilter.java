package WebResources.Filters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.container.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

@Provider
@PreMatching
public class LoggerFilter implements ContainerRequestFilter, ContainerResponseFilter {

  @Context
  HttpServletRequest webRequest;

  @Override
  public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
    Logger.getLogger("CONTAINER_RESPONSE_FILTER").info(containerRequestContext.getUriInfo().getAbsolutePath().toString());
    HttpSession session = webRequest.getSession(false);
    if (session == null) {
      Logger.getLogger("CONTAINER_RESPONSE_FILTER").info(" --- Creating new SESSION --- ");
      session = webRequest.getSession(true);
      session.setAttribute("uuid", UUID.randomUUID() + "===");
      session.setMaxInactiveInterval(5 * 60); // in seconds
    }
    Logger.getLogger("CONTAINER_RESPONSE_FILTER").info(" --- Current session: " + session.getId());

    containerResponseContext.getHeaders().add("XXX_Test", "IWFM");
    containerResponseContext.getHeaders().add("SessionUID", session.getAttribute("uuid"));
  }

  public void filter(ContainerRequestContext containerRequestContext) throws IOException {
  }
}
