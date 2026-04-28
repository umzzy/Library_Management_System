package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Member {
    private final int id = IdGenerator.generateId();
    private final String name;
    private final String email;
    private List<Book> borrowedBooks;
    private int maxBorrowedBooks;
    private int totalBorrowedBooks;

    static class IdGenerator{
        private static int nextId = 1;
        public static int generateId(){
            return nextId++;
        }
    }

    public Member(String name, String email) {
        this.name = name;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
        this.totalBorrowedBooks = 0;
    }

    protected void setMaxBorrowedBooks(int number) {
        this.maxBorrowedBooks = number;
    }

    public int getId(){
        return this.id;
    }

    public String getMemberId(){
        return "M1-%04d".formatted(this.id);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public boolean canBorrowMore(){
        if(this.borrowedBooks.size() >= maxBorrowedBooks){
            System.out.println(this.getName() + " can not borrow more books.");
            return false;
        }
        return true;
    }

    public boolean borrowBook(Book book){
        if(canBorrowMore()){
            if(book.isIssued()){
                return false;
            }else {
                this.borrowedBooks.add(book);
                totalBorrowedBooks++;
                return true;
            }
        }
        return false;
    }

    public boolean returnBook(Book book){
        if(borrowedBooks.contains(book)){
            borrowedBooks.remove(book);
            totalBorrowedBooks--;
            System.out.printf("User %1$s has returned %2$s book successfully.%n", this.getName(), book.getTitle());
            return true;
        }
        System.out.printf("User %1$s can not return %2$s book because he did not borrow it.", this.getName(), book.getTitle());
        return false;
    }

    public boolean hasBorrowedBook(Book book){
        return borrowedBooks.contains(book);
    }

    public int getTotalBorrowedBooks() {
        return totalBorrowedBooks;
    }

    public abstract String getMemberType();

    @Override
    public String toString(){
        return "%-10s | %-30s | %-30s | %-20s | %-5d".formatted(getMemberId(), getName(),
                getEmail(), getMemberType(), getTotalBorrowedBooks());
    }
}
