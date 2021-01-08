package WebResources;

import Model.BooksListMock;
import Services.DAOService.BooksListService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.logging.Logger;

@Path("/book")
@RequestScoped
public class ResourceBook {
  @Inject
  BooksListService booksListService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response findAll() {
    return Response.ok().entity(booksListService.findAll()).build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response addOne(InputStream bookStream) {
    JsonReader bookJsonReader = Json.createReader(bookStream);
    JsonObject bookJson = bookJsonReader.readObject();
    int isbn = bookJson.getInt("isbn");
    String title = bookJson.getString("title");
    if (booksListService.findOneByIsbn(isbn) != null) {
      return Response.status(Response.Status.CONFLICT).build();
    }
    booksListService.addOne(new BooksListMock.Book(isbn, title));
    return Response.status(Response.Status.CREATED).entity(bookStream).build();
  }

  @GET
  @Path("/{isbn}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findById(@PathParam("isbn") int isbn) {
    BooksListMock.Book book = booksListService.findOneByIsbn(isbn);
    if (book == null) return Response.status(Response.Status.NOT_FOUND).build();
    return Response.ok().entity(book).build();
  }

}
