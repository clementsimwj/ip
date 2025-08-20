public class Task {
    private String name;
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

    @Override
    public String toString() {
        String markSymbol = this.marked ? "X" : " ";
        return "[" + markSymbol + "] " + this.name;
    }
}
