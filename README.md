# TEXTBASED-Burger-Restaurant
This Java-based project is a Restaurant Ordering System designed to replicate the ordering process for a burger place. It offers a user-friendly interface for customers to customise their meals by selecting burgers, sides, drinks, and optional toppings. The system dynamically calculates the total price based on the chosen options and sizes.

# Restaurant Ordering System

## Overview
The **Restaurant Ordering System** is a console-based Java application that simulates a dynamic ordering process for a burger place. Customers can build their meals by choosing from a variety of burgers, sides, drinks, and extras. The application calculates the total price based on selected items and sizes, providing a detailed summary of the order.

## Features
- **Burger Options**: Choose from multiple burger types, including Cheese Burger, Chicken Burger, and Deluxe Burger.
- **Customizable Sides and Drinks**: Select from options like fries, coleslaw, and beverages (Fanta, Pepsi, etc.).
- **Topping Add-ons**: Add extras like gherkins, cheese, and onions for additional cost.
- **Dynamic Pricing**: Adjusts price based on size (Normal or Large) and selected items.
- **Interactive Menu**: Guides the user step-by-step through the ordering process.
- **Order Summary**: Displays the full order and calculates the final price, including toppings.

## Getting Started
### Prerequisites
- Java Development Kit (JDK) 8 or higher
- IDE or text editor (e.g., IntelliJ IDEA, Eclipse, or VS Code)
- Command-line interface for running the program

### Installation
1. Clone this repository:
   ```bash
   git clone https://github.com/your-username/restaurant-ordering-system.git

   2.	Navigate to the project directory:

cd restaurant-ordering-system


	3.	Open the project in your preferred IDE.

Running the Application

	1.	Compile the project:

javac Main.java


	2.	Run the application:

java Main


	3.	Follow the on-screen prompts to build your order.

Project Structure

	•	Item.java: Base class representing individual menu items (name, type, size, price).
	•	Burger.java: Extends Item, adds functionality for managing burger-specific properties and toppings.
	•	MealOrder.java: Represents a complete meal order with methods for menu display, pricing, and order summary.
	•	Main.java: Entry point for the application; handles user interaction and logic flow.

Example Usage

	•	Choose a burger: Deluxe
	•	Add a side: Fries
	•	Pick a drink: Pepsi
	•	Select size: Large
	•	Add toppings: Gherkins and Extra Cheese

The system will output the meal summary and total price.

Future Improvements

	•	Add a graphical user interface (GUI).
	•	Enable persistence of orders using a database.
	•	Expand menu options with additional customizations.
 	•	Change the print layout in main and improve 
   
