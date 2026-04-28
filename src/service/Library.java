package service;

import model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private Map<Integer, Book> books = new HashMap<>();
    private Map<Integer, Member> members = new HashMap<>();
    private List<Transaction>  transactions = new ArrayList<>();

    public void addDefaultBooks(){
        addBook(new Book("Clean Code", "Robert C. Martin", Category.PROGRAMMING));
        addBook(new Book("Java Basics", "James Gosling", Category.PROGRAMMING));
        addBook(new Book("Harry Potter", "J. K. Rowling", Category.SCIENCE_FICTION));
        addBook(new Book("Atomic Habits", "James Clear", Category.SELF_HELP));
        addBook(new Book("The Alchemist", "Paulo Coelho", Category.SCIENCE_FICTION));
    }

    public boolean addBook(Book book) {
        if (books.containsKey(book.getId())) {
            return false; // Book already exists
        }
        books.put(book.getId(), book);
        return true;
    }

    public List<Book> getAllBooks(){
        return new ArrayList<>(books.values());
    }

    public Book searchBookById(int id) {
        return books.get(id);
    }

    public List<Book> searchBookByTitle(String title) {
        List<Book> books = new ArrayList<>();

        for(Book book : this.books.values()){
            if(book.getTitle().toLowerCase().contains(title.toLowerCase())){
                books.add(book);
            }
        }
        return books;
    }

    public boolean deleteBookById(int id) {
        if(!books.containsKey(id)){;
            return false; // Book not found
        }
        books.remove(id);
        return true;
    }

    public boolean registerMember(Member member) {
        if(members.containsKey(member.getId())){
            return false; // Member already exists
        }
        members.put(member.getId(), member);
        return true;
    }

    public List<Member> getAllMembers(){
        return new ArrayList<>(members.values());
    }

    public Member searchMemberById(int id) {
        return members.get(id);
    }

    public List<Member> searchMemberByName(String name) {
        List<Member> members = new ArrayList<>();
        for(Member member : this.members.values()){
            if(member.getName().toLowerCase().contains(name.toLowerCase())){
                members.add(member);
            }
        }
        return members;
    }

    public boolean deleteMemberById(int id){
        if(!members.containsKey(id)){
            return false;
        }
        members.remove(id);
        return true;
    }

    public boolean issueBook(String bookId, int memberId, String date){
        Book book = books.get(bookId);
        Member member = members.get(memberId);

        if(book == null || member == null){
            return false;
        }
        if(book.isIssued()){
            return false;
        }
        if(!member.canBorrowMore()){
            return false;
        }
        boolean borrowed = member.borrowBook(book);
        if(!borrowed){
            System.out.println(member.getName() + " can not borrow more books.");
            return false;
        }else{
            book.issueItem();
            System.out.println(book.getTitle() + " borrowed successfully.");
        }
        transactions.add(new Transaction(book, Action.ISSUE, member, date));
        return true;
    }

    public boolean returnBook(String bookId, int memberId, String date){
        Book book = books.get(bookId);
        Member member = members.get(memberId);

        if(book == null || member == null){
            return false;
        }
        if(!member.hasBorrowedBook(book)){
            return false;
        }
        boolean returned = member.returnBook(book);
        if(!returned){
            return false;
        }

        book.returnItem();
        transactions.add(new Transaction(book, Action.RETURN, member, date));
        return true;
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public List<Transaction> getTransactionsByMemberId(int memberId) {
            List<Transaction> memberTransactions = new ArrayList<>();
            for(Transaction transaction : transactions){
                if(transaction.getMember().getId() == memberId){
                    memberTransactions.add(transaction);
                }
            }
            return memberTransactions;
    }
    public List<Transaction> getTransactionsByBookId(int bookId) {
            List<Transaction> bookTransactions = new ArrayList<>();
            for(Transaction transaction : transactions){
                if(transaction.getBook().getId() == bookId){
                    bookTransactions.add(transaction);
                }
            }
            return bookTransactions;
    }

    public List<Book> getAvailableBooks(){
        List<Book> availableBooks = new ArrayList<>();
        for(Book book : books.values()){
            if(!book.isIssued()){
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    public List<Book> getIssuedBooks(){
        List<Book> issuedBooks = new ArrayList<>();
        for(Book book : books.values()){
            if(book.isIssued()){
                issuedBooks.add(book);
            }
        }
        return issuedBooks;
    }
}
