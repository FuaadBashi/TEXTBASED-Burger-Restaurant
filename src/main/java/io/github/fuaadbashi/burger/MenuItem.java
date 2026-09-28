package io.github.fuaadbashi.burger;

import java.math.BigDecimal;

/**
 * One thing on the menu.
 *
 * @param largeUpcharge added when the meal is large; zero for items that don't come in sizes
 */
public record MenuItem(String name, Category category, BigDecimal price, BigDecimal largeUpcharge) {

    public BigDecimal priceFor(Size size) {
        return size == Size.LARGE ? price.add(largeUpcharge) : price;
    }
}
