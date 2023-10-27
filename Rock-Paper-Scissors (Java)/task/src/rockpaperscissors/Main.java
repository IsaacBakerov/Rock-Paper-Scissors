package rockpaperscissors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        File file = new File("D:\\Downloads\\Rock-Paper-Scissors (Java)\\Rock-Paper-Scissors (Java)\\task\\src\\rockpaperscissors\\rating.txt");
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        List<String> players = new ArrayList<>();
        try (Scanner rating = new Scanner(file)) {
            while (rating.hasNext()) {
                players.add(rating.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.println("Hello, " + name);

        int score = 0;

        for (String player : players) {
            if (player.contains(name)) {
                score = Integer.parseInt(player.split(" ")[1]);
                break;
            }
        }

        List<String> options = new ArrayList<>();
        String inputOptions = sc.nextLine();
        if (inputOptions.isBlank()) {
            options = Arrays.asList("rock", "scissors", "paper");
        } else {
            options = Arrays.stream(inputOptions.split(",")).toList();
        }
        System.out.println(options.toString());
        System.out.println("Okay, let's start");

        while (true) {
            String playerInput = sc.nextLine();

            if (playerInput.equals("!exit")) {
                System.out.println("Bye!");
                System.exit(0);
            } else if (playerInput.equals("!rating")) {
                System.out.println("Your rating: " + score);
            } else if (options.contains(playerInput)) {
                //air
                List<String> newOptions = new ArrayList<String>(options.size());
                int num = options.size() / 2 + 1;

                for (int i = options.indexOf(playerInput); i < options.size() + options.indexOf(playerInput); i++) {
                    int address = i + num;
                    while (address >= options.size()) {
                        address -= options.size();
                    }

                    newOptions.add(options.get(address));
                }

                //System.out.println(newOptions.toString());

                int randNum = random.nextInt(options.size());
                String computer = options.get(randNum);
                int playerIndex = newOptions.indexOf(playerInput);
                int cpuIndex = newOptions.indexOf(computer);

                if (playerIndex == cpuIndex) {
                    score += 50;
                    System.out.println("There is a draw " + computer);
                } else if (playerIndex > cpuIndex) {
                    System.out.println("Sorry, but the computer chose " + computer);
                } else {
                    score += 100;
                    System.out.println("Well done. The computer chose " + computer + " and failed");
                }


            } else {
                System.out.println("Invalid input");
            }
        }
    }
}
