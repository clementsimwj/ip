import java.util.Scanner;

public class Pepe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String border = "____________________________________________________________";
        String input;

        System.out.println(border);
        System.out.println("Hello, I am Pepe!\n" + "How may I help you today?" );
        System.out.println(border);

        while (true) {
            input = scanner.nextLine();

            if(input.equalsIgnoreCase("bye")) {
                System.out.println(border);
                System.out.println("Aww...so sad to see you leave! :(");
                System.out.println(border);
                break;
            } else {
                System.out.println(border);
                System.out.println(" " + input);
                System.out.println(border);
            }
        }

        scanner.close();
    }
}
