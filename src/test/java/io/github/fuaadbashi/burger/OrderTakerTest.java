package io.github.fuaadbashi.burger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

class OrderTakerTest {

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    private List<MealOrder> session(String... lines) {
        Scanner in = new Scanner(String.join("\n", lines) + "\n");
        PrintStream out = new PrintStream(output, true, StandardCharsets.UTF_8);
        return new OrderTaker(in, out).run();
    }

    private String printed() {
        return output.toString(StandardCharsets.UTF_8);
    }

    @Test
    void aCompleteOrderIsPricedAndItemised() {
        List<MealOrder> orders =
                session("chicken", "nuggets", "fanta", "large", "y", "gherkins, extra cheese", "n");

        assertEquals(1, orders.size());
        // Large chicken 3.00 + large nuggets 2.00 + large Fanta 2.50 + two toppings 1.00
        assertEquals(new BigDecimal("8.50"), orders.get(0).total());
        assertTrue(printed().contains("+ Extra Cheese"));
    }

    @Test
    void anUnknownChoiceIsAskedAgainRatherThanAccepted() {
        List<MealOrder> orders = session("pizza", "cheese", "fries", "water", "normal", "n", "n");

        assertEquals("Cheese", orders.get(0).burger().name());
        assertTrue(printed().contains("'pizza' isn't a burger on the menu."));
    }

    @Test
    void anUnknownToppingIsRejectedInsteadOfCharged() {
        List<MealOrder> orders =
                session("cheese", "fries", "water", "normal", "y", "sprinkles", "onions", "n");

        assertEquals(
                List.of("Onions"), orders.get(0).toppings().stream().map(MenuItem::name).toList());
        assertTrue(printed().contains("Not on the menu: sprinkles"));
    }

    @Test
    void aSentenceContainingTheLetterYIsNotTakenAsYes() {
        List<MealOrder> orders =
                session("cheese", "fries", "water", "normal", "no thank you", "n", "n");

        assertTrue(orders.get(0).toppings().isEmpty());
    }

    @Test
    void severalOrdersCanBePlacedInOneSession() {
        List<MealOrder> orders =
                session(
                        "cheese",
                        "fries",
                        "pepsi",
                        "normal",
                        "n",
                        "y",
                        "deluxe",
                        "coleslaw",
                        "7up",
                        "large",
                        "n",
                        "n");

        assertEquals(2, orders.size());
    }

    @Test
    void runningOutOfInputEndsTheSessionWithoutAnError() {
        assertTrue(session("cheese", "fries").isEmpty());
    }
}
