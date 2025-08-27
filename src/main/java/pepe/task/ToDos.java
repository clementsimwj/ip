package pepe.task;

public class ToDos extends Task {

    public ToDos(String name) {
        super(name);
    }

    @Override
    public String toString() {

        return "[T]" + super.toString();
    }

    @Override
    public boolean isDueNextWeek() {
        return false;
    }

    @Override
    public String toFileFormat() {
        return "T" + " | " + super.isMarked() +" | " + super.getName();
    }
}
