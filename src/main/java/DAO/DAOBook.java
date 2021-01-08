package DAO;

import Model.BooksListMock;

import javax.inject.Inject;
import java.util.List;

public class DAOBook {
  @Inject
  BooksListMock bookList;

  public List<BooksListMock.Book> getAll() {
    return bookList.getBooksList();
  }
}
