package io.github.fuaadbashi.burger;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to the Burger Place!");
        System.out.println();
        new OrderTaker(new Scanner(System.in), System.out).run();
        System.out.println("Thanks for visiting.");
    }
}
