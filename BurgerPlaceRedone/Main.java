package WorkExamples.WorkExamples.BurgerPlaceRedone;


import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean ordering = true;

        while (ordering) {
            MealOrder menu = new MealOrder();
            menu.printMenu();

            // Prompt user inputs
            String burgerChoice = getUserChoice("CHOOSE ONE Burger: ");
            String sideChoice = getUserChoice("CHOOSE ONE side: ");
            String drinkChoice = getUserChoice("CHOOSE ONE drink: ");
            String sizeChoice = getUserChoice("CHOOSE SIZE (Large/Normal): ").toLowerCase();
            boolean wantToppings = getUserConfirmation("DO YOU WANT EXTRA TOPPING ON YOUR BURGER (Y/N)?: ");

            // Create burger, side, and drink
            Burger burger = createBurger(burgerChoice, sizeChoice);
            Item side = createSide(sideChoice, sizeChoice);
            Item drink = createDrink(drinkChoice, sizeChoice);

            // Handle invalid inputs
            burger = validateItem(burger, "burger", burger.getAllBurger());
            side = validateItem(side, "side", side.getAllSide());
            drink = validateItem(drink, "drink", drink.getAllDrink());

            // Handle toppings
            String toppings = "";
            if (wantToppings) {
                toppings = addToppings(new Burger(
                    new Item("Gherkins", "extra", "na", 0.5),
                    new Item("Extra Cheese", "extra", "na", 0.5),
                    new Item("Onions", "extra", "na", 0.5)
                ));
            }

            // Create order and calculate total
            MealOrder order = new MealOrder(side, drink, burger);
            double totalPrice = order.printTotal() + calculateToppingPrice(wantToppings, toppings);

            // Display final order and price
            displayOrderSummary(order, toppings, totalPrice);

            // Confirm order completion
            ordering = !getUserConfirmation("Would you like to place another order? (Y/N): ");
        }
    }

 
    private static String getUserChoice(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine().toLowerCase();
    }

    private static boolean getUserConfirmation(String prompt) {
        System.out.println(prompt);
        String response = scanner.nextLine().toLowerCase();
        return response.contains("y") || response.contains("yes");
    }

    private static Burger createBurger(String choice, String size) {
        return switch (choice) {
            case "cheese" -> new Burger("Cheese", "burger", size, 2.0);
            case "chicken" -> new Burger("Chicken", "burger", size, 2.0);
            case "deluxe" -> new Burger("Deluxe", "burger", "normal", 4.0);
            default -> new Burger(choice, "not burger", size, 0.0);
        };
    }

    private static Item createSide(String choice, String size) {
        return switch (choice) {
            case "fries" -> new Item("Fries", "side", size, 1.0);
            case "nuggets" -> new Item("Nuggets", "side", size, 1.5);
            case "mash potatoes" -> new Item("Mash Potatoes", "side", size, 1.0);
            case "coleslaw" -> new Item("Coleslaw", "side", size, 1.0);
            default -> new Item(choice, "not side", size, 0.0);
        };
    }

    private static Item createDrink(String choice, String size) {
        return switch (choice) {
            case "fanta" -> new Item("Fanta", "drink", size, 1.5);
            case "pepsi" -> new Item("Pepsi", "drink", size, 1.5);
            case "7up" -> new Item("7UP", "drink", size, 1.5);
            case "water" -> new Item("Water", "drink", size, 0.8);
            default -> new Item(choice, "not drink", size, 0.0);
        };
    }

    private static <T extends Item> T validateItem(T item, String expectedType, String validOptions) {
        if (!item.getType().equals(expectedType)) {
            System.out.println(item.getName() + " is not a valid " + expectedType + ".");
            System.out.println("Available options: " + validOptions);
            String newChoice = getUserChoice("Please choose a valid " + expectedType + ": ");
            return (T) switch (expectedType) {
                case "burger" -> createBurger(newChoice, item.getSize());
                case "side" -> createSide(newChoice, item.getSize());
                case "drink" -> createDrink(newChoice, item.getSize());
                default -> item;
            };
        }
        return item;
    }

    private static String addToppings(Burger extraBurger) {
        System.out.println("Choose which extras you want (e.g., Gherkins, Extra Cheese, Onions): ");
        return extraBurger.addToppings(true);
    }

    private static double calculateToppingPrice(boolean wantToppings, String toppings) {
        if (!wantToppings || toppings.isEmpty()) {
            return 0.0;
        }
        return 0.5 * toppings.split("\\s+").length;
    }

    private static void displayOrderSummary(MealOrder order, String toppings, double totalPrice) {
        System.out.println("\nYour order summary:");
        System.out.println("Meal: " + order.ordered());
        if (!toppings.isEmpty()) {
            System.out.println("Extra Toppings: " + toppings);
        }
        System.out.printf("Total Price: $%.2f%n", totalPrice);
        System.out.println("--------------------------");
    }
}