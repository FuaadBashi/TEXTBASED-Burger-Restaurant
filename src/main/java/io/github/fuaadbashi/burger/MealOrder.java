package io.github.fuaadbashi.burger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/** A burger meal: one burger, side and drink in a single size, plus optional toppings. */
public record MealOrder(
        MenuItem burger, MenuItem side, MenuItem drink, Size size, List<MenuItem> toppings) {

    public MealOrder {
        require(burger, Category.BURGER);
        require(side, Category.SIDE);
        require(drink, Category.DRINK);
        toppings = List.copyOf(toppings);
        toppings.forEach(t -> require(t, Category.TOPPING));
        if (toppings.size() > Menu.MAX_TOPPINGS) {
            throw new IllegalArgumentException("At most " + Menu.MAX_TOPPINGS + " toppings");
        }
        if (toppings.stream().distinct().count() != toppings.size()) {
            throw new IllegalArgumentException("Each topping can only be added once");
        }
    }

    private static void require(MenuItem item, Category category) {
        if (item == null || item.category() != category) {
            throw new IllegalArgumentException("Expected a " + category + ", got " + item);
        }
    }

    public List<String> itemizedLines() {
        List<String> lines = new ArrayList<>();
        String sizeLabel = size == Size.LARGE ? "Large " : "";
        lines.add(line(sizeLabel + burger.name() + " Burger", burger.priceFor(size)));
        lines.add(line(sizeLabel + side.name(), side.priceFor(size)));
        lines.add(line(sizeLabel + drink.name(), drink.priceFor(size)));
        toppings.forEach(t -> lines.add(line("  + " + t.name(), t.priceFor(size))));
        lines.add(line("Total", total()));
        return lines;
    }

    private static String line(String label, BigDecimal price) {
        return String.format("%-28s $%6s", label, price);
    }

    public BigDecimal total() {
        BigDecimal total = burger.priceFor(size).add(side.priceFor(size)).add(drink.priceFor(size));
        for (MenuItem topping : toppings) {
            total = total.add(topping.priceFor(size));
        }
        return total;
    }
}
