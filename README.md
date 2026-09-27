# Burger Restaurant Ordering System

A Java console exercise that models menu items, burgers, and complete meal orders. Customers choose a burger, side, drink, size, and optional toppings before receiving an order summary.

## Run locally

Requires JDK 17 or later for the switch expressions used in the application.

```bash
git clone https://github.com/FuaadBashi/TEXTBASED-Burger-Restaurant.git
cd TEXTBASED-Burger-Restaurant
mkdir -p out
javac -d out BurgerRestaurant/*.java
java -cp out WorkExamples.WorkExamples.BurgerPlaceRedone.Main
```

## Code to explore

- [Item.java](BurgerRestaurant/Item.java): common menu-item properties.
- [Burger.java](BurgerRestaurant/Burger.java): burger options and toppings.
- [MealOrder.java](BurgerRestaurant/MealOrder.java): meal composition and pricing.
- [Main.java](BurgerRestaurant/Main.java): prompts and order flow.

Try an order using `cheese`, `fries`, `water`, and `normal`, then inspect the printed total. This is a learning exercise with in-memory orders and floating-point prices, not a payment or accounting system.
