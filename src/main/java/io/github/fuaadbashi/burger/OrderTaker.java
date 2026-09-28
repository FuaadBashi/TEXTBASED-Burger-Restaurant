package io.github.fuaadbashi.burger;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

/**
 * The console conversation. Each question repeats until it gets a valid answer.
 *
 * <p>One Scanner serves the whole session: a second Scanner on the same stream buffers input the
 * first never sees, which silently dropped answers when input was piped in.
 */
public class OrderTaker {

    private final Scanner in;
    private final PrintStream out;

    public OrderTaker(Scanner in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    /** Takes orders until the customer is done or input ends. Returns every completed meal. */
    public List<MealOrder> run() {
        List<MealOrder> orders = new ArrayList<>();
        out.println(Menu.describe());
        try {
            do {
                MealOrder order = takeOrder();
                orders.add(order);
                out.println();
                out.println("Your order:");
                order.itemizedLines().forEach(out::println);
                out.println("--------------------------------------");
            } while (askYesNo("Would you like to place another order? (y/n)"));
        } catch (EndOfInput e) {
            out.println();
        }
        return orders;
    }

    MealOrder takeOrder() {
        MenuItem burger = choose(Category.BURGER);
        MenuItem side = choose(Category.SIDE);
        MenuItem drink = choose(Category.DRINK);
        Size size = chooseSize();
        List<MenuItem> toppings =
                askYesNo("Extra toppings on your burger? (y/n)") ? chooseToppings() : List.of();
        return new MealOrder(burger, side, drink, size, toppings);
    }

    private MenuItem choose(Category category) {
        String label = category.name().toLowerCase(Locale.ROOT);
        while (true) {
            String answer = ask("Choose a " + label + " (" + Menu.names(category) + "):");
            Optional<MenuItem> item = Menu.find(category, answer);
            if (item.isPresent()) {
                return item.get();
            }
            out.println("'" + answer + "' isn't a " + label + " on the menu.");
        }
    }

    private Size chooseSize() {
        while (true) {
            String answer = ask("Size (normal/large):").toLowerCase(Locale.ROOT);
            switch (answer) {
                case "normal", "n":
                    return Size.NORMAL;
                case "large", "l":
                    return Size.LARGE;
                default:
                    out.println("Please answer normal or large.");
            }
        }
    }

    private List<MenuItem> chooseToppings() {
        while (true) {
            String answer =
                    ask(
                            "Which toppings? Separate with commas ("
                                    + Menu.names(Category.TOPPING)
                                    + "):");
            List<MenuItem> toppings = new ArrayList<>();
            List<String> unknown = new ArrayList<>();
            for (String name : answer.split(",")) {
                if (name.isBlank()) {
                    continue;
                }
                Menu.find(Category.TOPPING, name)
                        .ifPresentOrElse(
                                t -> {
                                    if (!toppings.contains(t)) {
                                        toppings.add(t);
                                    }
                                },
                                () -> unknown.add(name.trim()));
            }
            if (!unknown.isEmpty()) {
                out.println("Not on the menu: " + String.join(", ", unknown));
            } else if (toppings.size() > Menu.MAX_TOPPINGS) {
                out.println("At most " + Menu.MAX_TOPPINGS + " toppings, please.");
            } else {
                return toppings;
            }
        }
    }

    private boolean askYesNo(String prompt) {
        while (true) {
            String answer = ask(prompt).toLowerCase(Locale.ROOT);
            // Exact answers only: a substring match treated "no thank you" as yes.
            switch (answer) {
                case "y", "yes":
                    return true;
                case "n", "no":
                    return false;
                default:
                    out.println("Please answer y or n.");
            }
        }
    }

    private String ask(String prompt) {
        out.println(prompt);
        if (!in.hasNextLine()) {
            throw new EndOfInput();
        }
        return in.nextLine().trim();
    }

    private static final class EndOfInput extends RuntimeException {}
}
