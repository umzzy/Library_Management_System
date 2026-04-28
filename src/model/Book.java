package model;

public class Book extends LibraryItem{
    private final String author;
    private final Category category;

    public Book(String title, String author, Category category) {
        super(title);
        this.author = author;
        this.category = category;
    }

    @Override
    protected String getPrefix() {
        return "BK";
    }

    @Override
    protected  String getItemType() {
        return "Book";
    }

    public String getAuthor() {
        return this.author;
    }

    public Category getCategory() {
        return this.category;
    }

    @Override
    public void issueItem() {
        super.issueItem();
    }

    @Override
    public String toString() {
        return "%-10s | %-50s | %-20s | %-20s | %-10s | %-10s".formatted(getFormattedId(), getTitle(),
                getAuthor(), getCategory(), isIssued() ? "Issued" : "Available", getItemType());
    }
}
