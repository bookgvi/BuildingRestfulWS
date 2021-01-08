package Services.DAOService;

import DAO.DAOBook;
import Model.BooksListMock;

import javax.inject.Inject;
import java.util.List;

public class BooksListService {
  @Inject
  DAOBook daoBook;

  public List<BooksListMock.Book> findAll() {
    return daoBook.getAll();
  }
}
