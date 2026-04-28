package model;

public class StudentMember extends Member{
    public StudentMember(String name, String email) {
        super(name, email);
        setMaxBorrowedBooks(3);
    }

    @Override
    public String getMemberType() {
        return "Student";
    }
}

