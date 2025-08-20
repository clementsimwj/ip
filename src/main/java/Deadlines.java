public class Deadlines extends Task{
    private String dateline;

    public Deadlines(String dateline, String name) {
        super(name);
        this.dateline = dateline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateline +")";
    }
}
