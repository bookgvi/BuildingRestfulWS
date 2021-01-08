package Services.ErrorsHandler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

//@Provider
public class NotFound implements ExceptionMapper<IndexOutOfBoundsException> {
  public Response toResponse(IndexOutOfBoundsException e) {
    return Response.status(Response.Status.NOT_FOUND).build();
  }
}
