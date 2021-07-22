package LMS;

import java.util.Date;

public class Book {

    private String ISBN;
    private int price;
    private String genre;
    private String title;
    private int edition;
    private int WID;
    private int SID;
    private int publisher_ID;
    private int year;
    private Date Date_supplied;

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public int getWID() {
        return WID;
    }

    public void setWID(int WID) {
        this.WID = WID;
    }

    public int getSID() {
        return SID;
    }

    public void setSID(int SID) {
        this.SID = SID;
    }

    public int getPublisher_ID() {
        return publisher_ID;
    }

    public void setPublisher_ID(int publisher_ID) {
        this.publisher_ID = publisher_ID;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Date getDate_supplied() {
        return Date_supplied;
    }

    public void setDate_supplied(Date date_supplied) {
        Date_supplied = date_supplied;
    }
}

