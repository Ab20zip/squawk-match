import java.util.List;
import java.util.Scanner;

public class SquawkMatchApp {
    private static final MessageService messageService = new MessageService();
    private static final MatchService matchService = new MatchService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Welcome to SquawkMatch!");
            System.out.println("1. Register a new parrot");
            System.out.println("2. Find matches for a parrot");
            System.out.println("3. Send a message");
            System.out.println("4. View messages");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerParrot();
                    break;
                case 2:
                    findMatches();
                    break;
                case 3:
                    sendMessage();
                    break;
                case 4:
                    viewMessages();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void registerParrot() {
        System.out.print("Enter parrot name: ");
        String name = scanner.nextLine();
        System.out.print("Enter species: ");
        String species = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        Parrot parrot = new Parrot(matchService.getParrots().size() + 1, name, species, age, description);
        matchService.registerParrot(parrot);
        System.out.println("Parrot registered: " + parrot);
    }

    private static void findMatches() {
        System.out.print("Enter parrot ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Parrot parrot = matchService.getParrots().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if (parrot == null) {
            System.out.println("Parrot not found.");
            return;
        }

        List<Parrot> matches = matchService.findMatches(parrot);
        if (matches.isEmpty()) {
            System.out.println("No matches found.");
            return;
        }

        System.out.println("Matches found:");
        for (Parrot match : matches) {
            System.out.println(match);
            System.out.print("Swipe right (y) or left (n) on this match: ");
            String swipe = scanner.nextLine();
            if (swipe.equalsIgnoreCase("y")) {
                matchService.swipeRight(parrot, match);
                System.out.println("You swiped right on " + match.getName());
            } else {
                matchService.swipeLeft(parrot, match);
                System.out.println("You swiped left on " + match.getName());
            }
        }
    }

    private static void sendMessage() {
        System.out.print("Enter sender parrot ID: ");
        int senderId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter receiver parrot ID: ");
        int receiverId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter message content: ");
        String content = scanner.nextLine();

        Parrot sender = matchService.getParrots().stream()
                .filter(p -> p.getId() == senderId)
                .findFirst()
                .orElse(null);

        Parrot receiver = matchService.getParrots().stream()
                .filter(p -> p.getId() == receiverId)
                .findFirst()
                .orElse(null);

        if (sender == null || receiver == null) {
            System.out.println("Sender or receiver parrot not found.");
            return;
        }

        Message message = new Message(sender, receiver, content);
        messageService.sendMessage(message);
        System.out.println("Message sent: " + message);
    }

    private static void viewMessages() {
        System.out.print("Enter parrot ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Parrot parrot = matchService.getParrots().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if (parrot == null) {
            System.out.println("Parrot not found.");
            return;
        }

        messageService.getMessages().stream()
                .filter(m -> m.getReceiver().getId() == id)
                .forEach(System.out::println);
    }
}
