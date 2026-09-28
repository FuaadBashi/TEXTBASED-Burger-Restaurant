package io.github.fuaadbashi.burger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;

class MealOrderTest {

    private static MenuItem get(Category category, String name) {
        return Menu.find(category, name).orElseThrow();
    }

    private static MealOrder meal(
            String burger, String side, String drink, Size size, String... toppings) {
        return new MealOrder(
                get(Category.BURGER, burger),
                get(Category.SIDE, side),
                get(Category.DRINK, drink),
                size,
                List.of(toppings).stream().map(t -> get(Category.TOPPING, t)).toList());
    }

    @Test
    void aNormalMealCostsTheSumOfItsMenuPrices() {
        assertEquals(new BigDecimal("4.50"), meal("Cheese", "Fries", "Pepsi", Size.NORMAL).total());
    }

    @Test
    void aLargeMealAddsADollarToBurgerAndDrinkAndFiftyCentsToTheSide() {
        assertEquals(new BigDecimal("7.00"), meal("Cheese", "Fries", "Pepsi", Size.LARGE).total());
    }

    @Test
    void theDeluxeBurgerAndWaterCostTheSameInLarge() {
        assertEquals(
                meal("Deluxe", "Fries", "Water", Size.NORMAL).total().add(new BigDecimal("0.50")),
                meal("Deluxe", "Fries", "Water", Size.LARGE).total());
    }

    @Test
    void eachToppingAddsFiftyCentsIncludingMultiWordOnes() {
        assertEquals(
                new BigDecimal("5.50"),
                meal("Cheese", "Fries", "Pepsi", Size.NORMAL, "Extra Cheese", "Onions").total());
    }

    @Test
    void menuLookupIgnoresCaseAndSurroundingSpaces() {
        assertEquals("Mash Potatoes", get(Category.SIDE, "  mash potatoes ").name());
    }

    @Test
    void anItemCannotBeOrderedAsTheWrongCategory() {
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        new MealOrder(
                                get(Category.SIDE, "Fries"),
                                get(Category.SIDE, "Fries"),
                                get(Category.DRINK, "Pepsi"),
                                Size.NORMAL,
                                List.of()));
    }
}
