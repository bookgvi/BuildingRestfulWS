package DAO;

import Model.BooksListMock;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class DAOBook {
  @Inject
  BooksListMock bookList;

  public List<BooksListMock.Book> getAll() {
    return bookList.getBooksList();
  }

  public BooksListMock.Book getOne(int isbn) {
    return bookList.getOne(isbn);
  }
}
