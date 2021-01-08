package WebResources;

import Services.DAOService.BooksListService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;

@Path("book")
@RequestScoped
public class ResourceBook {
  @Inject
  BooksListService booksListService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response findAll() {
    return Response.ok().entity(booksListService.findAll()).build();
  }
}
