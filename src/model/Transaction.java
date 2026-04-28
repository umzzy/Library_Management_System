package model;

import java.time.LocalDate;

public class Transaction {
    private final int transactionID;
    private final Book book;
    private final Action action;
    private final Member member;
    private final String date;

        static class IdGenerator {
            private static int nextId = 1;
            protected static int generateId(){
                return nextId++;
            }
        }

    public Transaction(Book book, Action action, Member member,  String date) {
        this.transactionID = IdGenerator.generateId();
        this.book = book;
        this.action = action;
        this.member = member;
        this.date = date;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public Book getBook() {
        return book;
    }

    public Action getAction() {
        return action;
    }

    public Member getMember() {
        return member;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "%10d | %-30s | %-20s | %-30s | %-20s".formatted(transactionID, book.getTitle(), action, member.getName(), date);
    }
}
