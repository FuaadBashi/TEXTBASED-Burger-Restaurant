package WorkExamples.WorkExamples.BurgerPlaceRedone;

public class Item {
    String name;
    String type;
    String size;
    double price;

    public Item(){
    }

    public Item(String name, String type, String size, double price) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getAllSide() {   
        return  "Fries, Nuggets, Mash Potatoes, Coleslaw"; 
    }
    
    public String getAllDrink() {
        return  "Fanta, Pepsi, 7up, Water, Apple Juice";
    }


    public double getPrice() {
        if (size.contains("l") || size.contains("large")) {
            if (type.contains("drink") && !name.equals("water")) {
                return price + 1.00;
            }
            if (type.contains("side")) {
                return price + 0.50;
            } 
        } return price;
    }




    
}
