package app;

import model.*;
import service.Library;
import util.InputHelper;

import java.util.List;

public class LibraryApp {
    private Library library = new Library();

    public void start(){
        System.out.println("Welcome to the My Library!");
        library.addDefaultBooks();
        while (true){
            showMenu();
            int choice = InputHelper.readInt("Enter your choice: ");

            switch (choice){
                case 1 -> handleAddBook();
                case 2 -> handleViewAllBooks();
                case 3 -> handleSearchBookById();
                case 4 -> handleSearchBookByTitle();
                case 5 -> handleDeleteBook();
                case 6 -> handleRegisterStudent();
                case 7 -> handleRegisterFaculty();
                case 8 -> handleViewAllMembers();
                case 9 -> handleFilterMembersByType();
                case 10 -> handleSearchMemberById();
                case 11 -> handleSearchMemberByName();
                case 12 -> handleDeleteMemberById();
                case 13 -> handleIssueBook();
                case 14 -> handleReturnBook();
                case 15 -> handleViewTransactions();
                case 16 -> handleViewTransactionByMemberId();
                case 17 -> handleViewTransactionByBookId();
                case 18 -> handleViewAvailableBooks();
                case 19 -> handleViewIssuedBooks();
                case 20 -> handleExit();
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    };

    public void showMenu(){
        System.out.println("-------------------------------\nMenu:\n-------------------------------");

        String menu = """
                1.  Add Book
                2.  View All Books
                3.  Search Book by Id
                4.  Search Book by Title
                5.  Delete Book
                6.  Register Student Member
                7.  Register Staff Member
                8.  View All Members
                9.  Filter Members by Type
                10.  Search Member by Id
                11. Search Member by Name
                12. Delete Member by Id
                13. Issue Book
                14. Return Book
                15. View Transactions
                16. View Transaction By Member Id
                17. View Transaction By Book Id
                18. View Available Books
                19. View Issued Books
                20. Exit
                """;
        System.out.println(menu);
    }

    private void handleAddBook(){

        System.out.println("---------------------------------------\nAdd book: \n-------------------------------");

        String title = InputHelper.readString("Enter book title: ");
        String author = InputHelper.readString("Enter book author: ");
        Category category = InputHelper.readCategory("Choose category: ");

        Book book = new Book(title, author, category);

        boolean added = library.addBook(book);
        if(added){
            System.out.println("Book added successfully.");
        }else{
            System.out.println("Failed to add book.");
        }
    }

    private String center(String text, int length){
        if(text == null) text = "";
        if(text.length() >= length) return text;

        int totalPadding = length - text.length();
        int leftPadding = totalPadding / 2;
        int rightPadding = totalPadding - leftPadding;

        return String.format("%s%s%s", leftPadding > 0 ? " ".repeat(leftPadding) : "", text, rightPadding > 0 ? " ".repeat(rightPadding) : "");
    }

    private void handleViewAllBooks(){
        System.out.println("---------------------------------------\nAll Book in the Library: \n-------------------------------");
        System.out.printf("%s %-50s   %-20s   %-20s   %-10s\n", center("ID", 10), "Title", "Author", "Category", "Issued");
        library.getAllBooks().forEach(System.out::println);
    }

    private void handleSearchBookById(){
        System.out.println("---------------------------------------\nSearching Book in the Library by ID: \n-------------------------------");
        int id = InputHelper.readInt("Enter book ID: ");
        Book book = library.searchBookById(id);
        if(book != null){
            System.out.printf("%s %-50s   %-20s   %-20s   %-10s\n", center("ID", 10), "Title", "Author", "Category", "Issued");
            System.out.println(book);
        }else{
            System.out.println("Book not found.");
        }
    }

    private void handleSearchBookByTitle(){
        System.out.println("---------------------------------------\nSearching Book in the Library by Title: \n-------------------------------");
        String title = InputHelper.readString("Enter book title: ");
        List<Book> books = library.searchBookByTitle(title);
        if(books.isEmpty()){
            System.out.println("No books found.");
        }else{
            System.out.println("Books found:");
            System.out.printf("%s %-50s   %-20s   %-20s   %-10s\n", center("ID", 10), "Title", "Author", "Category", "Issued");
            books.forEach(System.out::println);
        }
    }

    private void handleDeleteBook(){
        int id = InputHelper.readInt("Enter book ID: ");
        boolean isDeleted = library.deleteBookById(id);
        if(isDeleted){
            System.out.println("Book deleted successfully.");
        }else{
            System.out.println("Failed to delete book.");
        }
    }

    private void handleRegisterStudent(){
        System.out.println("---------------------------------------\nRegistering Student: \n-------------------------------");
        String name = InputHelper.readString("Enter student name: ");
        String email = InputHelper.readEmail("Enter student email: ");

        Member member = new StudentMember(name, email);
        boolean registered = library.registerMember(member);
        if(registered){
            System.out.println("Student registered successfully.");
        }else{
            System.out.println("Failed to register member.");
        }

    }

    private void handleRegisterFaculty(){
        System.out.println("---------------------------------------\nRegistering Staff: \n-------------------------------");
        String name = InputHelper.readString("Enter staff name: ");
        String email = InputHelper.readEmail("Enter staff email: ");

        Member member = new FacultyMember(name, email);
        boolean registered = library.registerMember(member);
        if(registered){
            System.out.println("Staff registered successfully.");
        }else{
            System.out.println("Failed to register member.");
        }
    }

    private void handleViewAllMembers(){
        System.out.println("---------------------------------------\nMembers: \n-------------------------------");
        System.out.printf("%s %-30s   %-30s   %-20s   %-10s\n", center("ID", 10), "Name", "Email", "Type", "Total Borrowed");
        library.getAllMembers().forEach(System.out::println);
    }

    private void handleFilterMembersByType(){
        System.out.println("---------------------------------------\nSelect Members Type: \n-------------------------------");
        System.out.println("1. Student");
        System.out.println("2. Staff");

        int choice = InputHelper.readInt("Enter your choice: ");
        switch(choice){
            case 1 -> {
                System.out.println("---------------------------------------\nStudent Members: \n-------------------------------");
                System.out.printf("%s %-30s   %-30s   %-20s   %-10s\n", center("ID", 10), "Name", "Email", "Type", "Total Borrowed");
                List<?> studentMembers = library.getAllMembersByType("Student");
                if(studentMembers.isEmpty()){
                    System.out.println("No students found.");
                }else{
                    studentMembers.forEach(System.out::println);
                }
            }
            case 2 ->{
                System.out.println("---------------------------------------\nStaff Members: \n-------------------------------");
                System.out.printf("%s %-30s   %-30s   %-20s   %-10s\n", center("ID", 10), "Name", "Email", "Type", "Total Borrowed");
                List<?> staffMembers = library.getAllMembersByType("Staff");
                if(staffMembers.isEmpty()){
                    System.out.println("No students found.");
                }else{
                    staffMembers.forEach(System.out::println);
                }
            }
        }
    }

    private void handleSearchMemberById(){
        int id = InputHelper.readInt("Enter member ID: ");
        Member member = library.searchMemberById(id);

        System.out.println("---------------------------------------\nMembers Filtered By Id: \n-------------------------------");
        System.out.printf("%s %-30s   %-30s   %-20s   %-10s\n", center("ID", 10), "Name", "Email", "Type", "Total Borrowed");

        System.out.println(member);
    }

    private void handleSearchMemberByName(){
        String name = InputHelper.readString("Enter member name: ");
        List<Member> members = library.searchMemberByName(name);

        System.out.println("---------------------------------------\nMembers Filtered By Name: \n-------------------------------");
        System.out.printf("%s %-30s   %-30s   %-20s   %-10s\n", center("ID", 10), "Name", "Email", "Type", "Total Borrowed");
        members.forEach(System.out::println);
    }

    private void handleDeleteMemberById(){
        int id = InputHelper.readInt("Enter member ID: ");
        boolean isDeleted = library.deleteMemberById(id);
        if(isDeleted){
            System.out.println("Member deleted successfully.");
        }else{
            System.out.println("Failed to delete member.");
        }
    }

    private void handleIssueBook(){
        int bookId = InputHelper.readInt("Enter book ID: ");
        int memberId = InputHelper.readInt("Enter member ID: ");
        String date = InputHelper.readDate("Enter issue date: ");

        boolean issued = library.issueBook(bookId, memberId, date);
        if(issued){
            System.out.println("Book issued successfully.");
        }else{
            System.out.println("Failed to issue book.");
        }
    }

    private void handleReturnBook(){
        int bookId = InputHelper.readInt("Enter book ID: ");
        int memberId = InputHelper.readInt("Enter member ID: ");
        String date = InputHelper.readDate("Enter return date: ");

        boolean returned = library.returnBook(bookId, memberId, date);
        if(returned){
            System.out.println("Book returned successfully.");
        }else{
            System.out.println("Failed to return book.");
        }
    }

    private void handleViewTransactions() {
        List<Transaction> transactions = library.getAllTransactions();
        if (transactions.isEmpty()) {
            System.out.println("No transactions available.");
            return;
        } else {
            System.out.println("---------------------------------------\nTransactions: \n-------------------------------");
            System.out.printf("%-10s %-30s   %-20s   %-30s   %-20s\n", "Book ID", "Book Title", "Action", "Member Name", "Date");
            transactions.forEach(System.out::println);
        }
    }

    private void handleViewTransactionByMemberId() {
        int memberId = InputHelper.readInt("Enter member ID: ");
        List<Transaction> transactions = library.getTransactionsByMemberId(memberId);
        if (transactions.isEmpty()) {
            System.out.println("No transactions found for the given member ID.");
            return;
        } else {
            System.out.println("-------------------------------\nTransactions By Member ID: \n-------------------------------");
            System.out.printf("%-10s %-30s   %-20s   %-30s   %-20s\n", "Book ID", "Book Title", "Action", "Member Name", "Date");
            transactions.forEach(System.out::println);
        }
    }

    private void handleViewTransactionByBookId() {
        int bookId = InputHelper.readInt("Enter book ID: ");
        List<Transaction> transactions = library.getTransactionsByBookId(bookId);
        if (transactions.isEmpty()) {
            System.out.println("No transactions found for the given book ID.");
            return;
        } else {
            System.out.println("-------------------------------\nTransactions By Book ID: \n-------------------------------");
            System.out.printf("%-10s %-30s   %-20s   %-30s   %-20s\n", "Book ID", "Book Title", "Action", "Member Name", "Date");
            transactions.forEach(System.out::println);
        }
    }

    private void handleViewAvailableBooks() {
        List<Book> books = library.getAvailableBooks();
        if (books.isEmpty()) {
            System.out.println("No available books in the library.");
            return;
        } else {
            System.out.println("-------------------------------\nAvailable Books: \n-------------------------------");
            System.out.printf("%s %-50s   %-20s   %-20s   %-10s\n", center("ID", 10), "Title", "Author", "Category", "Issued");
            books.forEach(System.out::println);
        }
    }

    private void handleViewIssuedBooks() {
        List<Book> books = library.getIssuedBooks();
        if (books.isEmpty()) {
            System.out.println("No issued books in the library.");
            return;
        } else {
            System.out.println("-------------------------------\nIssued Books: \n-------------------------------");
            System.out.printf("%s %-50s   %-20s   %-20s   %-10s\n", center("ID", 10), "Title", "Author", "Category", "Issued");
            books.forEach(System.out::println);
        }
    }

    private void handleExit(){
        System.out.println("Goodbye!");
        System.exit(0);
    }
}