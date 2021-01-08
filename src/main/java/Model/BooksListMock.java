package Model;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class BooksListMock {
  private List<Book> booksList = new ArrayList<Book>();

  public BooksListMock() {
    Book book = new Book(1, "QQQ1");
    booksList.add(book);
    book = new Book(2, "QQQ2");
    booksList.add(book);
  }

  public List<Book> getBooksList() {
    return booksList;
  }

  public static class Book {
    private int isbn;
    private String title;

    Book(int isbn, String title) {
      this.isbn = isbn;
      this.title = title;
    }

    public void setIsbn(int isbn) {
      this.isbn = isbn;
    }

    public int getIsbn() {
      return isbn;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getTitle() {
      return title;
    }
  }
}
