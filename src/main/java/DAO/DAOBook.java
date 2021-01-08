package DAO;

import Model.BooksListMock;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

@RequestScoped
public class DAOBook {
  @Inject
  BooksListMock bookList;

  public List<BooksListMock.Book> getAll() {
    return bookList.getBooksList();
  }

  public BooksListMock.Book getOneByIsbn(int isbn) {
    BooksListMock.Book result = null;
    for(BooksListMock.Book book: getAll()) {
      if (book.getIsbn() == isbn) result = book;
    }
    return result;
  }

  public void addOne(BooksListMock.Book book) {
    bookList.addOne(book);
  }
}
