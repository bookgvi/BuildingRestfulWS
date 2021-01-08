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

  public BooksListMock.Book findOne(int isbn) {
    return daoBook.getOne(isbn);
  }
}
