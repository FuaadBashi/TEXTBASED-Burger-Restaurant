package WorkExamples.WorkExamples.BurgerPlaceRedone;

public class MealOrder {
   private Item side;
   private Item drink;
   private Burger burger;

 
    public MealOrder() {
    }

   public MealOrder(Item side, Item drink, Burger burger) {
    this.side = side;
    this.drink = drink;
    this.burger = burger;
    }

    public void printMenu() {
        System.out.printf(".. %s%n".repeat(6),
        "Burgers: $2.00 Chicken, $2.00 Cheese, $4.00 Deluxe",
                "Sides: $1.00 Fries, $1.50 Nuggest, $1.00 Mash Potatoes, $1.00 Coleslaw",
                "Drinks: $1.50 Fanta, $1.50 Pepsi, $1.50 7UP, $0.80 Water, $1.80 Apple Juice",
                "Sizes: Normal and Large(Large comes with free Large drink)",
                "LARGE will increase Burgers and Drinks by FIXED price ($1), expect for water and Deluxe Burger",
                "Extra toppings on burger: $0.50 each");  
    }

    public void addToppings() {
    }

    public void setDrinkSize() {
    }

    public void printItemizedList() {
    }

    public double printTotal() {
        return side.getPrice() + burger.getPrice() + drink.getPrice();
    }


    public String ordered(){
        return burger.getName()+ " Burger " + side.getName() + " " + drink.getName();
    }

}

