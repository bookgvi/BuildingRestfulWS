package WebResources;

import Model.BooksListMock;
import Services.DAOService.BooksListService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

  @GET
  @Path("/{isbn}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findByIsbn(@PathParam("isbn") int isbn) {
    BooksListMock.Book book = booksListService.findOne(isbn);
    if (book == null) return Response.status(Response.Status.NOT_FOUND).build();
    return Response.ok().entity(book).build();
  }
}
