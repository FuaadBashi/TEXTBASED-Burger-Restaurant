# Burger Restaurant

[![CI](https://github.com/FuaadBashi/TEXTBASED-Burger-Restaurant/actions/workflows/ci.yml/badge.svg)](https://github.com/FuaadBashi/TEXTBASED-Burger-Restaurant/actions/workflows/ci.yml)

A console ordering system for a burger restaurant, written in Java. Customers build a meal from a
burger, side and drink, choose a size, add toppings, and get an itemised receipt.

```
Your order:
Large Chicken Burger         $  3.00
Large Fries                  $  1.50
Large Water                  $  0.80
  + Onions                   $  0.50
  + Extra Cheese             $  0.50
Total                        $  6.30
```

## Highlights

- **Pricing core separate from the console.** `Menu`, `MenuItem` and `MealOrder` hold all the
  pricing rules and have no I/O, so they are unit-tested directly. `OrderTaker` handles only the
  conversation.
- **Exact money.** Prices are `BigDecimal`, so totals never pick up floating-point error.
- **Validated input.** Every question repeats until it gets an answer on the menu. Unknown
  toppings are rejected, not charged. Only `y`/`yes` and `n`/`no` count as answers.
- **Invariants in the domain.** A `MealOrder` record refuses a side in the burger slot, duplicate
  toppings, or more than three toppings.
- **Testable I/O.** `OrderTaker` takes a `Scanner` and `PrintStream`, so whole sessions are
  replayed in tests from scripted input.

## Getting started

Requires JDK 17+ and Maven.

```bash
git clone https://github.com/FuaadBashi/TEXTBASED-Burger-Restaurant.git
cd TEXTBASED-Burger-Restaurant
mvn package
java -jar target/burger-restaurant.jar
```

## Pricing rules

| | Normal | Large |
| --- | --- | --- |
| Burger | Cheese, Chicken $2.00 · Deluxe $4.00 | +$1.00 (Deluxe unchanged) |
| Side | Fries, Mash, Coleslaw $1.00 · Nuggets $1.50 | +$0.50 |
| Drink | Fanta, Pepsi, 7UP $1.50 · Apple Juice $1.80 · Water $0.80 | +$1.00 (Water unchanged) |
| Toppings | Gherkins, Extra Cheese, Onions $0.50 each, up to 3 | same |

## Project structure

```
src/main/java/io/github/fuaadbashi/burger/
├── Main.java          entry point
├── OrderTaker.java    console conversation with input validation
├── Menu.java          menu items, prices, lookup
├── MenuItem.java      record: name, category, price, large upcharge
├── MealOrder.java     record: a meal, its invariants, total and receipt
├── Category.java
└── Size.java
```

## Tests

```bash
mvn verify
```

This runs the JUnit suite and checks formatting with google-java-format. The suite covers pricing
rules and full scripted sessions, including invalid choices, unknown toppings and several orders.
