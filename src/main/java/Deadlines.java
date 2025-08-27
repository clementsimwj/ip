public class Deadlines extends Task{
    private String dateline;

    public Deadlines(String name, String dateline) {
        super(name);
        this.dateline = dateline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateline +")";
    }

    @Override
    public String toFileFormat() {
        return "T" + " | " + super.isMarked() +" | " + super.getName() + " | " + this.dateline;
    }
}
