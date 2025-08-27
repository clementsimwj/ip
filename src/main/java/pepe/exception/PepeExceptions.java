package pepe.exception;

public class PepeExceptions extends Exception {
    public PepeExceptions(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return ("Hey bro...I am going to tell you nicely!\n" + super.getMessage());
    }
}
