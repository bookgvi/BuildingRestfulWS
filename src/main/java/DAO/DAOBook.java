package DAO;

import Model.BooksListMock;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class DAOBook {
  @Inject
  BooksListMock bookList;

  public List<BooksListMock.Book> getAll() {
    return bookList.getBooksList();
  }

  public BooksListMock.Book getOneByIsbn(int isbn) {
    List<BooksListMock.Book> bookListColl = getAll().stream()
      .filter((BooksListMock.Book book) -> book.getIsbn() == isbn)
      .collect(Collectors.toList());
    return bookListColl.size() == 1 ? bookListColl.get(0) : null;
  }

  public void addOne(BooksListMock.Book book) {
    bookList.addOne(book);
  }
}
