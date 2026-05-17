package model;

public abstract class LibraryItem {
    private final int id = IdGenerator.generateId();
    private final String title;
    private boolean issued;

    static class IdGenerator{
        private static int nextId = 1;

        public static int generateId(){
            return nextId++;
        }
    }

    public LibraryItem(String title) {
        this.title = title;
        this.issued = false;
    }

    public int getId() {
        return this.id;
    }

    protected abstract String getPrefix();

    protected String getFormattedId(){
        return "%s-%03d".formatted(getPrefix(), this.id);
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isIssued() {
        return this.issued;
    }

    public void issueItem(){
        if (!isIssued()) {
            this.issued = true;
        } else {
            System.out.printf("%s is already issued.",  this.getTitle());
        }
    }

    public void returnItem(){
        if(this.issued){
            this.issued = false;
        }else{
            System.out.printf("%s is not issued.",  this.getTitle());
        }
    }

    protected abstract String getItemType();
}
