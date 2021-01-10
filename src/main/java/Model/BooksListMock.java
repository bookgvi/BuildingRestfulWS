package Model;

import javax.inject.Singleton;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class BooksListMock {
  private List<Book> booksList = new ArrayList<>();

  private BooksListMock() {
    Book book = new Book("0000000001", "QQQ1");
    booksList.add(book);
    book = new Book("0000000002", "QQQ2");
    booksList.add(book);
  }

  public List<Book> getBooksList() {
    return booksList;
  }

  public Book getOneById(int id) {
    return booksList.get(id);
  }

  public void addOne(Book book) {
    booksList.add(book);
  }

  public static class Book {
    private String isbn;
    private String title;

    public Book() {}

    public Book(String isbn, String title) {
      this.isbn = isbn.intern();
      this.title = title.intern();
    }

    public void setIsbn(String isbn) {
      this.isbn = isbn.intern();
    }

    @NotNull
    @Pattern(regexp = "[0-9]{10}")
    public String getIsbn() {
      return isbn;
    }

    public void setTitle(String title) {
      this.title = title.intern();
    }

    @NotNull
    @NotBlank
    public String getTitle() {
      return title;
    }

  }
}
