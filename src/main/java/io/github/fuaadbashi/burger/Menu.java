package io.github.fuaadbashi.burger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

/** The restaurant's menu. Prices are BigDecimal so totals never pick up floating-point error. */
public final class Menu {

    private static final BigDecimal NONE = BigDecimal.ZERO;
    private static final BigDecimal LARGE_BURGER_OR_DRINK = new BigDecimal("1.00");
    private static final BigDecimal LARGE_SIDE = new BigDecimal("0.50");

    public static final int MAX_TOPPINGS = 3;

    private static final List<MenuItem> ITEMS =
            List.of(
                    item("Cheese", Category.BURGER, "2.00", LARGE_BURGER_OR_DRINK),
                    item("Chicken", Category.BURGER, "2.00", LARGE_BURGER_OR_DRINK),
                    // The Deluxe is already a large burger.
                    item("Deluxe", Category.BURGER, "4.00", NONE),
                    item("Fries", Category.SIDE, "1.00", LARGE_SIDE),
                    item("Nuggets", Category.SIDE, "1.50", LARGE_SIDE),
                    item("Mash Potatoes", Category.SIDE, "1.00", LARGE_SIDE),
                    item("Coleslaw", Category.SIDE, "1.00", LARGE_SIDE),
                    item("Fanta", Category.DRINK, "1.50", LARGE_BURGER_OR_DRINK),
                    item("Pepsi", Category.DRINK, "1.50", LARGE_BURGER_OR_DRINK),
                    item("7UP", Category.DRINK, "1.50", LARGE_BURGER_OR_DRINK),
                    item("Apple Juice", Category.DRINK, "1.80", LARGE_BURGER_OR_DRINK),
                    // Water is free to size up.
                    item("Water", Category.DRINK, "0.80", NONE),
                    item("Gherkins", Category.TOPPING, "0.50", NONE),
                    item("Extra Cheese", Category.TOPPING, "0.50", NONE),
                    item("Onions", Category.TOPPING, "0.50", NONE));

    private Menu() {}

    private static MenuItem item(String name, Category category, String price, BigDecimal large) {
        return new MenuItem(name, category, new BigDecimal(price), large);
    }

    public static List<MenuItem> items(Category category) {
        return ITEMS.stream().filter(i -> i.category() == category).toList();
    }

    /** Case-insensitive lookup within one category, so "coleslaw" finds "Coleslaw". */
    public static Optional<MenuItem> find(Category category, String name) {
        String wanted = name.trim().toLowerCase(Locale.ROOT);
        return items(category).stream()
                .filter(i -> i.name().toLowerCase(Locale.ROOT).equals(wanted))
                .findFirst();
    }

    public static String names(Category category) {
        return items(category).stream().map(MenuItem::name).collect(Collectors.joining(", "));
    }

    public static String describe() {
        StringBuilder text = new StringBuilder();
        for (Category category : Category.values()) {
            text.append(
                    String.format(
                            "%-10s",
                            category.name().charAt(0)
                                    + category.name().substring(1).toLowerCase(Locale.ROOT)
                                    + "s:"));
            text.append(
                    items(category).stream()
                            .map(i -> "$" + i.price() + " " + i.name())
                            .collect(Collectors.joining(", ")));
            text.append(System.lineSeparator());
        }
        text.append(
                        "Large meals: +$1.00 burger (except Deluxe), +$0.50 side, +$1.00 drink (except Water).")
                .append(System.lineSeparator())
                .append("Up to " + MAX_TOPPINGS + " extra toppings.");
        return text.toString();
    }
}
