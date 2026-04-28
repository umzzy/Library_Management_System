package model;

public class FacultyMember extends Member{
    public FacultyMember(String name, String email) {
        super(name, email);
        setMaxBorrowedBooks(5);
    }
    @Override
    public String getMemberType() {
        return "Staff";
    }
}
