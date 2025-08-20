import java.util.Scanner;

public class Pepe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String border = "____________________________________________________________";
        String input;
        Task[] list = new Task[100];
        int counter = 0;

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
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println(border);
                for (int i = 0; i < counter; i++) {
                    System.out.println(i+1 + ". " + list[i]);
                }
                System.out.println(border);
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                Task task = list[index];
                task.markTask();
                System.out.println(border);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task);
                System.out.println(border);
            }
            else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                Task task = list[index];
                task.unmarkTask();
                System.out.println(border);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task);
                System.out.println(border);
            }
            else {
                System.out.println(border);
                System.out.println("added: " + input);
                System.out.println(border);
                list[counter] = new Task(input);
                counter++;
            }
        }

        scanner.close();
    }
}
