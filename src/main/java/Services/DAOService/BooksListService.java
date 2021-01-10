package Services.DAOService;

import DAO.DAOBook;
import Model.BooksListMock;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class BooksListService {
  @Inject
  DAOBook daoBook;

  public List<BooksListMock.Book> findAll() {
    return daoBook.getAll();
  }

  public BooksListMock.Book findOneByIsbn(String isbn) {
    return daoBook.getOneByIsbn(isbn);
  }

  public void addOne(BooksListMock.Book book) {
    daoBook.addOne(book);
  }
}
