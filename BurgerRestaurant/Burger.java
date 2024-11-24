package WorkExamples.WorkExamples.BurgerPlaceRedone;

import java.util.Scanner;

public class Burger extends Item {
    
    private Item extra1;
    private Item extra2;
    private Item extra3;

    Scanner scanner = new Scanner(System.in);

    public Burger(Item extra1, Item extra2, Item extra3) {
        this.extra1 = extra1;
        this.extra2 = extra2;
        this.extra3 = extra3;
    }

    public Burger(String name, String type, String size, double price) {
        super(name, type, size, price);
    }

    public Item getExtra1() {
        return extra1;
    }

    public Item getExtra2() {
        return extra2;
    }

    public Item getExtra3() {
        return extra3;
    }

    public String addToppings(boolean wantToppings) {
        if (wantToppings) {
            String pickWhichExtra = scanner.nextLine().toLowerCase();
            System.out.println(pickWhichExtra);
            return pickWhichExtra.replace(",", "").replace("and", " ").replace(".", "").replace("extra", ""). replace("  ", " ");
        }
        return name + " Burger " + "Has no extra toppings";
    }

    public double getTotalToppingPrice(boolean wantToppings, String topping) {
        if (!wantToppings) {
            return 0;
        } else {
        return (0.5 * topping.split("\\s+").length);
        }
    }
    


    public String getAllBurger() {   
        return  "Cheese Burger, Chicken Burger, Delux Burger";  
    }

    public double getPrice() {
        if (size.contains("l") && size.contains("large")){
            if (type.contains("burger") && !name.equals("delux")) {
                return price + 1.00;
            }
        } return price;
    }



}
