package model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
public class SelectedGenre {
   
    private SimpleStringProperty bookIsbn;
    private SimpleStringProperty bookname;
    private SimpleStringProperty author;
    private SimpleStringProperty publisher;
    private SimpleStringProperty publishDate;
    private SimpleStringProperty genre;
    private SimpleIntegerProperty amount;
    private SimpleIntegerProperty stock;
    private SimpleStringProperty bookId;
    
    public SelectedGenre(String ISBN,String bookname,String author,String publisher,String publishDate,String genre,int amount,int stock,String bookID) {
    	this.bookIsbn = new SimpleStringProperty(ISBN);
    	this.bookname = new SimpleStringProperty(bookname);
    	this.author = new SimpleStringProperty(author);
    	this.publisher = new SimpleStringProperty(publisher);
    	this.publishDate = new SimpleStringProperty(publishDate);
    	this.genre = new SimpleStringProperty(genre);
    	this.amount = new SimpleIntegerProperty(amount);
    	this.stock = new SimpleIntegerProperty(stock);
    	this.bookId = new SimpleStringProperty(bookID);
    }
    
    public String getBookIsbn() {
    	return this.bookIsbn.get();
    }
    public String getBookname() {
    	return this.bookname.get();
    }
    public String getAuthor() {
    	return this.author.get();
    }
    public String getPublisher() {
    	return this.publisher.get();
    }
    public String getPublishDate() {
    	return this.publishDate.get();
    }
    public String getGenre() {
    	return this.genre.get();
    }
    public int getAmount() {
    	return this.amount.get();
    }
    public int getStock() {
    	return this.stock.get();
    }
    public String getBookId() {
    	return this.bookId.get();
    }
}
