abstract class Task {
    private final String name;
    private boolean marked;

    public Task(String name) {
        this.name = name;
        this.marked = false;
    }

    public void markTask(){

        this.marked = true;
    }

    public void unmarkTask() {

        this.marked = false;
    }

    public String getName() {

        return this.name;
    }

    public int isMarked() {
        return this.marked ? 1 : 0;
    }

    @Override
    public String toString() {
        String markSymbol = this.marked ? "X" : " ";
        return "[" + markSymbol + "] " + this.name;
    }

    abstract public String toFileFormat();
}
